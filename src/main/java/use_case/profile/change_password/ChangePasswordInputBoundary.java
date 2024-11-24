package use_case.profile.change_password;

/**
 * The Change Password Use Case.
 */
public interface ChangePasswordInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param changePasswordInputData the input data for this use case
     */
    void execute(ChangePasswordInputData changePasswordInputData);

    /**
     * Executes the switch to profile view use case.
     */
    void switchToProfileView();
}
