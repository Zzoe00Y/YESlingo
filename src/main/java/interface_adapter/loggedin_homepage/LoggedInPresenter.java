package interface_adapter.loggedin_homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.loggedin.LoggedInOutputBoundary;
import view.LoggedInView;

/**
 * The Presenter for the Loggedin Use Case.
 */
public class LoggedInPresenter implements LoggedInOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.profileViewModel = profileViewModel;
    }

    public void switchToProfileView() {
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
