package use_case.text_translation;

/**
 * Output boundary interface for the Text Translation use case.
 * Defines methods for preparing success and failure responses.
 */
public interface TextTranslationOutputBoundary {
    /**
     * Prepares the success response for a successful translation operation.
     *
     * @param translation the response model containing the successful translation results
     * @return TextTranslationResponseModel formatted for presentation
     */
    TextTranslationResponseModel prepareSuccessView(TextTranslationResponseModel translation);

    /**
     * Prepares the failure response when a translation operation fails.
     *
     * @param error the error message describing what went wrong
     * @return TextTranslationResponseModel containing the error information
     */
    TextTranslationResponseModel prepareFailView(String error);
}