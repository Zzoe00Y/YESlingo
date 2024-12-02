package use_case.text_translation;

import java.util.logging.Logger;

import entity.Translation;

public class TextTranslationInteractor implements TextTranslationInputBoundary {
    private static final Logger logger = Logger.getLogger(TextTranslationInteractor.class.getName());

    private final TextTranslationDataAccessInterface translationService;
    private final TextTranslationOutputBoundary outputBoundary;

    public TextTranslationInteractor(
            TextTranslationDataAccessInterface translationService,
            TextTranslationOutputBoundary outputBoundary) {
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

            logger.info("Translation successful! Translated text: " + translation.getTranslatedText());

            final TextTranslationOutputData outputData = new TextTranslationOutputData(
                    translation.getSourceText(),
                    translation.getTranslatedText(),
                    translation.getSourceLang(),
                    translation.getTargetLang()
            );

            outputBoundary.prepareSuccessView(outputData);

        }
        catch (Exception e) {
            logger.severe("Error during translation: " + e.getMessage());
            outputBoundary.prepareFailView("Translation failed: " + e.getMessage());
        }
    }
}
