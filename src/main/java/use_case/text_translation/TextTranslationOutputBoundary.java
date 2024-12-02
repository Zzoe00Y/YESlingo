package use_case.text_translation;

/**
 * Output boundary interface for the Text Translation use case.
 * Defines methods for preparing success and failure responses.
 */
public interface TextTranslationOutputBoundary {
    /**
     * Prepares the view for a successful translation operation.
     *
     * @param outputData The data containing the translation results
     */
    void prepareSuccessView(TextTranslationOutputData outputData);

    /**
     * Prepares the view for a failed translation operation.
     *
     * @param error The error message describing why the translation failed
     */
    void prepareFailView(String error);
}
