package use_case.text_translation;

/**
 * Output boundary interface for the Text Translation use case.
 * Defines methods for preparing success and failure responses.
 */
public interface TextTranslationOutputBoundary {
    void prepareSuccessView(TextTranslationOutputData outputData);

    void prepareFailView(String error);
}
