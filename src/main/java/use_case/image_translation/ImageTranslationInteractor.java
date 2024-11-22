package use_case.image_translation;

import external_services.ImageToTextService;
import use_case.text_translation.TextTranslationInteractor;
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
    public void translate(ImageTranslationInputData inputData) {
        try {
            String extractedText = imageToTextService.extractText(inputData.getImage());
            String translatedText = textTranslationUseCase.translate(extractedText, inputData.getTargetLanguage());

            ImageTranslationOutputData outputData = new ImageTranslationOutputData(translatedText);
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            e.printStackTrace();
            outputBoundary.prepareFailView("Translation failed: " + e.getMessage());
        }
    }
}
