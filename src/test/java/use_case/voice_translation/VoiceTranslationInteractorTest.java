package use_case.voice_translation;

import external_services.SpeechToTextService;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class VoiceTranslationInteractorTest {

    private SpeechToTextService fakeSpeechToTextService;
    private VoiceTranslationOutputBoundary outputBoundary;
    private VoiceTranslationInteractor interactor;

    @Before
    public void setUp() {
        // Reset shared variables before each test to avoid cross-test interference
        fakeSpeechToTextService = null;
        outputBoundary = null;
        interactor = null;
    }

    @After
    public void tearDown() {
        // Clean up any references after each test
        fakeSpeechToTextService = null;
        outputBoundary = null;
        interactor = null;
    }

    @Test
    public void successVoiceTranslationTest() throws java.io.IOException {
        fakeSpeechToTextService = new SpeechToTextService() {
            @Override
            public String recognizeSpeech() {
                return "Hello, world!";
            }
        };

        outputBoundary = new VoiceTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(VoiceTranslationOutputData outputData) {
                assertEquals("Hello, world!", outputData.getRecognizedText());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Translation should have succeeded, but failed with error: " + error);
            }
        };

        interactor = new VoiceTranslationInteractor(fakeSpeechToTextService, outputBoundary);
        interactor.translate();
    }

    @Test
    public void failureSpeechRecognitionErrorTest() throws IOException {
        fakeSpeechToTextService = new SpeechToTextService() {
            @Override
            public String recognizeSpeech() {
                throw new RuntimeException("Speech recognition error");
            }
        };

        outputBoundary = new VoiceTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(VoiceTranslationOutputData outputData) {
                fail("Translation should have failed, but succeeded with text: " + outputData.getRecognizedText());
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Speech to text failed: Speech recognition error", error);
            }
        };

        interactor = new VoiceTranslationInteractor(fakeSpeechToTextService, outputBoundary);
        interactor.translate();
    }

    @Test
    public void failureNoSpeechRecognizedTest() throws java.io.IOException {
        fakeSpeechToTextService = new SpeechToTextService() {
            @Override
            public String recognizeSpeech() {
                return "";
            }
        };

        outputBoundary = new VoiceTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(VoiceTranslationOutputData outputData) {
                fail("Translation should have failed, but succeeded with text: " + outputData.getRecognizedText());
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Speech to text failed: No speech was recognized.", error);
            }
        };

        interactor = new VoiceTranslationInteractor(fakeSpeechToTextService, outputBoundary);
        interactor.translate();
    }
}