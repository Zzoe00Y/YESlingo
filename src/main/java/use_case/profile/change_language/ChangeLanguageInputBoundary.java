package use_case.profile.change_language;

/**
 * The Change language Use Case.
 */
public interface ChangeLanguageInputBoundary {

    /**
     * Executes the change language use case.
     * @param changeLanguageInputData the input data
     */
    void execute(ChangeLanguageInputData changeLanguageInputData);

    /**
     * Executes the switch to profile view use case.
     */
    void switchToProfileView();
}
