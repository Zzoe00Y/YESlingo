package use_case.profile.change_password;

/**
 * The input data for the Change Password Use Case.
 * Contains all necessary information for changing a user's password.
 */
public class ChangePasswordInputData {

    private final String username;
    private final String newPassword;
    private final String repeatPassword;
    private final String oldPassword;

    /**
     * Creates a new ChangePasswordInputData instance.
     * @param username the username of the user changing their password, must not be null
     * @param newPassword the new password to set, must not be null
     * @param repeatPassword the repeated new password for confirmation, must not be null
     * @param oldPassword the current password for verification, must not be null
     */
    public ChangePasswordInputData(String username, String newPassword, String repeatPassword, String oldPassword) {
        this.username = username;
        this.newPassword = newPassword;
        this.repeatPassword = repeatPassword;
        this.oldPassword = oldPassword;
    }

    /**
     * Gets the username of the user.
     * @return the username
     */
    String getUsername() {
        return username;
    }

    /**
     * Gets the new password.
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Gets the repeated password for confirmation.
     * @return the repeated password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Gets the old password for verification.
     * @return the old password
     */
    public String getOldPassword() {
        return oldPassword;
    }
}
