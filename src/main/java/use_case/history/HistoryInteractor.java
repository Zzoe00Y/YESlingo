package use_case.history;

import entity.User;
import entity.UserFactory;
import interface_adapter.chatbot.ChatBotState;
import interface_adapter.history.HistoryState;
import use_case.chatbot.ChatBotUserDataAccessInterface;

import java.util.ArrayList;

/**
 * The History Interactor.
 */
public class HistoryInteractor implements HistoryInputBoundary {
    private final HistoryUserDataAccessInterface userDataAccessObject;
    private final HistoryOutputBoundary historyPresenter;
    private final UserFactory userFactory;

    public HistoryInteractor(HistoryUserDataAccessInterface userDataAccessObject, HistoryOutputBoundary historyPresenter,
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
    public void pullUser(String userName) {
        User user = userDataAccessObject.get(userName);
        HistoryState newState = new HistoryState(userName, user.getHistory());
        historyPresenter.pullUser(newState);
    }

    @Override
    public void clearAll(String username) {
        User user = userDataAccessObject.get(username);
        user.setHistory(new ArrayList<>());
        userDataAccessObject.save(user);
        HistoryState newState = new HistoryState(username, user.getHistory());
        historyPresenter.clearAll(newState);
    }
}
