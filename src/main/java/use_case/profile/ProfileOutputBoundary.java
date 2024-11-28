package use_case.profile;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public interface ProfileOutputBoundary {

    /**
     * Switches to the LoggedIn View.
     */
    void switchToLoggedInView();

    /**
     * Switches to the ChangePassword View.
     */
    void switchToChangePasswordView(LoginOutputData loginOutputdata);

    /**
     * Switches to the LogIn View.
     */
    void switchToLogInView();

    /**
     * Switches to the ChangeLanguage View.
     */
    void switchToChangeLanguageView();
}
