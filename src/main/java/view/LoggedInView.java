package view;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.translation.TextTranslationController;
import interface_adapter.translation.ImageTranslationController;
import interface_adapter.translation.VoiceTranslationController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final JLabel username;

    private final JTextField textInputField = new JTextField(15);

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

        final JLabel title = new JLabel("Hi, ");
        username = new JLabel();
        this.add(title);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel topButtons = new JPanel();
        profileButton = new JButton("Profile");
        topButtons.add(profileButton);
        historyButton = new JButton("History");
        topButtons.add(historyButton);
        this.add(topButtons);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        inputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});
        outputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});

        JPanel languagePanel = new JPanel(new FlowLayout());
        languagePanel.add(new JLabel("Input Language:"));
        languagePanel.add(inputLanguageComboBox);
        languagePanel.add(new JLabel("Output Language:"));
        languagePanel.add(outputLanguageComboBox);
        this.add(languagePanel);

        textArea = new JTextArea(3, 20);
        add(new JLabel("Text:"));
        add(new JScrollPane(textArea));

        JPanel inputOptionsPanel = new JPanel();
        imageUploadButton = new JButton("Image Upload");
        voiceInputButton = new JButton("Voice Input");
        translateButton = new JButton("Translate");
        inputOptionsPanel.add(imageUploadButton);
        inputOptionsPanel.add(voiceInputButton);
        inputOptionsPanel.add(translateButton);

        translationLabel = new JLabel("Translation will appear here:");
        JPanel resultPanel = new JPanel();
        resultPanel.add(translationLabel);
        this.add(resultPanel);

        chatBoxButton = new JButton("ChatBox");
        inputOptionsPanel.add(chatBoxButton);
        this.add(chatBoxButton);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        if ("state".equals(evt.getPropertyName())) {
            LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText("Hi, " + state.getUsername());
        }
        if ("translation".equals(evt.getPropertyName())) {
            translationLabel.setText((String) evt.getNewValue());
        }}

    public String getViewName(){
        return viewName;
    }

//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        this.add(usernameLabel);
//        this.add(topButtonPanel);
//        this.add(languagePanel);
//        this.add(textPanel);
//        this.add(inputOptionsPanel);
//        this.add(resultPanel);
//        this.add(chatBoxButton);
//        this.add(logOutButton);


    }