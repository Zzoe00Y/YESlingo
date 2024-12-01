package external_services;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

/**
 * Service for converting speech to text using Sphinx4.
 * This service encapsulates the configuration and logic for speech recognition.
 */
public class SpeechToTextService {

    private final LiveSpeechRecognizer recognizer;

    /**
     * Initializes the SpeechToTextService with the Sphinx4 configuration.
     *
     * @throws java.io.IOException If the recognizer fails to initialize.
     */
    public SpeechToTextService() throws java.io.IOException {
        // Configure Sphinx4 with the default acoustic model, dictionary, and language model
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        // Initialize the recognizer
        recognizer = new LiveSpeechRecognizer(configuration);
    }

    /**
     * Starts speech recognition from the microphone.
     * Stops automatically after detecting prolonged silence.
     * @return Recognized text from the speech input.
     * @throws Exception If an error occurs during recognition.
     */
    public String recognizeSpeech() {
        System.out.println("Listening for speech...");

        recognizer.startRecognition(true);
        StringBuilder recognizedText = new StringBuilder();

        // Get the hypothesis (recognized text)
        String result = recognizer.getResult().getHypothesis();

        if (result != null && !result.trim().isEmpty()) {
            System.out.println("Recognized: " + result);
            recognizedText.append(result).append(" ");
        }

        recognizer.stopRecognition();
        System.out.println("RECOGNIZER STOPPED");
        return recognizedText.toString().trim();
    }
}