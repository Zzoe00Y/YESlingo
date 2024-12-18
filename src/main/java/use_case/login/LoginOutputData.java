package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final String password;
    private final boolean useCaseFailed;

    public LoginOutputData(String username, String password, boolean useCaseFailed) {
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
}
