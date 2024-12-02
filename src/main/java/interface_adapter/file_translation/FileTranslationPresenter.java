package interface_adapter.file_translation;

import interface_adapter.translation.TranslationViewInterface;
import use_case.file_translation.FileTranslationOutputBoundary;
import use_case.file_translation.FileTranslationOutputData;

/**
 * Presenter for the File Translation Use Case.
 * Converts the raw output data into a user-friendly format for the view.
 */
public class FileTranslationPresenter implements FileTranslationOutputBoundary {

    private final TranslationViewInterface view;

    /**
     * Constructs a FileTranslationPresenter with the specified view.
     *
     * @param view The view to display the results or errors.
     */
    public FileTranslationPresenter(TranslationViewInterface view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(FileTranslationOutputData outputData) {
        // Display the translated file URL to the user
        final String translatedFileUrl = outputData.getTranslatedFileUrl();
        final String successMessage = translatedFileUrl;
        System.out.println("Presenter: " + successMessage);

        // Call the view interface to update the UI
        view.displayTranslation(successMessage);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Log the error and display it in the view
        System.err.println("Presenter Error: " + errorMessage);

        // Call the view interface to update the UI with an error
        view.displayError("Translation failed: " + errorMessage);
    }
}
