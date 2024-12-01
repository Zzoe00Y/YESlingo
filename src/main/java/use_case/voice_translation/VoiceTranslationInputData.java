package use_case.voice_translation;

/**
 * Input data for the Voice Translation use case.
 *
 * This class holds the parameters required for recognizing speech and
 * translating the recognized text. The recognized speech is passed as input
 * for the translation process along with the source and target languages.
 */
public class VoiceTranslationInputData {
    private final long silenceThresholdMillis;
    private final String sourceLanguage;
    private final String targetLanguage;

    /**
     * Constructor for VoiceTranslationInputData.
     *
     * @param silenceThresholdMillis The maximum duration (in milliseconds) of silence
     *                                before stopping the speech recognition.
     * @param sourceLanguage         The language code of the source language (e.g., "en").
     * @param targetLanguage         The language code of the target language (e.g., "fr").
     */
    public VoiceTranslationInputData(long silenceThresholdMillis, String sourceLanguage, String targetLanguage) {
        this.silenceThresholdMillis = silenceThresholdMillis;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    /**
     * Retrieves the silence threshold in milliseconds.
     *
     * @return The silence threshold.
     */
    public long getSilenceThresholdMillis() {
        return silenceThresholdMillis;
    }

    /**
     * Retrieves the source language for translation.
     *
     * @return The source language code.
     */
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    /**
     * Retrieves the target language for translation.
     *
     * @return The target language code.
     */
    public String getTargetLanguage() {
        return targetLanguage;
    }
}
