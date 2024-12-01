package interface_adapter.voice_translation;

import use_case.voice_translation.VoiceTranslationInputBoundary;
// import use_case.voice_translation.VoiceTranslationInputData;

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
     */
    public void translateVoice() {
        System.out.println("Controller received voice translation request");
        try {
            // Step 1: Create input data for the interactor
            // VoiceTranslationInputData inputData = new VoiceTranslationInputData(sourceLang, targetLang);

            // Step 2: Delegate the task to the interactor to handle speech recognition and translation
            voiceTranslationInteractor.translate();

        } catch (Exception e) {
            System.err.println("Error in VoiceTranslationController: " + e.getMessage());
            throw new RuntimeException("Failed to initiate voice translation: " + e.getMessage(), e);
        }
    }
}
