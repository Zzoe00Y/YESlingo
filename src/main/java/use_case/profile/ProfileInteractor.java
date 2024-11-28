package use_case.profile;

import entity.UserFactory;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Profile Interactor.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary profilePresenter;
    private final UserFactory userFactory;

    public ProfileInteractor(ProfileOutputBoundary profilePresenter,
                             UserFactory userFactory) {
        this.profilePresenter = profilePresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToLoggedInView() {
        profilePresenter.switchToLoggedInView();
    }

    @Override
    public void switchToChangePasswordView(LoginOutputData loginOutputData) {
        profilePresenter.switchToChangePasswordView(loginOutputData);
    }

    public void switchToLogInView() {
        profilePresenter.switchToLogInView();
    }

    public void switchToChangeLanguageView() {
        profilePresenter.switchToChangeLanguageView();
    }
}
