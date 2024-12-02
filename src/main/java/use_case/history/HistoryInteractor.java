package use_case.history;

import java.util.ArrayList;

import entity.User;
import entity.UserFactory;

/**
 * The interactor class for the History use case.
 * Responsible for handling user history actions, such as retrieving history,
 * clearing history, and managing the transition between different views.
 */
public class HistoryInteractor implements HistoryInputBoundary {
    private final HistoryUserDataAccessInterface userDataAccessObject;
    private final HistoryOutputBoundary historyPresenter;
    private final UserFactory userFactory;

    public HistoryInteractor(HistoryUserDataAccessInterface userDataAccessObject,
                             HistoryOutputBoundary historyPresenter,
                             UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.historyPresenter = historyPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToLoggedInView() {
        historyPresenter.switchToLoggedInView();
    }

    @Override
    public void pullUser(HistoryInputData inputData) {
        final User user = userDataAccessObject.get(inputData.getUsername());
        final HistoryOutputData outputData = new HistoryOutputData(inputData.getUsername(), user.getHistory());
        historyPresenter.pullUser(outputData);
    }

    @Override
    public void clearAll(HistoryInputData inputData) {
        final User user = userDataAccessObject.get(inputData.getUsername());
        user.setHistory(new ArrayList<>());
        userDataAccessObject.save(user);
        final HistoryOutputData outputData = new HistoryOutputData(inputData.getUsername(), user.getHistory());
        historyPresenter.clearAll(outputData);
    }
}
