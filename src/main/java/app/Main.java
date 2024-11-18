package app;

import javax.swing.JFrame;

import entity.UserFactory;
import external_services.NanonetsImageToTextService;
import external_services.TextToTextTranslationService;
import interface_adapter.ViewManagerModel;
import interface_adapter.image_translation.ImageTranslationController;
import interface_adapter.image_translation.ImageTranslationPresenter;
import interface_adapter.loggedin_homepage.LoggedInController;
import interface_adapter.loggedin_homepage.LoggedInPresenter;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.image_translation.ImageTranslationInteractor;
import use_case.image_translation.ImageTranslationOutputBoundary;
import use_case.loggedin.LoggedInInputBoundary;
import use_case.loggedin.LoggedInInteractor;
import use_case.loggedin.LoggedInOutputBoundary;
import use_case.loggedin.LoggedInUserDataAccessInterface;
import use_case.text_translation.TextTranslationUseCase;
import view.LoggedInView;
import view.ProfileView;
import view.ViewManager;

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
                                            .addChatBotView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
//                                            .addLoggedinUseCase()
                                            //.addProfileUseCase()
                                            .addChatBotUseCase()
//                                            .addChangePasswordUseCase()
//                                            .addLogoutUseCase()
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


        ViewManagerModel viewManagerModel = appBuilder.getViewManagerModel();
        LoggedInViewModel loggedInViewModel = appBuilder.getLoggedInViewModel();
        ProfileViewModel profileViewModel = appBuilder.getProfileViewModel();
        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel, loggedInViewModel, profileViewModel);

        UserFactory userFactory = appBuilder.getUserFactory();
        LoggedInInteractor loggedInInteractor = new LoggedInInteractor(loggedInOutputBoundary, userFactory);

        LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        loggedInView.setLoggedInController(loggedInController);
    }
}
