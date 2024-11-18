package use_case.text_translation;

import entity.Translation;
import external_services.TranslationServiceAdapter;

/**
 * Interactor for the Text Translation Use Case.
 */

public class TextTranslationInteractor extends TextTranslationUseCase implements TextTranslationInputBoundary {
    private final TranslationGateway translationGateway;
    private final TextTranslationOutputBoundary outputBoundary;

    /**
     * Constructs a TextTranslationInteractor with the necessary gateway and presenter.
     *
     * @param translationGateway gateway for accessing external translation services
     * @param outputBoundary used for presenting success or failure of the translation process
     */
    public TextTranslationInteractor(TranslationGateway translationGateway,
                                     TextTranslationOutputBoundary outputBoundary) {
        super(new TranslationServiceAdapter(translationGateway));
        this.translationGateway = translationGateway;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public TextTranslationResponseModel translate(TextTranslationRequestModel request) {
        try {
            // Use translationGateway directly instead of calling super.translate
            Translation translation = translationGateway.translateText(
                    request.getSourceText(),
                    request.getSourceLang(),  // Actually use the source language
                    request.getTargetLang()
            );

            // Create success response
            return outputBoundary.prepareSuccessView(new TextTranslationResponseModel(
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