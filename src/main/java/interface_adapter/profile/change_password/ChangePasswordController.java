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
     * @param password the new password
     * @param username the user whose password to change
     */
    public void execute(String password, String username) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password);
        changePasswordUseCaseInteractor.execute(changePasswordInputData);
    }

    /**
     * Executes the "switch to ProfileView" Use Case.
     */
    public void switchToProfileView() {
        changePasswordUseCaseInteractor.switchToProfileView();
    }
}
