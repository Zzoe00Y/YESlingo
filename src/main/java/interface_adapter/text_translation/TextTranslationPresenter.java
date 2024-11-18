package interface_adapter.text_translation;

import view.LoggedInView;
import use_case.text_translation.TextTranslationOutputBoundary;
import use_case.text_translation.TextTranslationResponseModel;

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
     * Validates the response and handles any presentation-layer errors.
     *
     * @param response the response model containing translation results
     * @return TextTranslationResponseModel either the original response or an error response
     */
    @Override
    public TextTranslationResponseModel prepareSuccessView(TextTranslationResponseModel response) {
        if (response == null || response.getTranslatedText() == null) {
            return prepareFailView("Invalid translation response");
        }

        try {
            String translatedText = response.getTranslatedText().trim();
            if (translatedText.isEmpty()) {
                return prepareFailView("Empty translation result");
            }

            System.out.println("Preparing success view with translation: " + translatedText);
            view.displayTranslation(translatedText);
            return response;
        } catch (Exception e) {
            System.err.println("Error in success view preparation: " + e.getMessage());
            e.printStackTrace();
            return prepareFailView("Error displaying translation: " + e.getMessage());
        }
    }

    /**
     * Prepares and displays the failure view when a translation operation fails.
     * Handles error presentation and provides fallback error handling.
     *
     * @param error the error message describing what went wrong
     * @return TextTranslationResponseModel containing error information
     */
    @Override
    public TextTranslationResponseModel prepareFailView(String error) {
        try {
            String errorMessage = "Translation failed: " + (error != null ? error : "Unknown error");
            System.err.println("Preparing fail view: " + errorMessage);

            // Display error in the view
            view.displayError(errorMessage);

            // Create error response model
            return new TextTranslationResponseModel(
                    "", // source text
                    "Translation Error: " + error, // translated text
                    "", // source language
                    ""  // target language
            );
        } catch (Exception e) {
            System.err.println("Error in presenter error handling: " + e.getMessage());
            e.printStackTrace();

            // Try one last time to show error to user
            try {
                view.displayError("Critical error in translation system. Please try again.");
            } catch (Exception ignored) {
                // At this point we can't do much more
                System.err.println("Failed to display error to user");
            }

            return new TextTranslationResponseModel(
                    "",
                    "System Error",
                    "",
                    ""
            );
        }
    }
}