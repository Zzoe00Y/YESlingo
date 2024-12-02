package use_case.history;

public interface HistoryOutputBoundary {
    /**
     * Switches to the LoggedIn View.
     */
    void switchToLoggedInView();

    void pullUser(HistoryOutputData outputData);

    void clearAll(HistoryOutputData outputData);
}
