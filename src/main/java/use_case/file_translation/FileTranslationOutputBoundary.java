package use_case.file_translation;

/**
 * Output boundary interface for the File Translation use case.
 * Defines methods to prepare views for successful and failed file translation operations.
 */
public interface FileTranslationOutputBoundary {

    /**
     * Prepares the success view with the provided output data.
     *
     * @param outputData The output data containing the translated text.
     */
    void prepareSuccessView(FileTranslationOutputData outputData);

    /**
     * Prepares the failure view with the provided error message.
     *
     * @param error A string describing the error that occurred.
     */
    void prepareFailView(String error);
}
