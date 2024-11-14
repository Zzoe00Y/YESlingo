package view;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.translation.TranslationViewInterface;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program, displaying translation options and results.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener, TranslationViewInterface {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final JLabel usernameLabel;

    private final JComboBox<String> inputLanguageComboBox;
    private final JComboBox<String> outputLanguageComboBox;
    private final JTextArea textArea;
    private final JButton imageUploadButton;
    private final JButton voiceInputButton;
    private final JButton translateButton;
    private final JLabel translationLabel;
    private final JButton profileButton;
    private final JButton historyButton;
    private final JButton chatBoxButton;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Top Panel: Welcome and Navigation
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameLabel = new JLabel("Hi, ");  // Initial text; username will be set via propertyChange
        welcomePanel.add(usernameLabel);

        JPanel topButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButton = new JButton("Profile");
        historyButton = new JButton("History");
        topButtonsPanel.add(profileButton);
        topButtonsPanel.add(historyButton);

        topPanel.add(welcomePanel, BorderLayout.WEST);
        topPanel.add(topButtonsPanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        // Main Content Panel: Language selection, Text Entry, and Translate Button
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        // Language Selection Panel
        inputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});
        outputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});

        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languagePanel.add(new JLabel("Input Language:"));
        languagePanel.add(inputLanguageComboBox);
        languagePanel.add(new JLabel("Output Language:"));
        languagePanel.add(outputLanguageComboBox);

        mainContentPanel.add(languagePanel);

        // Text Entry Panel
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textArea = new JTextArea(3, 20);
        textPanel.add(new JLabel("Text:"));
        textPanel.add(new JScrollPane(textArea));

        mainContentPanel.add(textPanel);

        // Translate Button Panel
        JPanel translatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        translateButton = new JButton("Translate");
        translatePanel.add(translateButton);

        mainContentPanel.add(translatePanel);

        // Input Options Panel: Image and Voice Upload, ChatBox
        JPanel inputOptionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageUploadButton = new JButton("Upload Image");
        voiceInputButton = new JButton("Input Voice");
        chatBoxButton = new JButton("ChatBox");
        inputOptionsPanel.add(imageUploadButton);
        inputOptionsPanel.add(voiceInputButton);
        inputOptionsPanel.add(chatBoxButton);

        mainContentPanel.add(inputOptionsPanel);

        this.add(mainContentPanel, BorderLayout.CENTER);

        // Result Panel for Translation Output
        translationLabel = new JLabel("Translation Result:");
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultPanel.add(translationLabel);
        this.add(resultPanel, BorderLayout.SOUTH);

        // Set up listeners for buttons
        setupListeners();
    }

    private void setupListeners() {
        translateButton.addActionListener(e -> {
            // Placeholder for translation action
            // This will interact with the TextTranslationController
            System.out.println("Translate button clicked");
        });

        imageUploadButton.addActionListener(e -> {
            // Placeholder for image translation action
            // This will interact with the ImageTranslationController
            System.out.println("Image Upload button clicked");
        });

        voiceInputButton.addActionListener(e -> {
            // Placeholder for voice translation action
            // This will interact with the VoiceTranslationController
            System.out.println("Voice Input button clicked");
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            usernameLabel.setText("Hi, " + state.getUsername() + "!");
        }
        if ("translation".equals(evt.getPropertyName())) {
            translationLabel.setText((String) evt.getNewValue());
        }
    }

    @Override
    public void displayTranslation(String translatedText) {
        // Sets the translated text on the translation label
        translationLabel.setText("Translation: " + translatedText);
    }

    @Override
    public void displayError(String error) {
        // Sets an error message on the translation label
        translationLabel.setText("Error: " + error);
    }

    public String getViewName() {
        return viewName;
    }
}
