package use_case.profile.change_password;

import entity.User;
import entity.UserFactory;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordOutputBoundary changePasswordPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.changePasswordPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        final User user = userFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword());
        //changePasswordDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                                                                                  false);
        changePasswordPresenter.prepareSuccessView(changePasswordOutputData);
    }

    @Override
    public void switchToProfileView() {
        changePasswordPresenter.switchToProfileView();
    }
}
