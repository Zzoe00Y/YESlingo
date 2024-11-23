package use_case.file_translation;

public interface FileTranslationOutputBoundary {

    void prepareSuccessView(FileTranslationOutputData outputData);

    void prepareFailView(String error);
}
