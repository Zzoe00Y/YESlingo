package use_case.profile;

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
