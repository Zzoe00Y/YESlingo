package interface_adapter.loggedin_homepage;

import use_case.loggedin.LoggedInInputBoundary;

/**
 * Controller for the Loggedin Use Case.
 */
public class LoggedInController {

    private final LoggedInInputBoundary loggedInUseCaseInteractor;

    public LoggedInController(LoggedInInputBoundary loggedInUseCaseInteractor) {
        this.loggedInUseCaseInteractor = loggedInUseCaseInteractor;
    }

    /**
     * Executes the "switch to ProfileView" Use Case.
     */
    public void switchToProfileView() {
        loggedInUseCaseInteractor.switchToProfileView();
    }

    /**
     * Executes the "switch to ProfileView" Use Case.
     * @param username the username
     */
    public void switchToHistoryView(String username) {
        loggedInUseCaseInteractor.switchToHistoryView(username);
    }

    /**
     * Executes the "switch to ChatBotView" Use Case.
     * @param username the username
     */
    public void switchToChatBotView(String username) {
        loggedInUseCaseInteractor.switchToChatBotView(username);
    }
}
