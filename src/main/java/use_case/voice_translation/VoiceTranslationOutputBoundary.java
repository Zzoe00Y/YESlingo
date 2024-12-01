package use_case.voice_translation;

/**
 * Output boundary for the voice translation use case.
 * Defines methods for preparing success and failure views based on the results of the voice translation process.
 */
public interface VoiceTranslationOutputBoundary {

    /**
     * Prepares the view with the translated text and recognition results.
     *
     * @param outputData The data containing the recognized speech and translated text.
     */
    void prepareSuccessView(VoiceTranslationOutputData outputData);

    /**
     * Prepares the view for a failure scenario.
     *
     * @param error The error message explaining why the translation failed.
     */
    void prepareFailView(String error);
}