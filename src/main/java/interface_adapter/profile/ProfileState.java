package interface_adapter.profile;

/**
 * The state for the Profile View Model.
 */
public class ProfileState {
    private String username = "";
    private String password = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
