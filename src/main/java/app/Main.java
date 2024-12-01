package app;

import javax.swing.JFrame;

import external_services.FileTranslationService;
import external_services.TextTranslationService;
import external_services.SpeechToTextService;
import interface_adapter.file_translation.FileTranslationController;
import interface_adapter.file_translation.FileTranslationPresenter;
import interface_adapter.text_translation.TextTranslationController;
import interface_adapter.text_translation.TextTranslationPresenter;
import interface_adapter.voice_translation.VoiceTranslationController;  // IMPORT FOR VOICE TRANSLATION CONTROLLER
import interface_adapter.voice_translation.VoiceTranslationPresenter;  // IMPORT FOR VOICE TRANSLATION PRESENTER
import use_case.file_translation.FileTranslationInteractor;
import use_case.file_translation.FileTranslationOutputBoundary;
import use_case.text_translation.TextTranslationDataAccessInterface;
import use_case.text_translation.TextTranslationInteractor;
import use_case.text_translation.TextTranslationOutputBoundary;
import use_case.voice_translation.VoiceTranslationInteractor;  // VOICE TRANSLATION INTERACTOR IMPORT
import use_case.voice_translation.VoiceTranslationOutputBoundary;  // VOICE TRANSLATION OUTPUT BOUNDARY IMPORT
import view.*;

import java.io.IOException;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) throws IOException {
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

        LoggedInView loggedInView = appBuilder.getLoggedInView();

        TextTranslationDataAccessInterface translationService = new TextTranslationService();

        TextTranslationOutputBoundary textTranslationOutputBoundary =
                new TextTranslationPresenter(loggedInView);

        TextTranslationInteractor textTranslationInteractor =
                new TextTranslationInteractor(
                        translationService,
                        textTranslationOutputBoundary
                );

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

        SpeechToTextService speechToTextService = new SpeechToTextService();

        VoiceTranslationOutputBoundary voiceTranslationOutputBoundary = new VoiceTranslationPresenter(loggedInView);

        VoiceTranslationInteractor voiceTranslationInteractor = new VoiceTranslationInteractor(
                speechToTextService, voiceTranslationOutputBoundary);

        VoiceTranslationController voiceTranslationController = new VoiceTranslationController(voiceTranslationInteractor);

        loggedInView.setVoiceTranslationController(voiceTranslationController);
    }
}