package use_case.history;

/**
 * Output boundary for the History use case.
 * Defines methods for presenting history-related results to the view.
 */
public interface HistoryOutputBoundary {
    /**
     * Switches to the LoggedIn View.
     * Changes the current view to display the logged-in user interface.
     */
    void switchToLoggedInView();

    /**
     * Presents the user's translation history data.
     *
     * @param outputData the output data containing user history information, must not be null
     */
    void pullUser(HistoryOutputData outputData);

    /**
     * Presents the result of clearing all history entries.
     *
     * @param outputData the output data containing the clear operation result, must not be null
     */
    void clearAll(HistoryOutputData outputData);
}
