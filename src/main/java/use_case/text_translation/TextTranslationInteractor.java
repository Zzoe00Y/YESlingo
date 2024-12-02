package use_case.text_translation;

import java.util.logging.Logger;

import entity.Translation;

/**
 * Implementation of TextTranslationInputBoundary that coordinates the translation process.
 */
public class TextTranslationInteractor implements TextTranslationInputBoundary {
    private static final Logger LOGGER = Logger.getLogger(TextTranslationInteractor.class.getName());

    private final TextTranslationDataAccessInterface translationService;
    private final TextTranslationOutputBoundary outputBoundary;

    /**
     * Creates a new TextTranslationInteractor with the specified dependencies.
     *
     * @param translationService the service handling translation operations
     * @param outputBoundary the output boundary for presenting results
     * @throws IllegalArgumentException if any parameter is null
     */
    public TextTranslationInteractor(
            TextTranslationDataAccessInterface translationService,
            TextTranslationOutputBoundary outputBoundary) {
        if (translationService == null || outputBoundary == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }
        this.translationService = translationService;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void translate(TextTranslationInputData inputData) {
        try {
            final Translation translation = translationService.translateText(
                    inputData.getSourceText(),
                    inputData.getSourceLang(),
                    inputData.getTargetLang()
            );

            LOGGER.info("Translation successful! Translated text: " + translation.getTranslatedText());

            final TextTranslationOutputData outputData = new TextTranslationOutputData(
                    translation.getSourceText(),
                    translation.getTranslatedText(),
                    translation.getSourceLang(),
                    translation.getTargetLang()
            );

            outputBoundary.prepareSuccessView(outputData);
        }
        catch (RuntimeException ex) {
            LOGGER.severe("Error during translation: " + ex.getMessage());
            outputBoundary.prepareFailView("Translation failed: " + ex.getMessage());
        }
    }
}
