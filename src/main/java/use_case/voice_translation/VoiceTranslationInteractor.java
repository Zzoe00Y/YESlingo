package use_case.voice_translation;

import java.util.logging.Logger;

import external_services.SpeechToTextService;

/**
 * The interactor class for the Voice Translation use case.
 * This class coordinates the process of receiving transcribed speech,
 * performing text-to-text translation, and preparing the data for presentation via the UI.
 */
public class VoiceTranslationInteractor implements VoiceTranslationInputBoundary {

    private static final Logger LOGGER = Logger.getLogger(VoiceTranslationInteractor.class.getName());

    private final SpeechToTextService speechToTextService;
    private final VoiceTranslationOutputBoundary outputBoundary;

    /**
     * Constructor for VoiceTranslationInteractor.
     *
     * @param speechToTextService   The service used for converting speech to text.
     * @param outputBoundary        The output boundary to present the result to the UI.
     */
    public VoiceTranslationInteractor(SpeechToTextService speechToTextService,
                                      VoiceTranslationOutputBoundary outputBoundary) {
        this.speechToTextService = speechToTextService;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Translates the recognized speech into text and then translates the text to the target language.
     */
    @Override
    public void translate() {
        try {
            // Convert speech to text
            VoiceTranslationInputData inputData = new VoiceTranslationInputData(speechToTextService.recognizeSpeech());

            if (inputData.getRecognizedText() == null || inputData.getRecognizedText().isEmpty()) {
                // Handle case where no speech was recognized
                throw new IllegalStateException("No speech was recognized.");
            }

            LOGGER.info("Speech recognized: " + inputData.getRecognizedText());

            // Prepare the output data and pass it to the output boundary (UI)
            VoiceTranslationOutputData outputData = new VoiceTranslationOutputData(inputData.getRecognizedText());
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Log and pass the error to the UI via the output boundary
            LOGGER.severe("Error during voice translation: " + e.getMessage());
            outputBoundary.prepareFailView("Speech to text failed: " + e.getMessage());
        }
    }

}
