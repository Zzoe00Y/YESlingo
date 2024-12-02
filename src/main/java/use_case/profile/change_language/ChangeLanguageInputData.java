package use_case.profile.change_language;

import view.ChangeLanguageView;

/**
 * Input data class for the change language use case.
 */
public class ChangeLanguageInputData {
    private ChangeLanguageView.LanguageItem language;
    private String username;

    /**
     * Creates a new ChangeLanguageInputData instance.
     * @param username the username of the user, must not be null
     * @param language the language selection, must not be null
     */
    public ChangeLanguageInputData(String username,
                                   ChangeLanguageView.LanguageItem language) {
        this.username = username;
        this.language = language;
    }

    /**
     * Gets the selected language.
     * @return the language selection
     */
    public ChangeLanguageView.LanguageItem getLanguage() {
        return language;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
