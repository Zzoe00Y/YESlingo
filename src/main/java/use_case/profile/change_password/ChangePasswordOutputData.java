package use_case.profile.change_password;

/**
 * Output Data for the Change Password Use Case.
 * Contains the results of a password change operation including success/failure status.
 */
public class ChangePasswordOutputData {

    private final String username;
    private final String password;
    private final boolean useCaseFailed;

    /**
     * Creates a new ChangePasswordOutputData instance.
     * @param username the username of the user whose password was changed, must not be null
     * @param password the new password that was set, must not be null
     * @param useCaseFailed indicates whether the password change operation failed
     */
    public ChangePasswordOutputData(String username, String password, boolean useCaseFailed) {
        this.username = username;
        this.password = password;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username associated with this password change.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the new password that was set.
     * @return the new password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the use case operation failed.
     * @return true if the password change failed, false if it succeeded
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
