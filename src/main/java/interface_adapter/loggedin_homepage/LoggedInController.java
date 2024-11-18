package interface_adapter.loggedin_homepage;

import use_case.loggedin.LoggedInInputBoundary;
import use_case.loggedin.LoggedInInputData;

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
}
