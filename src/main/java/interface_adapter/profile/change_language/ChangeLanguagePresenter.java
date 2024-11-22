package interface_adapter.profile.change_language;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.change_language.ChangeLanguageOutputBoundary;

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

    public void switchToProfileView(){
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
