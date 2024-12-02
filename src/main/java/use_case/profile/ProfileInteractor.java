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

    /**
     * Executes the switch to login view Use Case.
     */
    public void switchToLogInView() {
        profilePresenter.switchToLogInView();
    }

    /**
     * Executes the switch to change language view Use Case.
     */
    public void switchToChangeLanguageView() {
        profilePresenter.switchToChangeLanguageView();
    }
}
