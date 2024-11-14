package use_case.image_translation;

public interface ImageTranslationInputBoundary {
    /**
     * Executes the image translation use case.
     * @param inputData The input data for the image translation, including the image and target language.
     */
    void execute(ImageTranslationInputData inputData);
}