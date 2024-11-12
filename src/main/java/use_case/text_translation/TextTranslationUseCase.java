package use_case.text_translation;

import external_services.TranslationService;

/**
 * Use case for translating text into a target language.
 */
public class TextTranslationUseCase {

    private final TranslationService translationService;

    /**
     * Constructs a TextTranslationUseCase with the provided translation service.
     *
     * @param translationService the service used to perform the translation
     */
    public TextTranslationUseCase(TranslationService translationService) {
        this.translationService = translationService;
    }

    /**
     * Translates the given text to the specified target language.
     *
     * @param text the text to translate
     * @param targetLanguage the language to translate the text into
     * @return the translated text
     * @throws Exception if an error occurs during translation
     */
    public String translate(String text, String targetLanguage) throws Exception {
        return translationService.translate(text, targetLanguage);
    }
}
