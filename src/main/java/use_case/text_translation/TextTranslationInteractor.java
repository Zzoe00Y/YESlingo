package use_case.text_translation;

import entity.Translation;
import external_services.TranslationServiceAdapter;

/**
 * Interactor for the Text Translation Use Case.
 */

public class TextTranslationInteractor extends TextTranslationUseCase implements TextTranslationInputBoundary {
    private final TextTranslationDataAccessInterface textTranslationDataAccessInterface;
    private final TextTranslationOutputBoundary outputBoundary;

    /**
     * Constructs a TextTranslationInteractor with the necessary gateway and presenter.
     *
     * @param textTranslationDataAccessInterface gateway for accessing external translation services
     * @param outputBoundary used for presenting success or failure of the translation process
     */
    public TextTranslationInteractor(TextTranslationDataAccessInterface textTranslationDataAccessInterface,
                                     TextTranslationOutputBoundary outputBoundary) {
        super(new TranslationServiceAdapter(textTranslationDataAccessInterface));
        this.textTranslationDataAccessInterface = textTranslationDataAccessInterface;
        this.outputBoundary = outputBoundary;
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