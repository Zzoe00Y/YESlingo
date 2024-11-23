package use_case.file_translation;

/**
 * Input data for the Image Translation use case.
 */
public class FileTranslationInputData {
    private final String filePath;
    private final String sourceLanguage;
    private final String targetLanguage;

    public FileTranslationInputData(String filePath, String sourceLanguage, String targetLanguage) {
        this.filePath = filePath;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }
}