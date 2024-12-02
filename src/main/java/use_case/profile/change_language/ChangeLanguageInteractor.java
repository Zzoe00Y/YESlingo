package use_case.profile.change_language;

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
        final String username = changeLanguageInputData.getUsername();
        final ChangeLanguageView.LanguageItem selectedLanguage = changeLanguageInputData.getLanguage();

        changeLanguageUserDataAccessObject.changeOutputLanguage(username, selectedLanguage);

        final ChangeLanguageOutputData changeLanguageOutputData =
                new ChangeLanguageOutputData(username, selectedLanguage);
        changeLanguagePresenter.prepareSuccessView(changeLanguageOutputData);
    }

    @Override
    public void switchToProfileView() {
        changeLanguagePresenter.switchToProfileView();
    }
}
