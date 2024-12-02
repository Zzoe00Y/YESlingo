package use_case.history;

/**
 * Input boundary for the History use case.
 * Defines operations for managing user translation history.
 */
public interface HistoryInputBoundary {

    /**
     * Executes the switch to logged-in view use case.
     * Changes the current view to the logged-in user profile view.
     */
    void switchToLoggedInView();

    /**
     * Retrieves user history data.
     *
     * @param inputData the input data containing user information, must not be null
     */
    void pullUser(HistoryInputData inputData);

    /**
     * Clears all history entries for a user.
     *
     * @param inputData the input data containing user information, must not be null
     */
    void clearAll(HistoryInputData inputData);
}
