package use_case.file_translation;

import external_services.FileTranslationService;

import java.util.logging.Logger;

public class FileTranslationInteractor implements FileTranslationInputBoundary {

    private static final Logger logger = Logger.getLogger(FileTranslationInteractor.class.getName());

    private final FileTranslationService fileTranslationService;
    private final FileTranslationOutputBoundary outputBoundary;

    public FileTranslationInteractor(FileTranslationService fileTranslationService,
                                     FileTranslationOutputBoundary outputBoundary) {
        this.fileTranslationService = fileTranslationService;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void translate(FileTranslationInputData inputData) {
        try {
            // Check if the file is a `.txt` file
            if (inputData.getFilePath().endsWith(".txt")) {
                // Translate the text content of the file
                String translatedText = fileTranslationService.translate(
                        inputData.getFilePath(),
                        inputData.getSourceLanguage(),
                        inputData.getTargetLanguage()
                );

                logger.info("Translation successful! Translated text: " + translatedText);
                FileTranslationOutputData outputData = new FileTranslationOutputData(translatedText);
                outputBoundary.prepareSuccessView(outputData);
            } else {
                // Handle other file types (if supported later)
                throw new UnsupportedOperationException("Only .txt files are supported at the moment.");
            }
        } catch (Exception e) {
            logger.severe("Error during file translation: " + e.getMessage());
            outputBoundary.prepareFailView("Translation failed: " + e.getMessage());
        }
    }}
