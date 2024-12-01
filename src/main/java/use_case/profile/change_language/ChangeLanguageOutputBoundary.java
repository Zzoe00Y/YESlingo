package use_case.profile.change_language;

/**
 * The output boundary for the Change Language Use Case.
 */
public interface ChangeLanguageOutputBoundary {

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();

    void prepareSuccessView(ChangeLanguageOutputData changeLanguageOutputData);
}
