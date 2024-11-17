package app;

import javax.swing.JFrame;

import external_services.NanonetsImageToTextService;
import external_services.TextToTextTranslationService;
import interface_adapter.image_translation.ImageTranslationController;
import interface_adapter.image_translation.ImageTranslationPresenter;
import use_case.image_translation.ImageTranslationInteractor;
import use_case.image_translation.ImageTranslationOutputBoundary;
import use_case.text_translation.TextTranslationUseCase;
import view.LoggedInView;

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
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChatBotView()
//                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);

        NanonetsImageToTextService imageToTextService = new NanonetsImageToTextService();
        TextTranslationUseCase textTranslationUseCase = new TextTranslationUseCase(new TextToTextTranslationService());

        LoggedInView loggedInView = appBuilder.getLoggedInView();

        ImageTranslationOutputBoundary outputBoundary = new ImageTranslationPresenter(loggedInView);

        ImageTranslationInteractor imageTranslationInteractor = new ImageTranslationInteractor(
                imageToTextService,
                textTranslationUseCase,
                outputBoundary
        );

        ImageTranslationController imageTranslationController = new ImageTranslationController(imageTranslationInteractor);
        loggedInView.setImageTranslationController(imageTranslationController);
    }
}
