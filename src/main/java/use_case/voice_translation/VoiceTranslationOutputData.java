package use_case.voice_translation;

/**
 * The output data class for the Voice Translation use case.
 * This class contains the recognized speech text and the translated text, which are prepared for the UI.
 */
public class VoiceTranslationOutputData {

    private final String recognizedText;

    /**
     * Constructor for VoiceTranslationOutputData.
     * @param recognizedText The recognized speech text.
     */
    public VoiceTranslationOutputData(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    /**
     * Retrieves the recognized text.
     *
     * @return The recognized speech text.
     */
    public String getRecognizedText() {
        return recognizedText;
    }
}
