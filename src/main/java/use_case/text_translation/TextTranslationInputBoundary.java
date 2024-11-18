package use_case.text_translation;

/**
 * Input boundary interface for the Text Translation use case.
 * Defines the contract for handling text translation operations.
 */
public interface TextTranslationInputBoundary {
    /**
     * Translates text based on the provided request model.
     *
     * @param request the request model containing text and translation parameters
     * @return TextTranslationResponseModel containing the translated text results
     */
    TextTranslationResponseModel translate(TextTranslationRequestModel request);
}