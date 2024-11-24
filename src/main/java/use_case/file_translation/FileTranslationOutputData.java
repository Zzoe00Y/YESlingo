package use_case.file_translation;

public class FileTranslationOutputData {
    private final String translatedFileUrl;

    public FileTranslationOutputData(String translatedFileUrl) {
        this.translatedFileUrl = translatedFileUrl;
    }

    public String getTranslatedFileUrl() {
        return translatedFileUrl;
    }
}