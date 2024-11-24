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
     */
    public void switchToHistoryView() {
        loggedInUseCaseInteractor.switchToHistoryView();
    }

    /**
     * Executes the "switch to ChatBotView" Use Case.
     */
    public void switchToChatBotView(String username) {
        loggedInUseCaseInteractor.switchToChatBotView(username);
    }
}
