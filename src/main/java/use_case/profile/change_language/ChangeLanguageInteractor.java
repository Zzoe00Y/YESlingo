package use_case.profile.change_language;

import entity.User;
import entity.UserFactory;
import use_case.profile.change_password.ChangePasswordOutputBoundary;
import view.ChangeLanguageView;

/**
 * The Change Language Interactor.
 */
public class ChangeLanguageInteractor implements ChangeLanguageInputBoundary {
    private final ChangeLanguageOutputBoundary changeLanguagePresenter;
    private final ChangeLanguageUserDataAccessInterface changeLanguageUserDataAccessObject;

    public ChangeLanguageInteractor(ChangeLanguageOutputBoundary changeLanguagePresenter,
                                    ChangeLanguageUserDataAccessInterface changeLanguageUserDataAccessObject) {
        this.changeLanguagePresenter = changeLanguagePresenter;
        this.changeLanguageUserDataAccessObject = changeLanguageUserDataAccessObject;
    }

    @Override
    public void execute(ChangeLanguageInputData changeLanguageInputData) {
        String username = changeLanguageInputData.getUsername();
        //User user = changeLanguageUserDataAccessObject.get(username);
        ChangeLanguageView.LanguageItem selectedLanguage = changeLanguageInputData.getLanguage();

        changeLanguageUserDataAccessObject.changeOutputLanguage(username, selectedLanguage);

        final ChangeLanguageOutputData changeLanguageOutputData = new ChangeLanguageOutputData(username, selectedLanguage);
        changeLanguagePresenter.prepareSuccessView(changeLanguageOutputData);
    }

    @Override
    public void switchToProfileView() {
        changeLanguagePresenter.switchToProfileView();
    }
}
