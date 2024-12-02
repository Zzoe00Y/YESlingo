package interface_adapter.profile;

/**
 * The state for the Profile View Model.
 */
public class ProfileState {
    private String username = "";
    private String password = "";
    private String language = "English";

    public ProfileState(ProfileState copy) {
        this.username = copy.username;
        this.password = copy.password;
    }

    public ProfileState() {
    }

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
