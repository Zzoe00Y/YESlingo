package use_case.voice_translation;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

/**
 * Interactor for the voice translation use case.
 * Implements the logic for recognizing speech, detecting silence,
 * and forwarding the recognized text to the output boundary.
 */
public class VoiceTranslationInteractor implements VoiceTranslationInputBoundary {
    private final VoiceTranslationOutputBoundary outputBoundary;

    /**
     * Constructor for VoiceTranslationInteractor.
     *
     * @param outputBoundary The output boundary to handle recognized text results.
     */
    public VoiceTranslationInteractor(VoiceTranslationOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void translate(VoiceTranslationInputData inputData) {
        try {
            // Configure the recognizer
            Configuration configuration = new Configuration();
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

            System.out.println("Start speaking...");
            recognizer.startRecognition(true);

            long silenceThreshold = inputData.getSilenceThresholdMillis();
            long lastTimeRecognized = System.currentTimeMillis();
            boolean isRecognitionStopped = false;

            // Process recognition until silence is detected
            while (!isRecognitionStopped) {
                String result = recognizer.getResult().getHypothesis();

                if (result != null && !result.trim().isEmpty()) {
                    System.out.println("You said: " + result);
                    lastTimeRecognized = System.currentTimeMillis(); // Reset silence timer
                    outputBoundary.handleRecognitionResult(new VoiceTranslationOutputData(result));
                }

                if (System.currentTimeMillis() - lastTimeRecognized > silenceThreshold) {
                    System.out.println("Silence detected, stopping recognition...");
                    recognizer.stopRecognition();
                    isRecognitionStopped = true;
                }

                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.err.println("Error during speech recognition: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
