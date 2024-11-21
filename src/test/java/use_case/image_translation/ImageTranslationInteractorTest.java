package use_case.image_translation;

import external_services.ImageToTextService;
import external_services.TranslationService;
import use_case.text_translation.TextTranslationUseCase;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class ImageTranslationInteractorTest {

    @Test
    public void successImageTranslationTest() throws Exception {
        // Mock ImageToTextService that successfully extracts text
        ImageToTextService mockImageToTextService = new ImageToTextService() {
            @Override
            public String extractText(BufferedImage image) {
                return "Hello, world!";
            }
        };

        // Mock TextTranslationUseCase that successfully translates text to the target language
        TextTranslationUseCase mockTextTranslationUseCase = new TextTranslationUseCase(new TranslationService() {
            @Override
            public String translate(String text, String targetLanguage) {
                return "Hola, mundo!";
            }
        });

        // Success presenter that checks if translation result matches expected output
        ImageTranslationOutputBoundary successPresenter = new ImageTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(ImageTranslationOutputData outputData) {
                assertEquals("Hola, mundo!", outputData.getTranslatedText()); // Expected translated text
            }

            @Override
            public void prepareFailView(String error) {
                fail("Translation should have succeeded, but it failed with error: " + error);
            }
        };

        // Setup interactor with mock services and execute
        ImageTranslationInteractor interactor = new ImageTranslationInteractor(mockImageToTextService, mockTextTranslationUseCase, successPresenter);
        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ImageTranslationInputData inputData = new ImageTranslationInputData(mockImage, "es");

        interactor.translate(inputData);
    }

    @Test
    public void failureImageToTextServiceTest() {
        // Mock ImageToTextService that simulates a failure in OCR processing
        ImageToTextService mockImageToTextService = new ImageToTextService() {
            @Override
            public String extractText(BufferedImage image) throws Exception {
                throw new Exception("OCR Service failed"); // Simulate OCR failure
            }
        };

        // Mock TextTranslationUseCase - should not be called in this test
        TextTranslationUseCase mockTextTranslationUseCase = new TextTranslationUseCase(new TranslationService() {
            @Override
            public String translate(String text, String targetLanguage) {
                return "This should not be called";
            }
        });

        // Failure presenter that checks if the correct error message is displayed
        ImageTranslationOutputBoundary failurePresenter = new ImageTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(ImageTranslationOutputData outputData) {
                fail("Translation should have failed, but it succeeded with text: " + outputData.getTranslatedText());
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Error occurred during OCR processing.", error); // Expected failure message
            }
        };

        // Setup interactor with mock services and execute
        ImageTranslationInteractor interactor = new ImageTranslationInteractor(mockImageToTextService, mockTextTranslationUseCase, failurePresenter);
        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ImageTranslationInputData inputData = new ImageTranslationInputData(mockImage, "es");

        interactor.translate(inputData);
    }

    @Test
    public void failureTextTranslationUseCaseTest() throws Exception {
        // Mock ImageToTextService that successfully extracts text
        ImageToTextService mockImageToTextService = new ImageToTextService() {
            @Override
            public String extractText(BufferedImage image) {
                return "Hello, world!";
            }
        };

        // Mock TextTranslationUseCase that simulates a failure in text translation
        TextTranslationUseCase mockTextTranslationUseCase = new TextTranslationUseCase(new TranslationService() {
            @Override
            public String translate(String text, String targetLanguage) throws Exception {
                throw new Exception("Translation Service failed");
            }
        });

        // Failure presenter that checks if the correct error message is displayed
        ImageTranslationOutputBoundary failurePresenter = new ImageTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(ImageTranslationOutputData outputData) {
                fail("Translation should have failed, but it succeeded with text: " + outputData.getTranslatedText());
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Error occurred during text translation.", error);
            }
        };

        // Setup interactor with mock services and execute
        ImageTranslationInteractor interactor = new ImageTranslationInteractor(mockImageToTextService, mockTextTranslationUseCase, failurePresenter);
        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ImageTranslationInputData inputData = new ImageTranslationInputData(mockImage, "es");

        interactor.translate(inputData);
    }
}
