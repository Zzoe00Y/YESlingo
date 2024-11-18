package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.change_password.ChangePasswordViewModel;
import use_case.profile.ProfileOutputBoundary;

public class ProfilePresenter implements ProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ProfileViewModel profileViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoginViewModel loginViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            LoggedInViewModel loggedInViewModel,
                            ProfileViewModel profileViewModel,
                            ChangePasswordViewModel changePasswordViewModel,
                            LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.profileViewModel = profileViewModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView() {
        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLogInView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
