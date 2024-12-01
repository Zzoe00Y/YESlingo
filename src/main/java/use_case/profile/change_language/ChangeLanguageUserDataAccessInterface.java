package use_case.profile.change_language;

import entity.User;
import view.ChangeLanguageView;

/**
 * DAO for the Change Language Use Case.
 */
public interface ChangeLanguageUserDataAccessInterface {

    /**
     * Changes the user's default output language.
     * @param username the user's username
     * @param selectedLanguage the user's selected language
     */
    void changeOutputLanguage(String username, ChangeLanguageView.LanguageItem selectedLanguage);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

}
