package use_case.loggedin;

/**
 * The Input Data for the Loggedin Use Case.
 */
public class LoggedInInputData {

    private final String username;
    private final String password;

    public LoggedInInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}

