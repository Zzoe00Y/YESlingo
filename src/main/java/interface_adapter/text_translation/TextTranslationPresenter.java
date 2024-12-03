package interface_adapter.text_translation;

import use_case.text_translation.TextTranslationOutputBoundary;
import use_case.text_translation.TextTranslationOutputData;
import view.LoggedInView;

/**
 * Presenter for the Text Translation feature.
 * Handles the presentation logic for displaying translation results and errors to the user.
 */
public class TextTranslationPresenter implements TextTranslationOutputBoundary {
    private final LoggedInView view;

    /**
     * Constructs a TextTranslationPresenter with the specified view.
     *
     * @param view the view interface for displaying results to the user
     */
    public TextTranslationPresenter(LoggedInView view) {
        this.view = view;
    }

    /**
     * Prepares and displays the success view for a successful translation.
     *
     * @param translation the translation output data
     */
    @Override
    public void prepareSuccessView(TextTranslationOutputData translation) {
        if (translation == null || translation.getTranslatedText() == null) {
            prepareFailView("Invalid translation response");
            return;
        }

        try {
            final String translatedText = translation.getTranslatedText().trim();
            if (translatedText.isEmpty()) {
                prepareFailView("Empty translation result");
                return;
            }

            System.out.println("Preparing success view with translation: " + translatedText);
            view.displayTranslation(translatedText);
        }
        catch (Exception exception) {
            System.err.println("Error in success view preparation: " + exception.getMessage());
            exception.printStackTrace();
            prepareFailView("Error displaying translation: " + exception.getMessage());
        }
    }

    /**
     * Prepares and displays the failure view when a translation operation fails.
     *
     * @param error the error message
     */
    @Override
    public void prepareFailView(String error) {
        try {
            final String errorMessage = "Translation failed: " + (error != null ? error : "Unknown error");
            System.err.println("Preparing fail view: " + errorMessage);
            view.displayError(errorMessage);
        }
        catch (Exception exception) {
            System.err.println("Error in presenter error handling: " + exception.getMessage());
            exception.printStackTrace();
            try {
                view.displayError("Critical error in translation system. Please try again.");
            }
            catch (Exception ignored) {
                System.err.println("Failed to display error to user");
            }
        }
    }
}
