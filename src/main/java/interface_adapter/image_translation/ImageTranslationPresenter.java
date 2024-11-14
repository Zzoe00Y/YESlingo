package interface_adapter.image_translation;

import interface_adapter.translation.TranslationViewInterface;
import use_case.image_translation.ImageTranslationOutputBoundary;
import use_case.image_translation.ImageTranslationOutputData;

/**
 * Presenter for the Image Translation Use Case.
 * This class handles formatting the output data and communicating with the view.
 */
public class ImageTranslationPresenter implements ImageTranslationOutputBoundary {

    private TranslationViewInterface view;

    /**
     * Constructs an ImageTranslationPresenter with the specified view interface.
     *
     * @param view the view interface that this presenter will update
     */
    public ImageTranslationPresenter(TranslationViewInterface view) {
        this.view = view;
    }

    /**
     * Prepares and displays the success view when the image translation is successful.
     *
     * @param outputData The data containing the translated text to display.
     */
    @Override
    public void prepareSuccessView(ImageTranslationOutputData outputData) {
        String formattedText = "Translation: " + outputData.getTranslatedText();
        view.displayTranslation(formattedText);
    }

    /**
     * Prepares and displays the failure view when there is an error.
     *
     * @param error The error message to display.
     */
    @Override
    public void prepareFailView(String error) {
        String formattedError = "Error: " + error;
        view.displayError(formattedError);
    }
}
