package app;

import javax.swing.*;

import external_services.MyMemoryGateway;
import external_services.NanonetsImageToTextService;
import interface_adapter.image_translation.ImageTranslationController;
import interface_adapter.image_translation.ImageTranslationPresenter;
import interface_adapter.text_translation.TextTranslationController;
import interface_adapter.text_translation.TextTranslationPresenter;
import use_case.image_translation.ImageTranslationInteractor;
import use_case.image_translation.ImageTranslationOutputBoundary;
import use_case.text_translation.TextTranslationInteractor;
import use_case.text_translation.TextTranslationOutputBoundary;
import use_case.text_translation.TranslationGateway;
import view.LoggedInView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize app builder
                final AppBuilder appBuilder = new AppBuilder();

                // Build basic application frame
                final JFrame application = appBuilder
                        .addLoginView()
                        .addSignupView()
                        .addLoggedInView()
                        .addSignupUseCase()
                        .addLoginUseCase()
                        .addLogoutUseCase()
                        .build();

                // Get reference to LoggedInView
                LoggedInView loggedInView = appBuilder.getLoggedInView();

                // Initialize gateway and service for translation
                TranslationGateway translationGateway = new MyMemoryGateway();

                // Set up text translation components
                TextTranslationOutputBoundary textTranslationOutputBoundary =
                        new TextTranslationPresenter(loggedInView);

                TextTranslationInteractor textTranslationInteractor =
                        new TextTranslationInteractor(translationGateway, textTranslationOutputBoundary);

                TextTranslationController textTranslationController =
                        new TextTranslationController(textTranslationInteractor);

                // Important: Set the text translation controller in the view
                loggedInView.setTextTranslationController(textTranslationController);

                // Set up image translation components
                NanonetsImageToTextService imageToTextService = new NanonetsImageToTextService();

                ImageTranslationOutputBoundary imageTranslationOutputBoundary =
                        new ImageTranslationPresenter(loggedInView);

                ImageTranslationInteractor imageTranslationInteractor =
                        new ImageTranslationInteractor(
                                imageToTextService,
                                textTranslationInteractor,
                                imageTranslationOutputBoundary
                        );

                ImageTranslationController imageTranslationController =
                        new ImageTranslationController(imageTranslationInteractor);

                loggedInView.setImageTranslationController(imageTranslationController);

                // Make the frame visible
                application.pack();
                application.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
}