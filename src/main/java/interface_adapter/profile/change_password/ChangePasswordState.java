package interface_adapter.profile.change_password;

/**
 * The state for the Change Password View Model.
 */
public class ChangePasswordState {
    private String username = "";
    private String newPassword = "";
    private String repeatPassword = "";
    private String oldPassword = "";
    private String oldPasswordError;
    private String repeatPasswordError;

//    public ChangePasswordState(ChangePasswordState copy) {
//        this.username = copy.username;
//        this.newPassword = copy.newPassword;
//        this.repeatPassword = copy.repeatPassword;
//        this.oldPassword = copy.oldPassword;
//        this.oldPasswordError = copy.oldPasswordError;
//        this.repeatPasswordError = copy.repeatPasswordError;
//    }
//
//    public ChangePasswordState() {
//
//    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getOldPassword() { return oldPassword; }

    public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getOldPasswordError() { return oldPasswordError; }

    public void setOldPasswordError(String oldPasswordError) {
        this.oldPasswordError = oldPasswordError;
    }

    public String getRepeatPasswordError() { return repeatPasswordError; }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

}
