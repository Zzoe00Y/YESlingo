package use_case.image_translation;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Input data for the Image Translation use case.
 */
public class ImageTranslationInputData {

    private final BufferedImage image;
    private final String targetLanguage;

    /**
     * Constructs an ImageTranslationInputData with the provided image and target language.
     *
     * @param image the image to be processed for text extraction and translation
     * @param targetLanguage the language to translate the extracted text into
     */
    public ImageTranslationInputData(BufferedImage image, String targetLanguage) {
        this.image = Objects.requireNonNull(image, "Image cannot be null");
        this.targetLanguage = targetLanguage != null ? targetLanguage : "en"; // Default to English if null
    }

    /**
     * Gets the image to be processed.
     *
     * @return the BufferedImage representing the image to be translated
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Gets the target language for translation.
     *
     * @return the target language as a String
     */
    public String getTargetLanguage() {
        return targetLanguage;
    }

    @Override
    public String toString() {
        return "ImageTranslationInputData{" +
                "image=" + image +
                ", targetLanguage='" + targetLanguage + '\'' +
                '}';
    }
}
