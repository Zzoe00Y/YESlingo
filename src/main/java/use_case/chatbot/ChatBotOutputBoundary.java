package use_case.chatbot;

/**
 * The output boundary for the ChatBot Use Case.
 */
public interface ChatBotOutputBoundary {

    /**
     * Prepares the success view for the ChatBot Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChatBotOutputData outputData);

    /**
     * Display the response message for the ChatBot sendChat Use Case.
     * @param outputData the output data
     */
    void displayResponse(ChatBotOutputData outputData);

    /**
     * Prepares the failure view for the ChatBot Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the LoggedIn View.
     */
    void switchToLoggedInView();
}
