package entity;

/**
 * Entity class representing a translation.
 * Contains the original text, its translation, and the associated language codes.
 * This is an immutable object that represents a complete translation operation.
 */
public class Translation {
    private final String sourceText;
    private final String translatedText;
    private final String sourceLang;
    private final String targetLang;

    /**
     * Constructs a Translation with all necessary information.
     *
     * @param sourceText the original text that was translated
     * @param translatedText the resulting translated text
     * @param sourceLang the language code of the source text (e.g., "en" for English)
     * @param targetLang the language code of the translated text (e.g., "es" for Spanish)
     */
    public Translation(String sourceText, String translatedText,
                       String sourceLang, String targetLang) {
        this.sourceText = sourceText;
        this.translatedText = translatedText;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }

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
