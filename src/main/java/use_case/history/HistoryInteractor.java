package use_case.history;

import entity.UserFactory;

/**
 * The History Interactor.
 */
public class HistoryInteractor implements HistoryInputBoundary {
    private final HistoryOutputBoundary historyPresenter;
    private final UserFactory userFactory;

    public HistoryInteractor(HistoryOutputBoundary historyPresenter,
                             UserFactory userFactory) {
        this.historyPresenter = historyPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToLoggedInView() {
        historyPresenter.switchToLoggedInView();
    }
}
