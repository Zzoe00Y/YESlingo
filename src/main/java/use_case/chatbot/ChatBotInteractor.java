package use_case.chatbot;

import java.util.ArrayList;
import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.Message;
import com.cohere.api.types.NonStreamedChatResponse;

import entity.ChatMessage;
import entity.User;
import entity.UserFactory;
import interface_adapter.chatbot.ChatBotState;
import space.dynomake.libretranslate.Language;
import space.dynomake.libretranslate.Translator;

/**
 * The ChatBot Interactor.
 */
public class ChatBotInteractor implements ChatBotInputBoundary {
    private final ChatBotUserDataAccessInterface userDataAccessObject;
    private final ChatBotOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChatBotInteractor(ChatBotUserDataAccessInterface chatbotDataAccessInterface,
                            ChatBotOutputBoundary chatbotOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = chatbotDataAccessInterface;
        this.userPresenter = chatbotOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void sendChat(ChatBotInputData chatbotInputData) {
        final User user = userDataAccessObject.get(chatbotInputData.getUsername());

        final String inputMessage = chatbotInputData.getMessage();
        final String inputLan = chatbotInputData.getInputLan();
        final String outputLan = chatbotInputData.getOutputLan();
        final String inputMessageEng = translate(inputMessage, inputLan, "ENGLISH");

        final ChatMessage responseEng = generateResponse(inputMessageEng, user.getChatHistoryMessagesEng());
        final String outputMessage = translate(responseEng.getMessage(), "ENGLISH", outputLan);
        final ChatMessage output = new ChatMessage("CHATBOT", outputMessage);

        updateUserChatHistory(user, output, responseEng, new ChatMessage("USER", inputMessage),
                new ChatMessage("USER", inputMessageEng));

        final ChatBotOutputData chatbotOutputData = new ChatBotOutputData(output);
        userPresenter.displayResponse(chatbotOutputData);
    }

    private String translate(final String inputMessage, final String inputLan, final String outputLan) {
        return Translator.translate(Language.valueOf(inputLan), Language.valueOf(outputLan), inputMessage);
    }

    private void updateUserChatHistory(User user, ChatMessage messageDisplayOut, ChatMessage messageEngOut,
                                       ChatMessage messageDisplayIn, ChatMessage messageEngIn) {
        user.addChatHistoryMessagesDisplay(messageDisplayIn);
        user.addChatHistoryMessagesDisplay(messageDisplayOut);
        user.addChatHistoryMessagesEng(messageEngIn);
        user.addChatHistoryMessagesEng(messageEngOut);
        userDataAccessObject.save(user);
    }

    /**
     * Return a ChatMessage with role CHATBOT and response generated with API Cohere.
     */
    private ChatMessage generateResponse(String message, ArrayList<ChatMessage> chatHistoryMessages) {
        final Cohere cohere = Cohere.builder().token("O40OXvNOKzdUtm6vQlpLiE7erjfv81ZeFUeHbvmg").build();

        final ArrayList<Message> chatHistoryMessagesList = new ArrayList<>();
        for (ChatMessage chatMessage : chatHistoryMessages) {
            final String role = chatMessage.getRole();
            final String value = chatMessage.getMessage();
            final com.cohere.api.types.ChatMessage m = com.cohere.api.types.ChatMessage.builder()
                                                                                .message(value)
                                                                                .build();
            switch (role) {
                case "USER":
                    chatHistoryMessagesList.add(Message.user(m));
                    break;
                case "CHATBOT":
                    chatHistoryMessagesList.add(Message.chatbot(m));
                    break;
                case "SYSTEM":
                    chatHistoryMessagesList.add(Message.system(m));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + role);
            }
        }

        final NonStreamedChatResponse response =
                cohere.chat(
                        ChatRequest.builder()
                                .message(message)
                                .chatHistory(chatHistoryMessagesList)
                                .build());

        return new ChatMessage("CHATBOT", response.getText());
    }

    @Override
    public void switchToLoggedInView() {
        userPresenter.switchToLoggedInView();
    }

    @Override
    public void pullUser(String userName) {
        final User user = userDataAccessObject.get(userName);
        final ChatBotState newState = new ChatBotState(userName, user.getInputLan(), user.getOutputLan(),
                user.getChatHistoryMessagesDisplay());
        userPresenter.pullUser(newState);
    }
}
