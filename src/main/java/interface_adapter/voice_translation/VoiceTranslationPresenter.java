package interface_adapter.voice_translation;

import use_case.voice_translation.VoiceTranslationOutputBoundary;
import use_case.voice_translation.VoiceTranslationOutputData;
import interface_adapter.translation.TranslationViewInterface;

/**
 * Presenter for the Voice Translation use case.
 * Converts the raw output data into a user-friendly format for the view.
 */
public class VoiceTranslationPresenter implements VoiceTranslationOutputBoundary {

    private final TranslationViewInterface view;

    /**
     * Constructs a VoiceTranslationPresenter with the specified view.
     *
     * @param view The view to display the results or errors.
     */
    public VoiceTranslationPresenter(TranslationViewInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(VoiceTranslationOutputData outputData) {
        // Display the translated text to the user
        String translatedText = outputData.getRecognizedText();
        System.out.println("Presenter: Speech to text succesful! result: " + translatedText);

        // Call the view interface to update the UI with the translation
        view.displayRecognizedText(translatedText);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Log the error and display it in the view
        System.err.println("Presenter Error: " + errorMessage);

        // Call the view interface to update the UI with an error message
        view.displayError("Translation failed: " + errorMessage);
    }
}
