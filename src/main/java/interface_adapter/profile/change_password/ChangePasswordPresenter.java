package interface_adapter.profile.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
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
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully..
        changePasswordViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    public void switchToProfileView(){
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
