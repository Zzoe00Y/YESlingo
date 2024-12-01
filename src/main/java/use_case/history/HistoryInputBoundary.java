package use_case.history;

public interface HistoryInputBoundary {

    /**
     * Executes the switch to loggedin view use case.
     */
    void switchToLoggedInView();

    /**
     * Pulls the user with the username.
     * @param username the username
     */
    void pullUser(String username);

    /**
     * Clears all the history of the user.
     * @param username the username
     */
    void clearAll(String username);
}
