package use_case.history;

public interface HistoryInputBoundary {

    /**
     * Executes the switch to loggedin view use case.
     */
    void switchToLoggedInView();

    void pullUser(HistoryInputData inputData);

    void clearAll(HistoryInputData inputData);
}
