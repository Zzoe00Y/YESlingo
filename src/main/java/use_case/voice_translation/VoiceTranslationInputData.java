package use_case.voice_translation;

/**
 * Encapsulates the input data for the Voice Translation use case.
 */
public class VoiceTranslationInputData {

    private final String recognizedText;

    /**
     * Constructs a VoiceTranslationInputData object.
     * @param recognizedText The text recognized from speech.
     */
    public VoiceTranslationInputData(String recognizedText) {
        this.recognizedText = recognizedText;
    }

    /**
     * Returns the recognized text.
     * @return The recognized text.
     */
    public String getRecognizedText() {
        return recognizedText;
    }
}
