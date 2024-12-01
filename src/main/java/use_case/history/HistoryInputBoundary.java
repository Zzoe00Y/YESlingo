package use_case.history;

public interface HistoryInputBoundary {

    /**
     * Executes the switch to loggedin view use case.
     */
    void switchToLoggedInView();

    void pullUser(String username);

    void clearAll(String username);
}
