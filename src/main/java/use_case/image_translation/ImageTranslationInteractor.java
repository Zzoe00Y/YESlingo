package use_case.image_translation;

import entity.Translation;
import external_services.ImageToTextService;
import use_case.text_translation.TextTranslationUseCase;
import java.util.logging.Logger;

/**
 * Interactor for the Image Translation Use Case.
 */
public class ImageTranslationInteractor implements ImageTranslationInputBoundary {

    private static final Logger logger = Logger.getLogger(ImageTranslationInteractor.class.getName());

    private final ImageToTextService imageToTextService;
    private final TextTranslationUseCase textTranslationUseCase;
    private final ImageTranslationOutputBoundary outputBoundary;

    /**
     * Constructs an ImageTranslationInteractor with the necessary services and presenter.
     *
     * @param imageToTextService     used for extracting text from an image
     * @param textTranslationUseCase Use case to translate extracted text into the target language
     * @param outputBoundary         used for presenting success or failure of the image translation process
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
            // Extract text from the image
            String extractedText = imageToTextService.extractText(inputData.getImage());

            // Translate the extracted text
            Translation translation = textTranslationUseCase.translate(extractedText, inputData.getTargetLanguage());

            // Prepare output with the translated text
            ImageTranslationOutputData outputData = new ImageTranslationOutputData(translation.getTranslatedText());
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            e.printStackTrace();
            outputBoundary.prepareFailView("Translation failed: " + e.getMessage());
        }
    }
}

