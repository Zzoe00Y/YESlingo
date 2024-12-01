package use_case.file_translation;

import java.io.IOException;
import java.util.logging.Logger;

import external_services.FileTranslationService;

/**
 * Interactor class for the File Translation use case.
 * Implements the {@link FileTranslationInputBoundary} to handle file translation operations.
 */
public class FileTranslationInteractor implements FileTranslationInputBoundary {

    private static final Logger LOGGER = Logger.getLogger(FileTranslationInteractor.class.getName());
    private static final String UNSUPPORTED_FILE_MESSAGE = "Only .txt files are supported at the moment.";

    private final FileTranslationService fileTranslationService;
    private final FileTranslationOutputBoundary outputBoundary;

    /**
     * Constructs a new {@code FileTranslationInteractor} with the specified file translation
     * service and output boundary.
     *
     * @param fileTranslationService The service responsible for file translation.
     * @param outputBoundary         The output boundary for presenting results.
     */
    public FileTranslationInteractor(FileTranslationService fileTranslationService,
                                     FileTranslationOutputBoundary outputBoundary) {
        this.fileTranslationService = fileTranslationService;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Translates the content of a `.txt` file based on the input data provided.
     * If the translation is successful, it passes the result to the output boundary;
     * otherwise, it handles and reports errors.
     *
     * @param inputData The input data containing file path, source language, and target language.
     */
    @Override
    public void translate(FileTranslationInputData inputData) {
        try {
            // Check if the file is a `.txt` file
            if (inputData.getFilePath().endsWith(".txt")) {
                final String translatedText;
                translatedText = fileTranslationService.translate(
                        inputData.getFilePath(),
                        inputData.getSourceLanguage(),
                        inputData.getTargetLanguage()
                );

                LOGGER.info("Translation successful! Translated text: " + translatedText);
                final FileTranslationOutputData outputData;
                outputData = new FileTranslationOutputData(translatedText);
                outputBoundary.prepareSuccessView(outputData);
            }
            else {
                throw new UnsupportedOperationException(UNSUPPORTED_FILE_MESSAGE);
            }
        }
        catch (UnsupportedOperationException unsupportedOperationException) {
            LOGGER.warning("Unsupported file type: " + unsupportedOperationException.getMessage());
            outputBoundary.prepareFailView("Error: " + UNSUPPORTED_FILE_MESSAGE);
        }
        catch (IOException ioException) {
            LOGGER.severe("IO error during file translation: " + ioException.getMessage());
            outputBoundary.prepareFailView("Translation failed due to an IO error: " + ioException.getMessage());
        }
    }
}

