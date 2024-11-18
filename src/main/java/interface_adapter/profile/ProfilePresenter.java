package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import use_case.profile.ProfileOutputBoundary;

public class ProfilePresenter implements ProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ProfileViewModel profileViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            LoggedInViewModel loggedInViewModel,
                            ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.profileViewModel = new ProfileViewModel();
    }


}
