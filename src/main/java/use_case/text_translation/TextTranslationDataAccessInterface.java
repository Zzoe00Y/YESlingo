package use_case.text_translation;

import entity.Translation;
import entity.User;

/**
 * Gateway interface for text translation operations.
 * Defines the contract for interacting with translation services or repositories.
 */
public interface TextTranslationDataAccessInterface {
    /**
     * Translates text from one language to another.
     *
     * @param sourceText the text to be translated
     * @param sourceLang the language code of the source text (e.g., "en" for English)
     * @param targetLang the language code of the desired translation (e.g., "es" for Spanish)
     * @return Translation entity containing the original and translated text
     * @throws RuntimeException if the translation operation fails
     */
    Translation translateText(String sourceText, String sourceLang, String targetLang);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Return the user.
     * @param username the username of the user
     * @return the User object
     */
    User get(String username);
}
