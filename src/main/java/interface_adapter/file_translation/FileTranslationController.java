package interface_adapter.file_translation;

import use_case.file_translation.FileTranslationInputBoundary;
import use_case.file_translation.FileTranslationInputData;

/**
 * Controller for handling file translation requests.
 * Converts user input into interactor-friendly data.
 */
public class FileTranslationController {

    private final FileTranslationInputBoundary fileTranslationInteractor;

    /**
     * Constructs a FileTranslationController with the provided interactor.
     *
     * @param fileTranslationInteractor The interactor to handle file translation logic.
     */
    public FileTranslationController(FileTranslationInputBoundary fileTranslationInteractor) {
        this.fileTranslationInteractor = fileTranslationInteractor;
    }

    /**
     * Initiates the file translation process.
     *
     * @param filePath      The path to the file to be translated.
     * @param sourceLang    The source language code (e.g., "en" for English).
     * @param targetLang    The target language code (e.g., "es" for Spanish).
     */
    public void translateFile(String filePath, String sourceLang, String targetLang) {
        System.out.println("Controller received file translation request");
        try {
            // Create input data for the interactor
            FileTranslationInputData inputData = new FileTranslationInputData(filePath, sourceLang, targetLang);

            // Delegate the file translation task to the interactor
            fileTranslationInteractor.translate(inputData);

        } catch (Exception e) {
            System.err.println("Error in FileTranslationController: " + e.getMessage());
            throw new RuntimeException("Failed to initiate file translation: " + e.getMessage(), e);
        }
    }
}
