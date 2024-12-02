package use_case.text_translation;

/**
 * Data class containing input parameters for text translation operations.
 */
public class TextTranslationInputData {
    private final String sourceText;
    private final String sourceLang;
    private final String targetLang;
    private final String username;

    /**
     * Creates a new TextTranslationInputData instance with the specified parameters.
     *
     * @param sourceText The text to be translated
     * @param sourceLang The language code of the source text
     * @param targetLang The language code of the desired translation
     * @param username The username of the user requesting the translation
     * @throws IllegalArgumentException if any parameter is null
     */
    public TextTranslationInputData(String sourceText, String sourceLang, String targetLang, String username) {
        if (sourceText == null) {
            throw new IllegalArgumentException("Source text cannot be null");
        }
        if (sourceLang == null) {
            throw new IllegalArgumentException("Source language cannot be null");
        }
        if (targetLang == null) {
            throw new IllegalArgumentException("Target language cannot be null");
        }
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        this.sourceText = sourceText;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
        this.username = username;
    }

    // Getters
    public String getSourceText() {
        return sourceText;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public String getUsername() {
        return username;
    }
}
