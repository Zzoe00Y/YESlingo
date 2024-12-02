package interface_adapter.history;

import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;

/**
 * Controller for the History Use Case.
 */
public class HistoryController {
    private final HistoryInputBoundary historyInteractor;

    public HistoryController(HistoryInputBoundary historyInteractor) {
        this.historyInteractor = historyInteractor;
    }

    /**
     * Executes the "switch to LoggedInView" Use Case.
     */
    public void switchToLoggedInView() {
        historyInteractor.switchToLoggedInView();
    }

    /**
     * Pulls the user with the username.
     * @param username the username
     */
    public void pullUser(String username) {
        final HistoryInputData inputData = new HistoryInputData(username);
        historyInteractor.pullUser(inputData);
    }

    /**
     * Clears all the history of the user.
     * @param username the username
     */
    public void clearAll(String username) {
        final HistoryInputData inputData = new HistoryInputData(username);
        historyInteractor.clearAll(inputData);
    }
}
