package interface_adapter.translation;

/**
 * Interface for displaying translation results and errors in the LoggedInView.
 */
public interface TranslationViewInterface {

    /**
     * Displays the translated text.
     *
     * @param translatedText The translated text to display.
     */
    void displayTranslation(String translatedText);

    /**
     * Displays an error message.
     * @param error The error message to display.
     */
    void displayError(String error);

    /**
     * Displays an recognized text.
     * @param recognizedText the recognized text
     */
    void displayRecognizedText(String recognizedText);
}
