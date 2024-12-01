package use_case.loggedin;

import entity.UserFactory;

/**
 * The LoggedIn Interactor.
 */
public class LoggedInInteractor implements LoggedInInputBoundary {
    private final LoggedInOutputBoundary loggedInPresenter;
    private final UserFactory userFactory;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary,
                              UserFactory factory) {
        this.loggedInPresenter = loggedInOutputBoundary;
        this.userFactory = factory;
    }

    @Override
    public void switchToProfileView() {
        loggedInPresenter.switchToProfileView();
    }

    @Override
    public void switchToChatBotView(String username) {
        loggedInPresenter.switchToChatBotView(username);
    }

    @Override
    public void switchToHistoryView(String username) {
        loggedInPresenter.switchToHistoryView(username);
    }

}
