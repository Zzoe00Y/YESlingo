package use_case.file_translation;

/**
 * Output data class for the File Translation use case.
 * Stores the URL of the translated file to be used in the success view.
 */
public class FileTranslationOutputData {

    private final String translatedFileUrl;

    /**
     * Constructs a new {@code FileTranslationOutputData} object with the specified translated file URL.
     *
     * @param translatedFileUrl The URL of the translated file.
     */
    public FileTranslationOutputData(String translatedFileUrl) {
        this.translatedFileUrl = translatedFileUrl;
    }

    /**
     * Returns the URL of the translated file.
     *
     * @return A string representing the translated file URL.
     */
    public String getTranslatedFileUrl() {
        return translatedFileUrl;
    }
}
