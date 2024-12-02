package use_case.text_translation;

/**
 * Input boundary interface for the Text Translation use case.
 * Defines the contract for handling text translation operations.
 */
public interface TextTranslationInputBoundary {

    /**
     * Translates.
     * @param inputData the input data
     */
    void translate(TextTranslationInputData inputData);
}
