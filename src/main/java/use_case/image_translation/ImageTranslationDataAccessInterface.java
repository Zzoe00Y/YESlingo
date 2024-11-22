package use_case.image_translation;

import java.util.List;

public interface ImageTranslationDataAccessInterface {

    /**
     * Save the translation of an image with its corresponding original and translated text.
     *
     * @param imageId        the unique ID of the image
     * @param originalText   the original extracted text
     * @param translatedText the translated text
     */
    void saveTranslation(String imageId, String originalText, String translatedText);

    /**
     * Retrieve the history of all translations.
     *
     * @return a list of strings representing the translation history
     */
    List<String> getTranslationHistory();

    /**
     * Delete a translation record based on the image ID.
     *
     * @param imageId the unique ID of the image whose translation should be deleted
     */
    void deleteTranslation(String imageId);
}
