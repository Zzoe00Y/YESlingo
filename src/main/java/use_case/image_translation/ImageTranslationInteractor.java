package use_case.image_translation;

import external_services.ImageToTextService;
import use_case.text_translation.TextTranslationUseCase;

/**
 * Interactor for the Image Translation Use Case.
 */
public class ImageTranslationInteractor implements ImageTranslationInputBoundary {

    private final ImageToTextService imageToTextService;
    private final TextTranslationUseCase textTranslationUseCase;
    private final ImageTranslationOutputBoundary outputBoundary;

    /**
     * Constructs an ImageTranslationInteractor with the necessary services and presenter.
     *
     * @param imageToTextService used for extracting text from an image
     * @param textTranslationUseCase Use case to translate extracted text into the target language
     * @param outputBoundary used for presenting success or failure of the image translation process
     */
    public ImageTranslationInteractor(ImageToTextService imageToTextService,
                                      TextTranslationUseCase textTranslationUseCase,
                                      ImageTranslationOutputBoundary outputBoundary) {
        this.imageToTextService = imageToTextService;
        this.textTranslationUseCase = textTranslationUseCase;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ImageTranslationInputData inputData) {
        try {
            // Extract text from the image using OCR
            String extractedText = imageToTextService.extractText(inputData.getImage());

            // Translate the extracted text
            String translatedText = textTranslationUseCase.translate(extractedText, inputData.getTargetLanguage());

            // Prepare success output data
            ImageTranslationOutputData outputData = new ImageTranslationOutputData(translatedText);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            e.printStackTrace();
            outputBoundary.prepareFailView("Error occurred during image translation: " + e.getMessage());
        }
    }
}
