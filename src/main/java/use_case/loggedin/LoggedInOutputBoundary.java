package use_case.loggedin;

/**
 * The output boundary for the Loggedin Use Case.
 */
public interface LoggedInOutputBoundary {

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();

    /**
     * Switches to the ChatBot View.
     * @param username the username
     */
    void switchToChatBotView(String username);

    /**
     * Switches to the History View.
     * @param username the username
     */
    void switchToHistoryView(String username);
}
