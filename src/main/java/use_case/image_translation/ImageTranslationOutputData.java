package use_case.image_translation;

/**
 * Data class containing the output data for a successful image translation.
 */
public class ImageTranslationOutputData {
    private final String translatedText;

    public ImageTranslationOutputData(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
