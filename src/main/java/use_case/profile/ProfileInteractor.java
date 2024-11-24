package use_case.profile;

import entity.UserFactory;

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
    public void switchToChangePasswordView() {
        profilePresenter.switchToChangePasswordView();
    }

    public void switchToLogInView() {
        profilePresenter.switchToLogInView();
    }

    public void switchToChangeLanguageView() {
        profilePresenter.switchToChangeLanguageView();
    }
}
