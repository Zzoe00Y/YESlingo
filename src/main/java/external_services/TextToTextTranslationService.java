package external_services;

/**
 * Service that adapts LibreTranslateGateway to the TranslationService interface.
 */
public class TextToTextTranslationService implements TranslationService {
    private final MyMemoryGateway gateway;

    public TextToTextTranslationService(MyMemoryGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public String translate(String text, String targetLanguage) throws Exception {
        try {
            // Use "auto" as source language for automatic language detection
            return gateway.translateText(text, "auto", targetLanguage)
                    .getTranslatedText();
        } catch (RuntimeException e) {
            throw new Exception("Translation failed: " + e.getMessage());
        }
    }
}