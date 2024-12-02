package use_case.voice_translation;

import external_services.SpeechToTextService;
import org.junit.Test;
import static org.junit.Assert.*;

public class VoiceTranslationInteractorTest {

    @Test
    public void successVoiceTranslationTest() throws java.io.IOException {
        // Using the fake service to simulate successful speech recognition
        SpeechToTextService fakeSpeechToTextService = new SpeechToTextService() {
            @Override
            public String recognizeSpeech() {
                return "Hello, world!";
            }
        };


        // Use a real implementation for the output boundary
        VoiceTranslationOutputBoundary outputBoundary = new VoiceTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(VoiceTranslationOutputData outputData) {
                assertEquals("Hello, world!", outputData.getRecognizedText());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Translation should have succeeded, but failed with error: " + error);
            }
        };

        // Prepare the interactor
        VoiceTranslationInteractor interactor = new VoiceTranslationInteractor(fakeSpeechToTextService, outputBoundary);

        // Call the translate method
        interactor.translate();
    }

    @Test
    public void failureSpeechRecognitionErrorTest() {
        try {
            // Simulating an error with SpeechToTextService by throwing an exception
            SpeechToTextService fakeSpeechToTextService = new SpeechToTextService() {
                @Override
                public String recognizeSpeech() {
                    throw new RuntimeException("Speech recognition error");
                }
            };

            // Use a real implementation for the output boundary
            VoiceTranslationOutputBoundary outputBoundary = new VoiceTranslationOutputBoundary() {
                @Override
                public void prepareSuccessView(VoiceTranslationOutputData outputData) {
                    fail("Translation should have failed, but succeeded with text: " + outputData.getRecognizedText());
                }

                @Override
                public void prepareFailView(String error) {
                    assertEquals("Speech to text failed: Speech recognition error", error);
                }
            };

            // Prepare the interactor
            VoiceTranslationInteractor interactor = new VoiceTranslationInteractor(fakeSpeechToTextService, outputBoundary);

            // Call the translate method
            interactor.translate();

        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void failureNoSpeechRecognizedTest() throws java.io.IOException {
        // Using the fake service to simulate no speech recognized
        SpeechToTextService fakeSpeechToTextService = new SpeechToTextService() {
            @Override
            public String recognizeSpeech() {
                return "";
            }
        };

        // Use a real implementation for the output boundary
        VoiceTranslationOutputBoundary outputBoundary = new VoiceTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(VoiceTranslationOutputData outputData) {
                fail("Translation should have failed, but succeeded with text: " + outputData.getRecognizedText());
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Speech to text failed: No speech was recognized.", error);
            }
        };

        // Prepare the interactor
        VoiceTranslationInteractor interactor = new VoiceTranslationInteractor(fakeSpeechToTextService, outputBoundary);

        // Call the translate method
        interactor.translate();
    }
}
