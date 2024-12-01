package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String username;
    private boolean useCaseFailed = false;

    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
}
