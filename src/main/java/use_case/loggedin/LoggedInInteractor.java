package use_case.loggedin;

/**
 * The LoggedIn Interactor.
 */
public class LoggedInInteractor implements LoggedInInputBoundary {
    private final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInOutputBoundary) {
        this.loggedInPresenter = loggedInOutputBoundary;
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
