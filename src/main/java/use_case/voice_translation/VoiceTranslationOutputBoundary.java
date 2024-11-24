package use_case.voice_translation;

/**
 * The output boundary interface for the voice translation use case.
 * Defines the contract for handling recognized speech output.
 */
public interface VoiceTranslationOutputBoundary {
    /**
     * Handles the recognized text and forwards it for further processing or display.
     *
     * @param outputData The output data containing the recognized text.
     */
    void handleRecognitionResult(VoiceTranslationOutputData outputData);
}
