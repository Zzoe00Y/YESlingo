package use_case.profile;

/**
 * The Profile Interactor.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary profilePresenter;

    public ProfileInteractor(ProfileOutputBoundary profilePresenter) {
        this.profilePresenter = profilePresenter;
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
