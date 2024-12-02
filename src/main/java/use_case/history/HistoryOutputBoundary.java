package use_case.history;

import interface_adapter.history.HistoryState;

public interface HistoryOutputBoundary {

    /**
     * Switches to the LoggedIn View.
     */
    void switchToLoggedInView();

    /**
     * Pulls the user.
     * @param newState the new state
     */
    void pullUser(HistoryState newState);

    /**
     * Clears all the history.
     * @param newState the new state
     */
    void clearAll(HistoryState newState);
}
