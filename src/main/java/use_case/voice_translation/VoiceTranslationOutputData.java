package use_case.voice_translation;

/**
 * Encapsulates the output data from the voice translation use case.
 * This typically includes the recognized speech text.
 */
public class VoiceTranslationOutputData {
    private final String recognizedText;

    /**
     * Constructor for VoiceTranslationOutputData.
     *
     * @param recognizedText The text recognized from speech input.
     */
    public VoiceTranslationOutputData(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    /**
     * Retrieves the recognized speech text.
     *
     * @return The recognized text.
     */
    public String getRecognizedText() {
        return recognizedText;
    }
}
