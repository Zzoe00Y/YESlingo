package use_case.image_translation;

/**
 * Output boundary for the Image Translation Use Case.
 * This interface defines methods for presenting the success and failure results of image translation.
 */
public interface ImageTranslationOutputBoundary {

    /**
     * Prepares the success view with the translated text result.
     *
     * @param outputData The data containing the translated text to display.
     */
    void prepareSuccessView(ImageTranslationOutputData outputData);

    /**
     * Prepares the failure view with an error message.
     *
     * @param error The error message to display.
     */
    void prepareFailView(String error);
}
