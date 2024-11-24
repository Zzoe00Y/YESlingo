package use_case.profile.change_language;

import entity.UserFactory;
import use_case.profile.change_password.ChangePasswordOutputBoundary;

/**
 * The Change Language Interactor.
 */
public class ChangeLanguageInteractor implements ChangeLanguageInputBoundary {
    private final ChangeLanguageOutputBoundary changeLanguagePresenter;
    private final UserFactory userFactory;

    public ChangeLanguageInteractor(ChangeLanguageOutputBoundary changeLanguagePresenter, UserFactory userFactory) {
        this.changeLanguagePresenter = changeLanguagePresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToProfileView() {
        changeLanguagePresenter.switchToProfileView();
    }
}
