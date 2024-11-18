package use_case.loggedin;

import entity.UserFactory;

/**
 * The LoggedIn Interactor.
 */
public class LoggedInInteractor implements LoggedInInputBoundary{
    private final LoggedInUserDataAccessInterface loggedInUserDataAccessObject;
    private final LoggedInOutputBoundary loggedInPresenter;
    private final UserFactory userFactory;

    public LoggedInInteractor(LoggedInUserDataAccessInterface loggedInUserDataAccessObject,
                              LoggedInOutputBoundary loggedInOutputBoundary,
                              UserFactory factory) {
        this.loggedInUserDataAccessObject = loggedInUserDataAccessObject;
        this.loggedInPresenter = loggedInOutputBoundary;
        this.userFactory = factory;
    }

    @Override
    public void switchToProfileView() {
        loggedInPresenter.switchToProfileView();
    }
}
