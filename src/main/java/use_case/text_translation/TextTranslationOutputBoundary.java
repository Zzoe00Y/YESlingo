package use_case.text_translation;

/**
 * Output boundary interface for the Text Translation use case.
 * Defines methods for preparing success and failure responses.
 */
public interface TextTranslationOutputBoundary {

    /**
     * Prepares success view.
     * @param outputData the outputData
     */
    void prepareSuccessView(TextTranslationOutputData outputData);

    /**
     * Prepares fail view.
     * @param error the outputData
     */
    void prepareFailView(String error);
}
