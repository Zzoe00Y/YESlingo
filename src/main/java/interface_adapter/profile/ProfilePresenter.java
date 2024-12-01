package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.change_language.ChangeLanguageViewModel;
import interface_adapter.profile.change_password.ChangePasswordViewModel;
import use_case.profile.ProfileOutputBoundary;

/**
 * The Presenter for the Profile Use Case.
 */
public class ProfilePresenter implements ProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoginViewModel loginViewModel;
    private final ChangeLanguageViewModel changeLanguageViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            LoggedInViewModel loggedInViewModel,
                            ChangePasswordViewModel changePasswordViewModel,
                            LoginViewModel loginViewModel,
                            ChangeLanguageViewModel changeLanguageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
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
    public void switchToChangePasswordView() {
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
