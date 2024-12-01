package use_case.text_translation;

public class TextTranslationInputData {
    private final String sourceText;
    private final String sourceLang;
    private final String targetLang;
    private final String username;

    public TextTranslationInputData(String sourceText, String sourceLang, String targetLang, String username) {
        this.sourceText = sourceText;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
        this.username = username;
    }

    // Getters
    public String getSourceText() { return sourceText; }
    public String getSourceLang() { return sourceLang; }
    public String getTargetLang() { return targetLang; }

    public String getUsername() {
        return username;
    }
}
