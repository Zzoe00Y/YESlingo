package interface_adapter.profile.change_language;

/**
 * Represents the state of the change language interface.
 * Stores the current user's username and selected language.
 */
public class ChangeLanguageState {
    private String username = "";
    private String language = "";

    /**
     * Gets the username of the current user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the current user.
     *
     * @param username the username to set, must not be null
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the currently selected language.
     *
     * @return the language code
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the selected language.
     *
     * @param language the language code to set, must not be null
     */
    public void setLanguage(String language) {
        this.language = language;
    }
}
