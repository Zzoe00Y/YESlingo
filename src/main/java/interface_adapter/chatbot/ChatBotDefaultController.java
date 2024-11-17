package interface_adapter.chatbot;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The controller for the Chatbot Use Case.
 */
public class ChatBotDefaultController {

    private final LoginInputBoundary loginUseCaseInteractor;

    public ChatBotDefaultController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    public void switchToSignupView() {
        loginUseCaseInteractor.switchToSignupView();
    }
}
