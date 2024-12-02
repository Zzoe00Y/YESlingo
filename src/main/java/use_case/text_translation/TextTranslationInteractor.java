package use_case.text_translation;

import java.util.logging.Logger;

import entity.Translation;
import entity.User;

public class TextTranslationInteractor implements TextTranslationInputBoundary {
    private static final Logger logger = Logger.getLogger(TextTranslationInteractor.class.getName());

    private final TextTranslationDataAccessInterface translationService;
    private final TextTranslationOutputBoundary outputBoundary;
    private final TextTranslationDataAccessInterface userDataAccessObject;

    public TextTranslationInteractor(
            TextTranslationDataAccessInterface translationService,
            TextTranslationOutputBoundary outputBoundary,
            TextTranslationDataAccessInterface userDataAccessObject) {
        this.translationService = translationService;
        this.outputBoundary = outputBoundary;
        this.userDataAccessObject = userDataAccessObject;
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


            if(userDataAccessObject != null) {
                final User user = userDataAccessObject.get(inputData.getUsername());
                user.getHistory().add("\n——————————————————————————\n" +
                        inputData.getSourceText() + "\n ---------------------------------------\n"
                        + translation.getTranslatedText() + "\n——————————————————————————\n");
                userDataAccessObject.save(user);
            }

            outputBoundary.prepareSuccessView(outputData);

        }
        catch (Exception e) {
            logger.severe("Error during translation: " + e.getMessage());
            outputBoundary.prepareFailView("Translation failed: " + e.getMessage());
        }
    }
}
