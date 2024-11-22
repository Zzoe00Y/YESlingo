package interface_adapter.chatbot;

import use_case.chatbot.ChatBotInputBoundary;
import use_case.chatbot.ChatBotInputData;

/**
 * The controller for the Chatbot Use Case.
 */
public class ChatBotController {

    private final ChatBotInputBoundary chatBotUseCaseInteractor;

    public ChatBotController(ChatBotInputBoundary chatBotUseCaseInteractor) {
        this.chatBotUseCaseInteractor = chatBotUseCaseInteractor;
    }

    /**
     * Executes the sendChat Use Case.
     * @param message the message that was sent to generate response
     * @param inputLan language of the message
     * @param outputLan desired language of the output
     * @param chatChannelID the ID for this chat channel
     * @param username the username of this user
     */
    public void sendChat(String message, String inputLan, String outputLan, String chatChannelID, String username) {
        chatBotUseCaseInteractor.sendChat(new ChatBotInputData(message, inputLan, outputLan, username));
    }

    /**
     * Executes the exit use case
     */
    public void switchToLoggedInView() {
        chatBotUseCaseInteractor.switchToLoggedInView();
    }

    /**
     * Executes the pull user info use case
     */
    public void pullUser(String username) {
        chatBotUseCaseInteractor.pullUser(username);
    }

}
