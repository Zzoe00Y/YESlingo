package data_access;

import use_case.image_translation.ImageTranslationDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryImageTranslationDataAccessObject extends ImageTranslationDataAccessInterface {

    private final Map<String, String[]> translations = new HashMap<>();

    /**
     * Saves a translated image result to the in-memory storage.
     *
     * @param imageId        Unique identifier for the image.
     * @param originalText   The text extracted from the image.
     * @param translatedText The translated version of the text.
     */
    @Override
    public void saveTranslation(String imageId, String originalText, String translatedText) {
        translations.put(imageId, new String[]{originalText, translatedText});
    }

    /**
     * Retrieves a list of previous translations.
     *
     * @return List of previous translations stored in the system.
     */
    @Override
    public List<String> getTranslationHistory() {
        List<String> history = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : translations.entrySet()) {
            String imageId = entry.getKey();
            String originalText = entry.getValue()[0];
            String translatedText = entry.getValue()[1];
            history.add("Image ID: " + imageId + ", Original Text: " + originalText + ", Translated Text: " + translatedText);
        }
        return history;
    }

    /**
     * Deletes a specific translation from the in-memory storage.
     *
     * @param imageId The unique identifier for the translation to be deleted.
     */
    @Override
    public void deleteTranslation(String imageId) {
        translations.remove(imageId);
    }
}