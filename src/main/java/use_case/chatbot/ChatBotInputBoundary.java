package use_case.chatbot;

/**
 * Input Boundary for actions which are related to chatbot.
 */
public interface ChatBotInputBoundary {

    /**
     * Executes the chatbot sent chat use case.
     * @param chatbotInputData the input data
     */
    void sendChat(ChatBotInputData chatbotInputData);

    /**
     * Executes the switch to loggedIn view use case.
     */
    void switchToLoggedInView();

    /**
     * Executes the pull user use case.
     * @param userName the username
     */
    void pullUser(String userName);
}
