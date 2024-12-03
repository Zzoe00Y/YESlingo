package use_case.profile;

/**
 * Output boundary interface for the Profile use case.
 * Defines methods for switching between different views in the profile system.
 */
public interface ProfileOutputBoundary {

    /**
     * Switches to the LoggedIn View.
     */
    void switchToLoggedInView();

    /**
     * Switches to the ChangePassword View.
     */
    void switchToChangePasswordView();

    /**
     * Switches to the LogIn View.
     */
    void switchToLogInView();

    /**
     * Switches to the ChangeLanguage View.
     */
    void switchToChangeLanguageView();
}
