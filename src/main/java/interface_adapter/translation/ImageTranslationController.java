package interface_adapter.translation;

import use_case.image_translation.ImageTranslationInputBoundary;
import use_case.image_translation.ImageTranslationInputData;

/**
 * Controller for the Image Translation Use Case.
 */
public class ChangePasswordController {
    private final ImageTranslationInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param username the user whose password to change
     */
    public void execute(String password, String username) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
