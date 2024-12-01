package use_case.profile;

import use_case.login.LoginOutputData;

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
