package external_services;

import use_case.text_translation.TranslationGateway;

/**
 * Adapter to convert TranslationGateway to TranslationService
 */
public class TranslationServiceAdapter implements TranslationService {
    private final TranslationGateway translationGateway;

    public TranslationServiceAdapter(TranslationGateway translationGateway) {
        this.translationGateway = translationGateway;
    }

    @Override
    public String translate(String text, String targetLanguage) throws Exception {
        try {
            return translationGateway.translateText(text, "auto", targetLanguage)
                    .getTranslatedText();
        } catch (RuntimeException e) {
            throw new Exception("Translation failed: " + e.getMessage());
        }
    }
}