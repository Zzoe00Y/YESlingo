package use_case.image_translation;

import use_case.text_translation.TextTranslationUseCase;
import external_services.ImageToTextService;

/**
 * Interactor for the Image Translation Use Case.
 * This class handles the logic of extracting text from an image and translating it.
 */
public class ImageTranslationInteractor implements ImageTranslationInputBoundary {

    private final ImageToTextService imageToTextService;
    private final TextTranslationUseCase textTranslationUseCase;

    public ImageTranslationInteractor(ImageToTextService imageToTextService, TextTranslationUseCase textTranslationUseCase) {
        this.imageToTextService = imageToTextService;
        this.textTranslationUseCase = textTranslationUseCase;
    }

    public void execute(ImageTranslationInputData inputData) {
        try {
            // Step 1: Extract text from the image
            String extractedText = imageToTextService.extractText(inputData.getImage());

            // Step 2: Translate the extracted text
            String translatedText = textTranslationUseCase.translate(extractedText, inputData.getTargetLanguage());

            System.out.println("Translated Text: " + translatedText);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred during image translation.");
        }
    }
}