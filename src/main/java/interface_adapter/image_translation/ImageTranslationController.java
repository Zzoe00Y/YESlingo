package interface_adapter.image_translation;

import use_case.image_translation.ImageTranslationInputBoundary;
import use_case.image_translation.ImageTranslationInputData;

import java.awt.image.BufferedImage;

/**
 * Controller for the Image Translation Use Case.
 */
public class ImageTranslationController {
    private final ImageTranslationInputBoundary imageTranslationUseCaseInteractor;                ;

    public ImageTranslationController(ImageTranslationInputBoundary imageTranslationUseCaseInteractor) {
        this.imageTranslationUseCaseInteractor = imageTranslationUseCaseInteractor;
    }

    /**
     * Executes the image translation use case.
     *
     * @param image the image containing the text to translate
     * @param targetLanguage the target language for the translation
     */
    public void execute(BufferedImage image, String targetLanguage) {
        final ImageTranslationInputData imageTranslationInputData = new ImageTranslationInputData(image, targetLanguage);
        imageTranslationUseCaseInteractor.execute(imageTranslationInputData);
    }
}
