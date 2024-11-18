package use_case.profile;

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
}
