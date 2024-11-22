package use_case.text_translation;

public class TextTranslationOutputData {
    private final String sourceText;
    private final String translatedText;
    private final String sourceLang;
    private final String targetLang;

    public TextTranslationOutputData(String sourceText, String translatedText,
                                     String sourceLang, String targetLang) {
        this.sourceText = sourceText;
        this.translatedText = translatedText;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
    }

    // Getters
    public String getSourceText() { return sourceText; }
    public String getTranslatedText() { return translatedText; }
    public String getSourceLang() { return sourceLang; }
    public String getTargetLang() { return targetLang; }
}

