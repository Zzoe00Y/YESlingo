package interface_adapter.voice_translation;

import use_case.voice_translation.VoiceTranslationInputBoundary;

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
    public void speechToText() {
        System.out.println("Controller received voice translation request");
        try {

            voiceTranslationInteractor.translate();

        }
        catch (Exception exception) {
            System.err.println("Error in VoiceTranslationController: " + exception.getMessage());
            throw new RuntimeException("Failed to initiate voice translation: " + exception.getMessage(), exception);
        }
    }
}
