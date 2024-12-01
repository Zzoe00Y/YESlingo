package interface_adapter.text_translation;

import use_case.text_translation.TextTranslationInputBoundary;
import use_case.text_translation.TextTranslationInputData;

/**
 * Controller for handling text translation requests.
 * Converts UI input into use case request models and coordinates with the interactor.
 */
public class TextTranslationController {
    private final TextTranslationInputBoundary translationInteractor;

    /**
     * Constructs a TextTranslationController with the specified interactor.
     *
     * @param translationInteractor the use case interactor that handles translation operations
     */
    public TextTranslationController(TextTranslationInputBoundary translationInteractor) {
        this.translationInteractor = translationInteractor;
    }

    /**
     * Initiates a translation request with the provided parameters.
     * Creates a request model and delegates to the interactor for processing.
     *
     * @param text the text to be translated
     * @param sourceLang the language code of the source text (e.g., "en" for English)
     * @param targetLang the language code of the desired translation (e.g., "es" for Spanish)
     * @param username the username of the currently logged in user
     */
    public void translate(String text, String sourceLang, String targetLang, String username) {
        System.out.println("Controller received translation request");
        TextTranslationInputData requestModel = new TextTranslationInputData(
                text,
                sourceLang,
                targetLang,
                username
        );
        translationInteractor.translate(requestModel);
    }
}
