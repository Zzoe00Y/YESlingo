package app;

import javax.swing.JFrame;

import entity.User;
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
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.profile.change_password.ChangePasswordController;
import interface_adapter.profile.change_password.ChangePasswordPresenter;
import interface_adapter.profile.change_password.ChangePasswordViewModel;
import use_case.image_translation.ImageTranslationInteractor;
import use_case.image_translation.ImageTranslationOutputBoundary;
import use_case.loggedin.LoggedInInputBoundary;
import use_case.loggedin.LoggedInInteractor;
import use_case.loggedin.LoggedInOutputBoundary;
import use_case.loggedin.LoggedInUserDataAccessInterface;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileUserDataAccessInterface;
import use_case.profile.change_password.ChangePasswordInteractor;
import use_case.profile.change_password.ChangePasswordUserDataAccessInterface;
import use_case.text_translation.TextTranslationUseCase;
import view.ChangePasswordView;
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
                                            .addChangePasswordView()
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
        UserFactory userFactory = appBuilder.getUserFactory();

        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel, loggedInViewModel, profileViewModel);
        LoggedInInteractor loggedInInteractor = new LoggedInInteractor(loggedInOutputBoundary, userFactory);
        LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        loggedInView.setLoggedInController(loggedInController);


        ProfileView profileView = appBuilder.getProfileView();
        ChangePasswordViewModel changePasswordViewModel = appBuilder.getChangePasswordViewModel();
        LoginViewModel loginViewModel = appBuilder.getLoginViewModel();

        ProfilePresenter profilePresenter = new ProfilePresenter(viewManagerModel, loggedInViewModel, profileViewModel, changePasswordViewModel, loginViewModel);
        ProfileInteractor profileInteractor = new ProfileInteractor(profilePresenter, userFactory);
        ProfileController profileController = new ProfileController(profileInteractor);
        profileView.setProfileController(profileController);


        ChangePasswordView changePasswordView = appBuilder.getChangePasswordView();

        ChangePasswordPresenter changePasswordPresenter = new ChangePasswordPresenter(viewManagerModel, changePasswordViewModel, profileViewModel);
        ChangePasswordInteractor changePasswordUseCaseInteractor = new ChangePasswordInteractor(changePasswordPresenter, userFactory);
        ChangePasswordController changePasswordController = new ChangePasswordController(changePasswordUseCaseInteractor);
        changePasswordView.setChangePasswordController(changePasswordController);
    }
}
