package use_case.history;

import entity.User;
import entity.UserFactory;

import java.util.ArrayList;

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
        User user = userDataAccessObject.get(inputData.getUsername());
        HistoryOutputData outputData = new HistoryOutputData(inputData.getUsername(), user.getHistory());
        historyPresenter.pullUser(outputData);
    }

    @Override
    public void clearAll(HistoryInputData inputData) {
        User user = userDataAccessObject.get(inputData.getUsername());
        user.setHistory(new ArrayList<>());
        userDataAccessObject.save(user);
        HistoryOutputData outputData = new HistoryOutputData(inputData.getUsername(), user.getHistory());
        historyPresenter.clearAll(outputData);
    }
}
