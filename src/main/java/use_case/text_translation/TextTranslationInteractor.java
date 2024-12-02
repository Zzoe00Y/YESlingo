package use_case.text_translation;

import java.util.logging.Logger;

import entity.Translation;
import entity.User;

/**
 * Interactor class that implements the text translation use case.
 * Handles the business logic for translating text and managing translation history.
 */
public class TextTranslationInteractor implements TextTranslationInputBoundary {
    private static final Logger LOGGER = Logger.getLogger(TextTranslationInteractor.class.getName());

    private final TextTranslationDataAccessInterface translationService;
    private final TextTranslationOutputBoundary outputBoundary;
    private final TextTranslationDataAccessInterface userDataAccessObject;

    /**
     * Creates a new TextTranslationInteractor.
     * @param translationService the service responsible for text translation, must not be null
     * @param outputBoundary the output boundary for presenting results, must not be null
     * @param userDataAccessObject the data access object for user operations, can be null
     */
    public TextTranslationInteractor(
            TextTranslationDataAccessInterface translationService,
            TextTranslationOutputBoundary outputBoundary,
            TextTranslationDataAccessInterface userDataAccessObject) {

        this.translationService = translationService;
        this.outputBoundary = outputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Translates the given text according to the input data specifications.
     * If a user is logged in (userDataAccessObject != null), the translation is saved to their history.
     *
     * @param inputData contains the source text, source language, target language, and username, must not be null
     * @throws RuntimeException if translation fails for any reason, which is caught and handled internally
     */
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

            if (userDataAccessObject != null) {
                final User user = userDataAccessObject.get(inputData.getUsername());
                user.getHistory().add("\n——————————————————————————\n"
                        + inputData.getSourceText() + "\n ---------------------------------------\n"
                        + translation.getTranslatedText() + "\n——————————————————————————\n");

                userDataAccessObject.save(user);
            }

            outputBoundary.prepareSuccessView(outputData);
        }
        catch (Exception exception) {
            LOGGER.severe("Error during translation: " + exception.getMessage());
            outputBoundary.prepareFailView("Translation failed: " + exception.getMessage());
        }
    }
}

