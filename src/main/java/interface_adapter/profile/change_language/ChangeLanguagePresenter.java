package interface_adapter.profile.change_language;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInState;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.change_language.ChangeLanguageOutputBoundary;
import use_case.profile.change_language.ChangeLanguageOutputData;

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

    /**
     * Prepares the success view for the Change Language Use Case.
     * @param response the output data
     */
    public void prepareSuccessView(ChangeLanguageOutputData response) {
        final ProfileState profileState = profileViewModel.getState();
        final String language = response.getLanguage().getDisplayName();
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

        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to the profile view.
     */
    public void switchToProfileView() {
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
