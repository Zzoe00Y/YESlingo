package use_case.chatbot;

import interface_adapter.chatbot.ChatBotState;

/**
 * The output boundary for the ChatBot Use Case.
 */
public interface ChatBotOutputBoundary {

    /**
     * Display the response message for the ChatBot sendChat Use Case.
     * @param outputData the output data
     */
    void displayResponse(ChatBotOutputData outputData);

    /**
     * Switches to the LoggedIn View.
     */
    void switchToLoggedInView();

    /**
     * Update the chatBot state through pull user use case.
     * @param newState the new state
     */
    void pullUser(ChatBotState newState);
}
