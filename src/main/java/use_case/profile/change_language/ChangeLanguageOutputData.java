package use_case.profile.change_language;

import view.ChangeLanguageView;

/**
 * Output data class for the change language use case.
 * Contains the results of a language change operation.
 */
public class ChangeLanguageOutputData {

    private String username;
    private ChangeLanguageView.LanguageItem language;

    /**
     * Creates a new ChangeLanguageOutputData instance.
     * @param username the username of the user, must not be null
     * @param language the new language setting, must not be null
     */
    public ChangeLanguageOutputData(String username, ChangeLanguageView.LanguageItem language) {
        this.username = username;
        this.language = language;
    }

    /**
     * Gets the username associated with this language change.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the new language setting.
     * @return the language selection
     */
    public ChangeLanguageView.LanguageItem getLanguage() {
        return language;
    }
}
