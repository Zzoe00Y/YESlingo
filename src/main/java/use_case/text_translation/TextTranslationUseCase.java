package use_case.text_translation;

import entity.Translation;

/**
 * Interactor for the Text Translation Use Case.
 * Responsible for translating text into the specified target language using the provided gateway.
 */
public class TextTranslationUseCase {

    private final TranslationGateway translationGateway;

    /**
     * Constructs a TextTranslationUseCase with the given translation gateway.
     *
     * @param translationGateway The gateway used for translating text.
     */
    public TextTranslationUseCase(TranslationGateway translationGateway) {
        this.translationGateway = translationGateway;
    }

    /**
     * Translates the given text into the specified target language.
     *
     * @param sourceText  The text to be translated.
     * @param targetLang  The target language for the translation (e.g., "es").
     * @return The translated text.
     * @throws Exception If an error occurs during the translation process.
     */
    public Translation translate(String sourceText, String targetLang) throws RuntimeException {
        if (sourceText == null || sourceText.trim().isEmpty()) {
            throw new IllegalArgumentException("Source text cannot be null or empty.");
        }
        if (targetLang == null || targetLang.trim().isEmpty()) {
            throw new IllegalArgumentException("Target language cannot be null or empty.");
        }

        // Use "auto" as the source language for automatic detection
        return translationGateway.translateText(sourceText, "auto", targetLang);
    }
}
