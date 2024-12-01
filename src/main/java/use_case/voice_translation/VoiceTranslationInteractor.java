package use_case.voice_translation;

// import external_services.TextToTextTranslationService;
import use_case.text_translation.TextTranslationDataAccessInterface;
import external_services.SpeechToTextService;
import java.util.logging.Logger;

/**
 * The interactor class for the Voice Translation use case.
 *
 * This class coordinates the process of receiving transcribed speech,
 * performing text-to-text translation, and preparing the data for presentation via the UI.
 */
public class VoiceTranslationInteractor implements VoiceTranslationInputBoundary {

    private static final Logger logger = Logger.getLogger(VoiceTranslationInteractor.class.getName());

    private final SpeechToTextService speechToTextService;  // For speech-to-text conversion
    // private final TextTranslationDataAccessInterface textTranslationService;    // For text-to-text translation
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
        // this.textTranslationService = textTranslationService;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Translates the recognized speech into text and then translates the text to the target language.
     *
     * @param inputData The input data containing the silence threshold, source, and target languages.
     */
    @Override
    public void translate(VoiceTranslationInputData inputData) {
        try {
            // Step 1: Convert speech to text
            String recognizedText = speechToTextService.recognizeSpeech(inputData.getSilenceThresholdMillis());

            if (recognizedText == null || recognizedText.isEmpty()) {
                // Handle case where no speech was recognized
                throw new IllegalStateException("No speech was recognized.");
            }

            logger.info("Speech recognized: " + recognizedText);

//            // Step 2: Perform text translation
//            String translatedText = textTranslationService.translateText(
//                    recognizedText,
//                    inputData.getSourceLanguage(),
//                    inputData.getTargetLanguage() // Assuming source language is auto-detected
//            );

//            logger.info("Translation successful! Translated text: " + translatedText);
//
            // Step 3: Prepare the output data and pass it to the output boundary (UI)
            VoiceTranslationOutputData outputData = new VoiceTranslationOutputData(recognizedText);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Log and pass the error to the UI via the output boundary
            logger.severe("Error during voice translation: " + e.getMessage());
            outputBoundary.prepareFailView("Speech to text failed: " + e.getMessage());
        }
    }
}
