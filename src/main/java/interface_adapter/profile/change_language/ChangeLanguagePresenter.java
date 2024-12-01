package interface_adapter.profile.change_language;

import external_services.TextTranslationService;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInState;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.change_language.ChangeLanguageOutputBoundary;
import use_case.profile.change_language.ChangeLanguageOutputData;
import view.LoggedInView;

/**
 * The Presenter for the Change Language Use Case.
 */
public class ChangeLanguagePresenter implements ChangeLanguageOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ChangeLanguageViewModel changeLanguageViewModel;
    private final ProfileViewModel profileViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public ChangeLanguagePresenter(ViewManagerModel viewManagerModel,
                                   ChangeLanguageViewModel changeLanguageViewModel,
                                   ProfileViewModel profileViewModel,
                                   LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changeLanguageViewModel = changeLanguageViewModel;
        this.profileViewModel = profileViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    public void prepareSuccessView(ChangeLanguageOutputData response) {
        final ProfileState profileState = profileViewModel.getState();
        String language = response.getLanguage().getDisplayName();
        profileState.setLanguage(language);
        this.profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();

        final ChangeLanguageState changeLanguageState = changeLanguageViewModel.getState();
        changeLanguageState.setLanguage(language);
        this.changeLanguageViewModel.setState(changeLanguageState);

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setLanguage(language);
        this.loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();

//        LoggedInView loggedInView = loggedInView.setDefaultLanguage(response.getLanguage());
//        loggedInView.propertyChange(response.getLanguage());
//
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToProfileView(){
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
