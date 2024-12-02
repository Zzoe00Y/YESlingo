package interface_adapter.history;

import use_case.history.HistoryInputBoundary;

/**
 * Controller for the History Use Case.
 */
public class HistoryController {
    private HistoryInputBoundary historyInteractor;

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
        historyInteractor.pullUser(username);
    }

    /**
     * Clears all the history of the user.
     * @param username the username
     */
    public void clearAll(String username) {
        historyInteractor.clearAll(username);
    }
}
