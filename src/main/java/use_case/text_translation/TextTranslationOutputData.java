package use_case.text_translation;

/**
 * Data class containing output data from text translation operations.
 */
public class TextTranslationOutputData {
    private final String sourceText;
    private final String translatedText;
    private final String sourceLang;
    private final String targetLang;

    /**
     * Creates a new TextTranslationOutputData instance with the specified parameters.
     *
     * @param sourceText The original text that was translated
     * @param translatedText The translated text
     * @param sourceLang The language code of the source text
     * @param targetLang The language code of the translated text
     * @throws IllegalArgumentException if any parameter is null
     */
    public TextTranslationOutputData(String sourceText, String translatedText,
                                     String sourceLang, String targetLang) {
        if (sourceText == null) {
            throw new IllegalArgumentException("Source text cannot be null");
        }
        if (translatedText == null) {
            throw new IllegalArgumentException("Translated text cannot be null");
        }
        if (sourceLang == null) {
            throw new IllegalArgumentException("Source language cannot be null");
        }
        if (targetLang == null) {
            throw new IllegalArgumentException("Target language cannot be null");
        }

        this.sourceText = sourceText;
        this.translatedText = translatedText;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }

    // Getters
    public String getSourceText() {
        return sourceText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }
}

