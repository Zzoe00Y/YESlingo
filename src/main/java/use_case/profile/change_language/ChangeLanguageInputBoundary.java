package use_case.profile.change_language;

import view.ChangeLanguageView;

/**
 * The Change language Use Case.
 */
public interface ChangeLanguageInputBoundary {

    /**
     * Executes the switch to profile view use case.
     */
    void switchToProfileView();

    void execute(ChangeLanguageInputData changeLanguageInputData);
}
