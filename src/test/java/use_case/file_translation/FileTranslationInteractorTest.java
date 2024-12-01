package use_case.file_translation;

import external_services.FileTranslationService;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test suite for the FileTranslationInteractor.
 * Covers all aspects of the file translation use case.
 */
public class FileTranslationInteractorTest {

    /**
     * Tests that the interactor successfully translates a valid .txt file
     * from English to French using a mock translation service.
     */
    @Test
    public void testSuccessfulTranslation() {
        FileTranslationService mockService = new FileTranslationService() {
            @Override
            public String translate(String filePath, String sourceLang, String targetLang) {
                assertEquals("en", sourceLang);
                assertEquals("fr", targetLang);
                return "Ravi de vous rencontrer !";
            }
        };

        FileTranslationOutputBoundary outputBoundary = new FileTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(FileTranslationOutputData outputData) {
                assertEquals("Ravi de vous rencontrer !", outputData.getTranslatedFileUrl());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Expected successful translation, but got error: " + error);
            }
        };

        FileTranslationInputData inputData = new FileTranslationInputData(
                "test_files/valid_text.txt", "en", "fr");

        FileTranslationInteractor interactor = new FileTranslationInteractor(mockService, outputBoundary);
        interactor.translate(inputData);
    }

    /**
     * Tests that the interactor handles an unsupported file type correctly
     * by invoking the failure view with an appropriate message.
     */
    @Test
    public void testUnsupportedFileType() {
        FileTranslationService mockService = new FileTranslationService() {
            @Override
            public String translate(String filePath, String sourceLang, String targetLang) {
                fail("Translation service should not be called for unsupported file types.");
                return null;
            }
        };

        FileTranslationOutputBoundary outputBoundary = new FileTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(FileTranslationOutputData outputData) {
                fail("Should not succeed with unsupported file type.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Error: Only .txt files are supported at the moment.", error);
            }
        };

        FileTranslationInputData inputData = new FileTranslationInputData(
                "test_files/unsupported_file.pdf", "en", "fr");

        FileTranslationInteractor interactor = new FileTranslationInteractor(mockService, outputBoundary);
        interactor.translate(inputData);
    }

    /**
     * Tests that the interactor handles a missing file by catching FileNotFoundException
     * and invoking the failure view with an appropriate message.
     */
    @Test
    public void testFileNotFound() {
        FileTranslationService mockService = new FileTranslationService() {
            @Override
            public String translate(String filePath, String sourceLang, String targetLang) throws IOException {
                throw new FileNotFoundException("File not found: " + filePath);
            }
        };

        FileTranslationOutputBoundary outputBoundary = new FileTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(FileTranslationOutputData outputData) {
                fail("Translation should have failed due to missing file.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("File not found"));
            }
        };

        FileTranslationInputData inputData = new FileTranslationInputData(
                "test_files/nonexistent.txt", "en", "fr");

        FileTranslationInteractor interactor = new FileTranslationInteractor(mockService, outputBoundary);

        // Assert that FileNotFoundException is handled
        interactor.translate(inputData);
    }

    /**
     * Tests that the interactor handles an IOException during translation
     * and invokes the failure view with an appropriate message.
     */
    @Test
    public void testIOExceptionDuringTranslation() {
        FileTranslationService mockService = new FileTranslationService() {
            @Override
            public String translate(String filePath, String sourceLang, String targetLang) throws IOException {
                throw new IOException("IO error while reading the file.");
            }
        };

        FileTranslationOutputBoundary outputBoundary = new FileTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(FileTranslationOutputData outputData) {
                fail("Translation should have failed due to an IO error.");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(error.contains("IO error while reading the file"));
            }
        };

        FileTranslationInputData inputData = new FileTranslationInputData(
                "test_files/io_error.txt", "en", "fr");

        FileTranslationInteractor interactor = new FileTranslationInteractor(mockService, outputBoundary);

        // No need for assertThrows since interactor should handle IOException internally
        interactor.translate(inputData);
    }

    /**
     * Tests that the interactor handles empty input data and invokes the failure view.
     */
    @Test
    public void testEmptyInputData() {
        FileTranslationService mockService = new FileTranslationService() {
            @Override
            public String translate(String filePath, String sourceLang, String targetLang) {
                fail("Translation service should not be called with empty input data.");
                return null;
            }
        };

        FileTranslationOutputBoundary outputBoundary = new FileTranslationOutputBoundary() {
            @Override
            public void prepareSuccessView(FileTranslationOutputData outputData) {
                fail("Translation should have failed due to empty input data.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Error: Only .txt files are supported at the moment.", error);
            }
        };

        FileTranslationInputData inputData = new FileTranslationInputData(
                "", "", "");

        FileTranslationInteractor interactor = new FileTranslationInteractor(mockService, outputBoundary);
        interactor.translate(inputData);
    }
}
