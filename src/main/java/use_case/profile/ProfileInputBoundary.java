package use_case.profile;

/**
 * Input boundary interface for the Profile use case.
 * Defines methods for switching between different views in the profile system.
 */
public interface ProfileInputBoundary {

    /**
     * Executes the switch to loggedin view use case.
     */
    void switchToLoggedInView();

    /**
     * Executes the switch to change password view use case.
     */
    void switchToChangePasswordView();

    /**
     * Executes the switch to log in view use case.
     */
    void switchToLogInView();

    /**
     * Executes the switch to change language view use case.
     */
    void switchToChangeLanguageView();
}
