package use_case.text_translation;

/**
 * Input boundary interface for the Text Translation use case.
 * Defines the contract for handling text translation operations.
 */
public interface TextTranslationInputBoundary {
    /**
     * Translates text according to the parameters specified in the input data.
     *
     * @param inputData The data containing text to be translated and translation parameters
     */
    void translate(TextTranslationInputData inputData);
}
