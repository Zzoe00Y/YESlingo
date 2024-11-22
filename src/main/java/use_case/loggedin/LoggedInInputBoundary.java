package use_case.loggedin;

/**
 * Input Boundary for actions which are related to logged in.
 */
public interface LoggedInInputBoundary {

    /**
     * Executes the switch to profile view use case.
     */
    void switchToProfileView();

    /**
     * Executes the switch to chatBot view use case.
     */
    void switchToChatBotView(String username);

}
