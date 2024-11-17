package use_case.chatbot;

import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.Message;
import com.cohere.api.types.NonStreamedChatResponse;
import entity.ChatMessage;
import entity.User;
import entity.UserFactory;

import java.util.List;

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
//        if (userDataAccessObject.existsByName(chatbotInputData.getUsername())) {
            if (chatbotInputData.getInputLan().equals("English")){
//                ChatMessage response = new ChatMessage("CHATBOT", "helllwwwwwwwwwwwwwwwwwwwww wwwwwwwwwwwwwwww\n" +
//                        "wwwwwwwwwwwww wwwwwwwwwwwwwwww wwwwwwwwwwwmmm mmmmmmmmmmmmmm\nmmmmmmmmmmmmmmmmmmmmmmmmm mmmmmm" +
//                        "" +
//                        ":) 4444444444444" +
//                        "\n\n\n\n hahahahaha");
                ChatMessage response = generateResponse(chatbotInputData.getMessage(), chatbotInputData.getChannelID());
//                userDataAccessObject.save(user);

                final ChatBotOutputData chatbotOutputData = new ChatBotOutputData(response);
                userPresenter.displayResponse(chatbotOutputData);
            }
//        }
//        else if (!chatbotInputData.getPassword().equals(chatbotInputData.getRepeatPassword())) {
//            userPresenter.prepareFailView("Passwords don't match.");
//        }
//        else {
//            final User user = userFactory.create(chatbotInputData.getUsername(), chatbotInputData.getPassword());
//            userDataAccessObject.save(user);
//
//            final ChatBotOutputData chatbotOutputData = new ChatBotOutputData(user.getName(), false);
//            userPresenter.prepareSuccessView(chatbotOutputData);
//        }
    }

    private ChatMessage generateResponse(String message, String channelID) {
        Cohere cohere = Cohere.builder().token("O40OXvNOKzdUtm6vQlpLiE7erjfv81ZeFUeHbvmg").clientName("snippet").build();

        NonStreamedChatResponse response =
                cohere.chat(
                        ChatRequest.builder()
                                .message(message)
//                                .conversationId(channelID)
                                .build());

        return new ChatMessage("CHATBOT", response.getText());
    }

    @Override
    public void switchToLoggedInView() {
        userPresenter.switchToLoggedInView();
    }
}
