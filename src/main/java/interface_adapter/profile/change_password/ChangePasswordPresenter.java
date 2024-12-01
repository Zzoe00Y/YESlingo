package interface_adapter.profile.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.change_password.ChangePasswordOutputBoundary;
import use_case.profile.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final ProfileViewModel profileViewModel;
    private final LoginViewModel loginViewModel;

    public ChangePasswordPresenter(ViewManagerModel viewManagerModel,
                                   ChangePasswordViewModel changePasswordViewModel,
                                   ProfileViewModel profileViewModel,
                                   LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.profileViewModel = profileViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData response) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setPassword(response.getPassword());
        this.profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();

        final LoginState loginState = loginViewModel.getState();
        loginState.setPassword(response.getPassword());
        this.loginViewModel.setState(loginState);

        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setOldPassword(response.getPassword());
        this.changePasswordViewModel.setState(changePasswordState);

        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setOldPasswordError(error);
        changePasswordViewModel.firePropertyChanged();
    }

    /**
     * Switches to the profile view.
     */
    public void switchToProfileView() {
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
