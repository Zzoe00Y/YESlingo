package interface_adapter.profile.change_language;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.change_language.ChangeLanguageOutputBoundary;
import use_case.profile.change_language.ChangeLanguageOutputData;

public class ChangeLanguagePresenter implements ChangeLanguageOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ChangeLanguageViewModel changeLanguageViewModel;
    private final ProfileViewModel profileViewModel;

    public ChangeLanguagePresenter(ViewManagerModel viewManagerModel,
                                   ChangeLanguageViewModel changeLanguageViewModel,
                                   ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changeLanguageViewModel = changeLanguageViewModel;
        this.profileViewModel = profileViewModel;
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

        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        //TODO textTranslation

    }
    public void switchToProfileView(){
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
