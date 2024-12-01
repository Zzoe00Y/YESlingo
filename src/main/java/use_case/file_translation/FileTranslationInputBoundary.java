package use_case.file_translation;

/**
 * Input boundary interface for the File Translation use case.
 * Defines the method to handle file translation operations and acts as a contract
 * for the implementation of the use case.
 */
public interface FileTranslationInputBoundary {

    /**
     * Translates the content of a file based on the input data provided.
     * The input data includes details such as file path, source language,
     * target language, and other necessary translation parameters.
     *
     * @param inputData An object of {@link FileTranslationInputData} containing the
     *                  necessary information for the file translation process,
     *                  including file path, source language, and target language.
     */
    void translate(FileTranslationInputData inputData);
}
