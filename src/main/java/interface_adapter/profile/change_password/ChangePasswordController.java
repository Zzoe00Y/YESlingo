package interface_adapter.profile.change_password;

import use_case.profile.change_password.ChangePasswordInputBoundary;
import use_case.profile.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary changePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary changePasswordUseCaseInteractor) {
        this.changePasswordUseCaseInteractor = changePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param username the user whose password to change
     * @param newPassword the new password
     * @param repeatPassword the new password
     * @param oldPassword the new password
     */
    public void execute(String username, String newPassword, String repeatPassword, String oldPassword) {
        final ChangePasswordInputData changePasswordInputData =
                new ChangePasswordInputData(username, newPassword, repeatPassword, oldPassword);
        changePasswordUseCaseInteractor.execute(changePasswordInputData);
    }

    /**
     * Executes the "switch to ProfileView" Use Case.
     */
    public void switchToProfileView() {
        changePasswordUseCaseInteractor.switchToProfileView();
    }
}
