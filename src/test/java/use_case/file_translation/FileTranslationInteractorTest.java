package use_case.file_translation;

import org.junit.Test;
import external_services.FileTranslationService;

import static org.junit.Assert.*;

public class FileTranslationInteractorTest {

    @Test
    public void successFileTranslationTest() {
        FileTranslationService mockFileTranslationService = new FileTranslationService() {
            @Override
            public String translate(String filePath, String sourceLang, String targetLang) {
                assertEquals("en", sourceLang);
                assertEquals("es", targetLang);
                return "Translation completed successfully for file: test1.txt";
            }
        };

        FileTranslationOutputBoundary successPresenter = new FileTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(FileTranslationOutputData outputData) {
                assertEquals("Translation completed successfully for file: test1.txt", outputData.getTranslatedFileUrl());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Translation failed, error: " + error);
            }
        };

        FileTranslationInputData inputData = new FileTranslationInputData(
                "/Users/freyazhang/IdeaProjects/YESlingo/test1.txt", "en", "es");

        FileTranslationInteractor interactor = new FileTranslationInteractor(mockFileTranslationService, successPresenter);
        interactor.translate(inputData);
    }

    @Test
    public void failureFileNotFoundTest() {
        // Mock FileTranslationService for file not found error
        FileTranslationService mockFileTranslationService = new FileTranslationService() {
            @Override
            public String translate(String filePath, String sourceLang, String targetLang) {
                throw new RuntimeException("File not found: " + filePath);
            }
        };

        FileTranslationOutputBoundary failurePresenter = new FileTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(FileTranslationOutputData outputData) {
                fail("Translation should have failed, but it succeeded with file URL: " + outputData.getTranslatedFileUrl());
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("File not found"));
            }
        };

        FileTranslationInputData inputData = new FileTranslationInputData(
                "/Users/freyazhang/IdeaProjects/YESlingo/nonexistent.txt", "en", "es");

        FileTranslationInteractor interactor = new FileTranslationInteractor(mockFileTranslationService, failurePresenter);
        interactor.translate(inputData);
    }
}
