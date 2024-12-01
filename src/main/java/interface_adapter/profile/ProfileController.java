package interface_adapter.profile;

import use_case.login.LoginOutputData;
import use_case.profile.ProfileInputBoundary;

public class ProfileController {
    private final ProfileInputBoundary profileInteractor;

    public ProfileController(ProfileInputBoundary profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    /**
     * Executes the "switch to LoggedInView" Use Case.
     */
    public void switchToLoggedInView() {
        profileInteractor.switchToLoggedInView();
    }

    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     */
    public void switchToChangePasswordView() {
        profileInteractor.switchToChangePasswordView();
    }

    /**
     * Executes the "switch to LogInView" Use Case.
     */
    public void switchToLogInView() {
        profileInteractor.switchToLogInView();
    }

    /**
     * Executes the "switch to ChangeLanguageView" Use Case.
     */
    public void switchToChangeLanguageView() {
        profileInteractor.switchToChangeLanguageView();
    }
}
