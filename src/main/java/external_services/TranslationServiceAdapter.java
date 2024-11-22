package external_services;

import use_case.text_translation.TextTranslationDataAccessInterface;

/**
 * Adapter to convert TranslationGateway to TranslationService
 */
public class TranslationServiceAdapter implements TranslationService {
    private final TextTranslationDataAccessInterface textTranslationDataAccessInterface;

    public TranslationServiceAdapter(TextTranslationDataAccessInterface textTranslationDataAccessInterface) {
        this.textTranslationDataAccessInterface = textTranslationDataAccessInterface;
    }

    @Override
    public String translate(String text, String targetLanguage) throws Exception {
        try {
            return textTranslationDataAccessInterface.translateText(text, "auto", targetLanguage)
                    .getTranslatedText();
        } catch (RuntimeException e) {
            throw new Exception("Translation failed: " + e.getMessage());
        }
    }
}