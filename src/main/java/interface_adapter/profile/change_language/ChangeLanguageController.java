package interface_adapter.profile.change_language;

import use_case.profile.change_language.ChangeLanguageInputBoundary;
import use_case.profile.change_language.ChangeLanguageInputData;
import view.ChangeLanguageView;

/**
 * Controller for the Change Language Use Case.
 */
public class ChangeLanguageController {

    private final ChangeLanguageInputBoundary changeLanguageUseCaseInteractor;

    public ChangeLanguageController(ChangeLanguageInputBoundary changeLanguageUseCaseInteractor) {
        this.changeLanguageUseCaseInteractor = changeLanguageUseCaseInteractor;
    }

    /**
     * Executes the Change Language Case.
     * @param username the username of the user
     * @param selectedLanguage the default language of the user
     */
    public void execute(String username, ChangeLanguageView.LanguageItem selectedLanguage) {
        final ChangeLanguageInputData changeLanguageInputData = new ChangeLanguageInputData(username, selectedLanguage);
        changeLanguageUseCaseInteractor.execute(changeLanguageInputData);
    }

    /**
     * Executes the "switch to ProfileView" Use Case.
     */
    public void switchToProfileView() {
        changeLanguageUseCaseInteractor.switchToProfileView();
    }
}
