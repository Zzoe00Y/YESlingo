package use_case.profile.change_password;

import entity.User;
import entity.UserFactory;

import javax.swing.*;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordOutputBoundary changePasswordPresenter;
    private final ChangePasswordUserDataAccessInterface changePasswordUserDataAccessObject;

    public ChangePasswordInteractor(ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    ChangePasswordUserDataAccessInterface changePasswordUserDataAccessInterface) {
        this.changePasswordPresenter = changePasswordOutputBoundary;
        this.changePasswordUserDataAccessObject = changePasswordUserDataAccessInterface;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {

        User user = changePasswordUserDataAccessObject.get(changePasswordInputData.getUsername());

        if (!changePasswordInputData.getOldPassword().equals(user.getPassword())) {
            changePasswordPresenter.prepareFailView("Old password does not match.");
        }
        else if (!changePasswordInputData.getNewPassword().equals(changePasswordInputData.getRepeatPassword())) {
            changePasswordPresenter.prepareFailView("New passwords don't match.");
        }
        else {
            String newPassword = changePasswordInputData.getNewPassword();
            changePasswordUserDataAccessObject.changePassword(user, newPassword);

            final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(), newPassword, false);
            changePasswordPresenter.prepareSuccessView(changePasswordOutputData);
        }
    }

    @Override
    public void switchToProfileView() {
        changePasswordPresenter.switchToProfileView();

    }
}
