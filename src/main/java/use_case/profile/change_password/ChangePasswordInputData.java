package use_case.profile.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String username;
    private final String newPassword;
    private final String repeatPassword;
    private final String oldPassword;

    public ChangePasswordInputData(String username, String newPassword, String repeatPassword, String oldPassword) {
        this.username = username;
        this.newPassword = newPassword;
        this.repeatPassword = repeatPassword;
        this.oldPassword = oldPassword;
    }

    String getUsername() {
        return username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
