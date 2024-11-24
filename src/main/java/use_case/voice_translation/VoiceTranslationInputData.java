package use_case.voice_translation;

/**
 * The input data class for the Voice Translation use case.
 *
 * This class holds the transcribed speech text, which is the primary input
 * for the translation process. The transcribed text is passed to the use case
 * so it can be translated.
 */
public class VoiceTranslationInputData {
    private final long silenceThresholdMillis;

    /**
     * Constructor for VoiceTranslationInputData.
     *
     * @param silenceThresholdMillis The maximum duration (in milliseconds) of silence
     *                                before stopping the speech recognition.
     */
    public VoiceTranslationInputData(long silenceThresholdMillis) {
        this.silenceThresholdMillis = silenceThresholdMillis;
    }

    /**
     * Retrieves the silence threshold in milliseconds.
     *
     * @return The silence threshold.
     */
    public long getSilenceThresholdMillis() {
        return silenceThresholdMillis;
    }
}
