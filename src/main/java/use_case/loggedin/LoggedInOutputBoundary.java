package use_case.loggedin;

/**
 * The output boundary for the Loggedin Use Case.
 */
public interface LoggedInOutputBoundary {

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();

    void switchToChatBotView(String username);
    
    void switchToHistoryView(String username);
}
