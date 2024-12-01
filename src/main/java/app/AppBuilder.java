package app;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import external_services.FileTranslationService;
import external_services.SpeechToTextService;
import external_services.TextTranslationService;
import interface_adapter.ViewManagerModel;
import interface_adapter.file_translation.FileTranslationController;
import interface_adapter.file_translation.FileTranslationPresenter;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.loggedin_homepage.LoggedInController;
import interface_adapter.loggedin_homepage.LoggedInPresenter;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.chatbot.ChatBotController;
import interface_adapter.chatbot.ChatBotPresenter;
import interface_adapter.chatbot.ChatBotViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
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
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.text_translation.TextTranslationController;
import interface_adapter.text_translation.TextTranslationPresenter;
import interface_adapter.voice_translation.VoiceTranslationController;
import interface_adapter.voice_translation.VoiceTranslationPresenter;
import use_case.chatbot.ChatBotInputBoundary;
import use_case.chatbot.ChatBotInteractor;
import use_case.chatbot.ChatBotOutputBoundary;
import use_case.file_translation.FileTranslationInteractor;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInteractor;
import use_case.history.HistoryOutputBoundary;
import use_case.loggedin.LoggedInInputBoundary;
import use_case.loggedin.LoggedInInteractor;
import use_case.loggedin.LoggedInOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.change_language.ChangeLanguageInputBoundary;
import use_case.profile.change_language.ChangeLanguageInteractor;
import use_case.profile.change_language.ChangeLanguageOutputBoundary;
import use_case.profile.change_password.ChangePasswordInputBoundary;
import use_case.profile.change_password.ChangePasswordInteractor;
import use_case.profile.change_password.ChangePasswordOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.text_translation.TextTranslationDataAccessInterface;
import use_case.text_translation.TextTranslationInteractor;
import use_case.text_translation.TextTranslationOutputBoundary;
import use_case.voice_translation.VoiceTranslationInteractor;
import view.*;
import use_case.voice_translation.VoiceTranslationInteractor;


/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private ChatBotViewModel chatBotViewModel;
    private ChatBotDefaultView chatBotView;
    private ProfileViewModel profileViewModel;
    private ProfileView profileView;
    private ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordView changePasswordView;
    private ChangeLanguageViewModel changeLanguageViewModel;
    private ChangeLanguageView changeLanguageView;
    private HistoryViewModel historyViewModel;
    private HistoryView historyView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    public InMemoryUserDataAccessObject getUserDataAccessObject() {
        return userDataAccessObject;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the Profile View to the application.
     * @return this builder
     */
    public AppBuilder addProfileView() {
        profileViewModel = new ProfileViewModel();
        profileView = new ProfileView(profileViewModel);
        cardPanel.add(profileView, profileView.getViewName());
        return this;
    }

    /**
     * Adds the Profile View to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordView() {
        changePasswordViewModel = new ChangePasswordViewModel();
        changePasswordView = new ChangePasswordView(changePasswordViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;
    }

    /**
     * Adds the Profile View to the application.
     * @return this builder
     */
    public AppBuilder addChangeLanguageView() {
        changeLanguageViewModel = new ChangeLanguageViewModel();
        changeLanguageView = new ChangeLanguageView(changeLanguageViewModel);
        cardPanel.add(changeLanguageView, changeLanguageView.getViewName());
        return this;
    }

    /**
     * Adds the Profile View to the application.
     * @return this builder
     */
    public AppBuilder addHistoryView() {
        historyViewModel = new HistoryViewModel();
        historyView = new HistoryView(historyViewModel);
        cardPanel.add(historyView, historyView.getViewName());
        return this;
    }

    /**
     * Adds the ChatBot View to the application.
     * @return this builder
     */
    public AppBuilder addChatBotView() {
        chatBotViewModel = new ChatBotViewModel();
        chatBotView = new ChatBotDefaultView(chatBotViewModel);
        cardPanel.add(chatBotView, chatBotView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, signupViewModel, profileViewModel, changePasswordViewModel, changeLanguageViewModel);
      
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoggedinUseCase() {
        final LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel,
                loggedInViewModel, profileViewModel, historyViewModel, chatBotViewModel);
        final LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(loggedInOutputBoundary, userFactory);

        final LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        loggedInView.setLoggedInController(loggedInController);
        return this;
    }

    /**
     * Adds the Profile Use Case to the application.
     * @return this builder
     */
    public AppBuilder addProfileUseCase() {
        final ProfileOutputBoundary profileOutputBoundary = new ProfilePresenter(viewManagerModel,
                loggedInViewModel, changePasswordViewModel, loginViewModel, changeLanguageViewModel);

        final ProfileInputBoundary profileInteractor = new ProfileInteractor(profileOutputBoundary);

        final ProfileController controller = new ProfileController(profileInteractor);
        profileView.setProfileController(controller);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary = new ChangePasswordPresenter(viewManagerModel, changePasswordViewModel, profileViewModel, loginViewModel);
        final ChangePasswordInputBoundary changePasswordInteractor = new ChangePasswordInteractor(changePasswordOutputBoundary, userDataAccessObject);

        final ChangePasswordController controller = new ChangePasswordController(changePasswordInteractor);
        changePasswordView.setChangePasswordController(controller);
        return this;
    }

    /**
     * Adds the Change Language Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangeLanguageUseCase() {
        final ChangeLanguageOutputBoundary changeLanguageOutputBoundary = new ChangeLanguagePresenter(viewManagerModel, changeLanguageViewModel, profileViewModel, loggedInViewModel);
        final ChangeLanguageInputBoundary changeLanguageInteractor = new ChangeLanguageInteractor(changeLanguageOutputBoundary, userDataAccessObject);

        final ChangeLanguageController controller = new ChangeLanguageController(changeLanguageInteractor);
        changeLanguageView.setChangeLanguageController(controller);
        return this;
    }

    /**
     * Adds the History Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHistoryUseCase() {
        final HistoryOutputBoundary historyOutputBoundary = new HistoryPresenter(viewManagerModel, historyViewModel, loggedInViewModel);
        final HistoryInputBoundary historyInteractor = new HistoryInteractor(userDataAccessObject, historyOutputBoundary, userFactory);


        final HistoryController controller = new HistoryController(historyInteractor);
        historyView.setHistoryController(controller);
        return this;
    }

    /**
     * Adds the ChatBot Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChatBotUseCase() {
        final ChatBotOutputBoundary chatBotOutputBoundary = new ChatBotPresenter(viewManagerModel,
                chatBotViewModel, loggedInViewModel);
        final ChatBotInputBoundary userChatBotInteractor = new ChatBotInteractor(
                userDataAccessObject, chatBotOutputBoundary, userFactory);

        final ChatBotController controller = new ChatBotController(userChatBotInteractor);
        chatBotView.setChatBotController(controller);
        return this;
    }

    /**
     * Adds the File Translation Use Case to the application.
     * @return this builder
     */
    public AppBuilder addFileTranslationUseCase() {
        FileTranslationInteractor fileTranslationInteractor = createFileTranslationInteractor();
        FileTranslationController fileTranslationController = new FileTranslationController(fileTranslationInteractor);

        // Inject the controller into the LoggedInView
        loggedInView.setFileTranslationController(fileTranslationController);

        return this;
    }

    private FileTranslationInteractor createFileTranslationInteractor() {
        if (loggedInView == null) {
            throw new IllegalStateException("LoggedInView is not initialized");
        }

        FileTranslationService fileTranslationService = new FileTranslationService();
        FileTranslationPresenter fileTranslationPresenter = new FileTranslationPresenter(loggedInView);

        return new FileTranslationInteractor(fileTranslationService, fileTranslationPresenter);
    }

    /**
     * Adds the Text Translation Use Case to the application.
     * @return this builder
     */
    public AppBuilder addTextTranslationUseCase() {
        TextTranslationInteractor textTranslationInteractor = createTextTranslationInteractor();
        TextTranslationController textTranslationController =
                new TextTranslationController(textTranslationInteractor);

        loggedInView.setTextTranslationController(textTranslationController);

        return this;
    }

    private TextTranslationInteractor createTextTranslationInteractor() {
        if (loggedInView == null) {
            throw new IllegalStateException("LoggedInView is not initialized");
        }

        TextTranslationDataAccessInterface translationService = new TextTranslationService();

        TextTranslationOutputBoundary textTranslationPresenter =
                new TextTranslationPresenter(loggedInView);

        return new TextTranslationInteractor(
                translationService,
                textTranslationPresenter
        );
    }

    public AppBuilder addVoiceTranslationUseCase() throws IOException {
        VoiceTranslationInteractor voiceTranslationInteractor = createVoiceTranslationInteractor();
        VoiceTranslationController voiceTranslationController = new VoiceTranslationController(voiceTranslationInteractor);
        // Inject the controller into the LoggedInView
        loggedInView.setVoiceTranslationController(voiceTranslationController);
        return this;
    }

    private VoiceTranslationInteractor createVoiceTranslationInteractor() throws IOException {
        // Ensure dependencies are correctly initialized
        if (loggedInView == null) {
            throw new IllegalStateException("LoggedInView is not initialized");
        }

        SpeechToTextService speechToTextService = new SpeechToTextService();
        // Initialize the presenter for the FileTranslationInteractor
        VoiceTranslationPresenter voiceTranslationPresenter = new VoiceTranslationPresenter(loggedInView);

        // Create and return the FileTranslationInteractor with required dependencies
        return new VoiceTranslationInteractor(speechToTextService, voiceTranslationPresenter);
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}

