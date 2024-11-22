package use_case.chatbot;

import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.Message;
import com.cohere.api.types.NonStreamedChatResponse;
import entity.ChatMessage;
import entity.User;
import entity.UserFactory;
import interface_adapter.chatbot.ChatBotState;

import java.util.ArrayList;

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

        String inputMessage = chatbotInputData.getMessage();
        String inputLan = chatbotInputData.getInputLan();
        String outputLan = chatbotInputData.getOutputLan();
        String inputMessageEng = translate(inputMessage, inputLan, "English");

        ChatMessage responseEng = generateResponse(inputMessageEng, user.getChatHistoryMessagesEng());

        String outputMessage = translate(responseEng.getMessage(), "English", outputLan);
        ChatMessage output = new ChatMessage("CHATBOT",outputMessage);
        updateUserChatHistory(user, output, responseEng);

        final ChatBotOutputData chatbotOutputData = new ChatBotOutputData(output);
        userPresenter.displayResponse(chatbotOutputData);
    }

    private String translate(final String inputMessage, final String inputLan, final String outputLan) {
        return inputMessage;
    }

    private void updateUserChatHistory(User user, ChatMessage chatMessageDisplay, ChatMessage chatMessageEng) {
        user.addChatHistoryMessagesDisplay(chatMessageDisplay);
        user.addChatHistoryMessagesEng(chatMessageEng);
        userDataAccessObject.save(user);
    }

    /**
     * return a ChatMessage with role CHATBOT and response generated with API Cohere.
     */
    private ChatMessage generateResponse(String message, ArrayList<ChatMessage> chatHistoryMessages) {
        Cohere cohere = Cohere.builder().token("O40OXvNOKzdUtm6vQlpLiE7erjfv81ZeFUeHbvmg").build();

        ArrayList<Message> chatHistoryMessagesList = new ArrayList<>();
        for (ChatMessage chatMessage : chatHistoryMessages) {
            String role = chatMessage.getRole();
            String value = chatMessage.getMessage();
            com.cohere.api.types.ChatMessage m = com.cohere.api.types.ChatMessage.builder()
                                                                                .message(value)
                                                                                .build();
            switch (role){
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

        NonStreamedChatResponse response =
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
        User user = userDataAccessObject.get(userName);
        ChatBotState newState = new ChatBotState(userName, user.getInputLan(), user.getOutputLan(), user.getChatHistoryMessagesDisplay());
        userPresenter.pullUser(newState);
    }
}