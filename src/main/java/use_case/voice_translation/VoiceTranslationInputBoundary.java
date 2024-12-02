package use_case.voice_translation;

/**
 * Input boundary interface for voice translation use case.
 * Defines the contract for translating speech to text and passing it to translation logic.
 */
public interface VoiceTranslationInputBoundary {
    /**
     * Start processing speech input and translating it into text.
     * This method runs until silence is detected.
     */
    void translate();
}

