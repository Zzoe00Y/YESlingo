package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInState;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.change_language.ChangeLanguageState;
import interface_adapter.profile.change_language.ChangeLanguageViewModel;
import interface_adapter.profile.change_password.ChangePasswordPresenter;
import interface_adapter.profile.change_password.ChangePasswordState;
import interface_adapter.profile.change_password.ChangePasswordViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.ChangePasswordView;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final ProfileViewModel profileViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final ChangeLanguageViewModel changeLanguageViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          SignupViewModel signupViewModel,
                          ProfileViewModel profileViewModel,
                          ChangePasswordViewModel changePasswordViewModel,
                          ChangeLanguageViewModel changeLanguageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.profileViewModel = profileViewModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.changeLanguageViewModel = changeLanguageViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(response.getUsername());
        profileState.setPassword(response.getPassword());
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setUsername(response.getUsername());
        changePasswordState.setOldPassword(response.getPassword());
        this.changePasswordViewModel.setState(changePasswordState);

        final ChangeLanguageState changeLanguageState = changeLanguageViewModel.getState();
        changeLanguageState.setUsername(response.getUsername());
        this.changeLanguageViewModel.setState(changeLanguageState);

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
