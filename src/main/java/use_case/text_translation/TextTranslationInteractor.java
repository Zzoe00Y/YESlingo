package use_case.text_translation;

import entity.Translation;
import entity.User;
import external_services.TranslationServiceAdapter;
import use_case.chatbot.ChatBotUserDataAccessInterface;

/**
 * Interactor for the Text Translation Use Case.
 */

public class TextTranslationInteractor extends TextTranslationUseCase implements TextTranslationInputBoundary {

    private final TextTranslationDataAccessInterface userDataAccessObject;
    private final TextTranslationDataAccessInterface textTranslationDataAccessInterface;
    private final TextTranslationOutputBoundary outputBoundary;


    /**
     * Constructs a TextTranslationInteractor with the necessary gateway and presenter.
     *
     * @param userDataAccessObject gateway for accessing external translation services
     * @param outputBoundary used for presenting success or failure of the translation process
     */
    public TextTranslationInteractor(TextTranslationDataAccessInterface userDataAccessObject,
                                     TextTranslationOutputBoundary outputBoundary) {
        super(new TranslationServiceAdapter(userDataAccessObject));
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
        this.textTranslationDataAccessInterface = userDataAccessObject;
    }

    @Override
    public TextTranslationOutputData translate(TextTranslationInputData request) {
        try {
            // Use translationGateway directly instead of calling super.translate
            Translation translation = textTranslationDataAccessInterface.translateText(
                    request.getSourceText(),
                    request.getSourceLang(),  // Actually use the source language
                    request.getTargetLang()
            );

            final User user = userDataAccessObject.get(request.getUsername());
            user.getHistory().add("\n——————————————————————————\n" +
                    request.getSourceText() + "\n ---------------------------------------\n"
                    + translation.getTranslatedText() + "\n——————————————————————————\n");
            userDataAccessObject.save(user);

            // Create success response
            return outputBoundary.prepareSuccessView(new TextTranslationOutputData(
                    request.getSourceText(),
                    translation.getTranslatedText(),
                    request.getSourceLang(),
                    request.getTargetLang()
            ));
        } catch (Exception e) {
            return outputBoundary.prepareFailView("Translation failed: " + e.getMessage());
        }
    }
}