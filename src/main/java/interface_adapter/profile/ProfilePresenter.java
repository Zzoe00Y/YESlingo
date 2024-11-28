package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.change_language.ChangeLanguageViewModel;
import interface_adapter.profile.change_password.ChangePasswordState;
import interface_adapter.profile.change_password.ChangePasswordViewModel;
import use_case.login.LoginOutputData;
import use_case.profile.ProfileOutputBoundary;

public class ProfilePresenter implements ProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ProfileViewModel profileViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoginViewModel loginViewModel;
    private final ChangeLanguageViewModel changeLanguageViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            LoggedInViewModel loggedInViewModel,
                            ProfileViewModel profileViewModel,
                            ChangePasswordViewModel changePasswordViewModel,
                            LoginViewModel loginViewModel,
                            ChangeLanguageViewModel changeLanguageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.profileViewModel = profileViewModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.loginViewModel = loginViewModel;
        this.changeLanguageViewModel = changeLanguageViewModel;
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView(LoginOutputData loginOutputData) {
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setOldPassword(loginOutputData.getPassword());

        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLogInView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChangeLanguageView() {
        viewManagerModel.setState(changeLanguageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
