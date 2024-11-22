package external_services;

import entity.Translation;
import use_case.text_translation.TranslationGateway;

/**
 * Service that adapts MyMemoryGateway to the TranslationGateway interface.
 */
public class TextToTextTranslationService implements TranslationGateway {
    private final MyMemoryGateway gateway;

    public TextToTextTranslationService(MyMemoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Translation translateText(String sourceText, String sourceLang, String targetLang) {
        try {
            return gateway.translateText(sourceText, sourceLang, targetLang);
        } catch (RuntimeException e) {
            throw new RuntimeException("Translation failed: " + e.getMessage(), e);
        }
    }
}
