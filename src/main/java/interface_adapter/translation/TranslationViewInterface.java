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
     *
     * @param error The error message to display.
     */
    void displayError(String error);

    void displayRecognizedText(String recognizedText);
}
