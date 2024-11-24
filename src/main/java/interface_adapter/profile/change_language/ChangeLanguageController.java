package interface_adapter.profile.change_language;

import use_case.profile.change_language.ChangeLanguageInputBoundary;

/**
 * Controller for the Change Language Use Case.
 */
public class ChangeLanguageController {

    private final ChangeLanguageInputBoundary changeLanguageUseCaseInteractor;

    public ChangeLanguageController(ChangeLanguageInputBoundary changeLanguageUseCaseInteractor) {
        this.changeLanguageUseCaseInteractor = changeLanguageUseCaseInteractor;
    }

    /**
     * Executes the "switch to ProfileView" Use Case.
     */
    public void switchToProfileView() {
        changeLanguageUseCaseInteractor.switchToProfileView();
    }
}
