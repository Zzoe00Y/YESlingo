package interface_adapter.profile.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.change_password.ChangePasswordOutputBoundary;
import use_case.profile.change_password.ChangePasswordOutputData;
import view.ViewManager;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final ProfileViewModel profileViewModel;

    public ChangePasswordPresenter(ViewManagerModel viewManagerModel,
                                   ChangePasswordViewModel changePasswordViewModel,
                                   ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData response) {
        final ProfileState profileState = profileViewModel.getState();
        //profileState.setUsername(response.getUsername());
        profileState.setPassword(response.getPassword());
        this.profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();

        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setOldPasswordError(error);
        changePasswordViewModel.firePropertyChanged();
    }

    public void switchToProfileView(){
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
