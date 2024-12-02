package use_case.profile.change_password;

/**
 * Output Data for the Change Password Use Case.
 */
public class ChangePasswordOutputData {

    private final String username;
    private final String password;
    private final boolean useCaseFailed;

    public ChangePasswordOutputData(String username, String password, boolean useCaseFailed) {
        this.username = username;
        this.password = password;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
