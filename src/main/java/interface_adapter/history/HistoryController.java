package interface_adapter.history;

import use_case.history.HistoryInputBoundary;

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
}
