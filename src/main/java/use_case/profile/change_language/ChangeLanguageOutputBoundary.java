package use_case.profile.change_language;

/**
 * The output boundary for the Change Language Use Case.
 */
public interface ChangeLanguageOutputBoundary {

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param changeLanguageOutputData the output data
     */
    void prepareSuccessView(ChangeLanguageOutputData changeLanguageOutputData);
}
