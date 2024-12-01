package interface_adapter.voice_translation;

import use_case.voice_translation.VoiceTranslationInputBoundary;
import use_case.voice_translation.VoiceTranslationInputData;

/**
 * Controller for handling voice translation requests.
 * Converts user speech input into interactor-friendly data.
 */
public class VoiceTranslationController {

    private final VoiceTranslationInputBoundary voiceTranslationInteractor;

    /**
     * Constructs a VoiceTranslationController with the provided interactor.
     *
     * @param voiceTranslationInteractor The interactor to handle voice translation logic.
     */
    public VoiceTranslationController(VoiceTranslationInputBoundary voiceTranslationInteractor) {
        this.voiceTranslationInteractor = voiceTranslationInteractor;
    }

    /**
     * Initiates the voice translation process.
     * Converts speech to text, then translates the text.
     *
     * @param silenceThresholdMillis The maximum duration of silence (in milliseconds) before stopping speech recognition.
     * @param sourceLang             The source language code (e.g., "en" for English).
     * @param targetLang             The target language code (e.g., "fr" for French).
     */
    public void translateVoice(long silenceThresholdMillis, String sourceLang, String targetLang) {
        System.out.println("Controller received voice translation request");
        try {
            // Step 1: Create input data for the interactor
            VoiceTranslationInputData inputData = new VoiceTranslationInputData(silenceThresholdMillis, sourceLang, targetLang);

            // Step 2: Delegate the task to the interactor to handle speech recognition and translation
            voiceTranslationInteractor.translate(inputData);

        } catch (Exception e) {
            System.err.println("Error in VoiceTranslationController: " + e.getMessage());
            throw new RuntimeException("Failed to initiate voice translation: " + e.getMessage(), e);
        }
    }
}
