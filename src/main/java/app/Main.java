package app;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;

import javax.swing.JFrame;

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
        final UserFactory userFactory = new CommonUserFactory();
        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        final AppBuilder appBuilder = new AppBuilder(userFactory, userDataAccessObject);
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
                .addChatBotUseCase()
                .addTextTranslationUseCase()
                .addVoiceTranslationUseCase()
                .addFileTranslationUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
