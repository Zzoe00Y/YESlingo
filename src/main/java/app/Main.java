package app;

import javax.swing.JFrame;

import entity.User;
import entity.UserFactory;
import external_services.FileTranslationService;
import external_services.MyMemoryGateway;
import external_services.TextToTextTranslationService;
import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.ChatBotViewModel;
import interface_adapter.file_translation.FileTranslationController;
import interface_adapter.file_translation.FileTranslationPresenter;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.loggedin_homepage.LoggedInController;
import interface_adapter.loggedin_homepage.LoggedInPresenter;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.change_language.ChangeLanguageController;
import interface_adapter.profile.change_language.ChangeLanguagePresenter;
import interface_adapter.profile.change_language.ChangeLanguageViewModel;
import interface_adapter.profile.change_password.ChangePasswordController;
import interface_adapter.profile.change_password.ChangePasswordPresenter;
import interface_adapter.profile.change_password.ChangePasswordViewModel;
import interface_adapter.text_translation.TextTranslationController;
import interface_adapter.text_translation.TextTranslationPresenter;
import use_case.file_translation.FileTranslationInteractor;
import use_case.file_translation.FileTranslationOutputBoundary;
import use_case.history.HistoryInteractor;
import use_case.history.HistoryOutputBoundary;
import use_case.loggedin.LoggedInInputBoundary;
import use_case.loggedin.LoggedInInteractor;
import use_case.loggedin.LoggedInOutputBoundary;
import use_case.loggedin.LoggedInUserDataAccessInterface;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileUserDataAccessInterface;
import use_case.profile.change_language.ChangeLanguageInputBoundary;
import use_case.profile.change_language.ChangeLanguageInteractor;
import use_case.profile.change_password.ChangePasswordInteractor;
import use_case.profile.change_password.ChangePasswordUserDataAccessInterface;
import use_case.text_translation.TextTranslationDataAccessInterface;
import use_case.text_translation.TextTranslationInteractor;
import use_case.text_translation.TextTranslationOutputBoundary;
import use_case.text_translation.TextTranslationUseCase;
import view.*;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        // add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addProfileView()
                .addChangePasswordView()
                .addChangeLanguageView()
                .addHistoryView()
                .addChatBotView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLoggedinUseCase()
                .addProfileUseCase()
                .addChangePasswordUseCase()
                .addChangeLanguageUseCase()
                .addHistoryUseCase()
                .addHistoryUseCase()
                .addChatBotUseCase()
                .build();

        application.pack();
        application.setVisible(true);

        // Get reference to LoggedInView
        LoggedInView loggedInView = appBuilder.getLoggedInView();

        // Initialize gateway and service for translation

        // Set up text translation components
        TextTranslationOutputBoundary textTranslationOutputBoundary =
                new TextTranslationPresenter(loggedInView);

        TextTranslationInteractor textTranslationInteractor =
                new TextTranslationInteractor(appBuilder.getUserDataAccessObject(), textTranslationOutputBoundary);

        TextTranslationController textTranslationController =
                new TextTranslationController(textTranslationInteractor);

        loggedInView.setTextTranslationController(textTranslationController);

        FileTranslationService fileTranslationService = new FileTranslationService();

        FileTranslationOutputBoundary fileTranslationOutputBoundary =
                new FileTranslationPresenter(loggedInView);

        FileTranslationInteractor fileTranslationInteractor =
                new FileTranslationInteractor(
                        fileTranslationService,
                        fileTranslationOutputBoundary
                );

        FileTranslationController fileTranslationController =
                new FileTranslationController(fileTranslationInteractor);

        loggedInView.setFileTranslationController(fileTranslationController);
    }
}