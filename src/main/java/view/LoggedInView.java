package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import interface_adapter.file_translation.FileTranslationController;
import interface_adapter.loggedin_homepage.LoggedInState;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.loggedin_homepage.LoggedInController;
import interface_adapter.text_translation.TextTranslationController;
import interface_adapter.translation.TranslationViewInterface;
import interface_adapter.voice_translation.VoiceTranslationController;

public class LoggedInView extends JPanel implements PropertyChangeListener, TranslationViewInterface {
    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private LoggedInController loggedInController;
    private FileTranslationController fileTranslationController;
    private TextTranslationController textTranslationController;
    private VoiceTranslationController voiceTranslationController;

    private final JLabel usernameLabel;
    private JComboBox<LanguageItem> inputLanguageComboBox;
    private JComboBox<LanguageItem> outputLanguageComboBox;
    private final JTextArea textArea;
    private final JTextArea translationTextArea;
    private final JButton fileUploadButton;
    private final JButton voiceInputButton;
    private final JButton translateButton;
    private final JButton profileButton;
    private final JButton historyButton;
    private final JButton chatBotButton;
    private JPanel languagePanel;

    // Inner class to hold language information
    private static class LanguageItem {
        private final String displayName;
        private final String code;

        LanguageItem(String displayName, String code) {
            this.displayName = displayName;
            this.code = code;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        // Top Panel with Welcome and Buttons
        final JPanel topPanel = new JPanel(new BorderLayout());
        final JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameLabel = new JLabel("Hi, ");
        welcomePanel.add(usernameLabel);

        final JPanel topButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButton = new JButton("Profile");
        historyButton = new JButton("History");
        topButtonsPanel.add(profileButton);
        topButtonsPanel.add(historyButton);

        topPanel.add(welcomePanel, BorderLayout.WEST);
        topPanel.add(topButtonsPanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        // Main Content Panel
        final JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        // Language Selection Panel
        languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputLanguageComboBox = createInputLanguageComboBox();
        outputLanguageComboBox = createOutputLanguageComboBox();
        languagePanel.add(new JLabel("Input Language:"));
        languagePanel.add(inputLanguageComboBox);
        languagePanel.add(new JLabel("Output Language:"));
        languagePanel.add(outputLanguageComboBox);

        mainContentPanel.add(languagePanel);

        // Text Input Panel
        final JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textArea = new JTextArea(5, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        final JScrollPane textScrollPane = new JScrollPane(textArea);
        textPanel.add(new JLabel("Text:"));
        textPanel.add(textScrollPane);

        mainContentPanel.add(textPanel);

        // Translate Button Panel
        final JPanel translatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        translateButton = new JButton("Translate");
        translatePanel.add(translateButton);

        mainContentPanel.add(translatePanel);

        // Input Options Panel
        final JPanel inputOptionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fileUploadButton = new JButton("Upload File");
        voiceInputButton = new JButton("Input Voice");
        chatBotButton = new JButton("ChatBot");
        inputOptionsPanel.add(fileUploadButton);
        inputOptionsPanel.add(voiceInputButton);
        inputOptionsPanel.add(chatBotButton);

        mainContentPanel.add(inputOptionsPanel);

        // Translation Result Panel
        final JPanel translationPanel = new JPanel(new BorderLayout());
        translationPanel.setBorder(BorderFactory.createTitledBorder("Translation Result"));
        translationTextArea = new JTextArea(5, 30);
        translationTextArea.setEditable(false);
        translationTextArea.setLineWrap(true);
        translationTextArea.setWrapStyleWord(true);
        final JScrollPane translationScrollPane = new JScrollPane(translationTextArea);
        translationPanel.add(translationScrollPane, BorderLayout.CENTER);

        mainContentPanel.add(translationPanel);

        this.add(mainContentPanel, BorderLayout.CENTER);

        setupListeners();
    }

    private JComboBox<LanguageItem> createInputLanguageComboBox() {
        final DefaultComboBoxModel<LanguageItem> model = new DefaultComboBoxModel<>();
        final String[][] languages = {
                {"English", "en"}, {"Spanish", "es"}, {"French", "fr"},
                {"German", "de"}, {"Italian", "it"}, {"Portuguese", "pt"},
                {"Chinese", "zh-CN"}, {"Japanese", "ja"}, {"Korean", "ko"},
                {"Russian", "ru"}, {"Arabic", "ar"}, {"Dutch", "nl"},
                {"Greek", "el"}, {"Hebrew", "he"}, {"Hindi", "hi"},
                {"Polish", "pl"}, {"Turkish", "tr"}, {"Vietnamese", "vi"},
        };

        for (String[] lang : languages) {
            model.addElement(new LanguageItem(lang[0], lang[1]));
        }
        return new JComboBox<>(model);
    }

    private JComboBox<LanguageItem> createOutputLanguageComboBox() {
        final DefaultComboBoxModel<LanguageItem> model = new DefaultComboBoxModel<>();
        final String[][] languages = {
                {"English", "en"}, {"Spanish", "es"}, {"French", "fr"},
                {"German", "de"}, {"Italian", "it"}, {"Portuguese", "pt"},
                {"Chinese", "zh-CN"}, {"Japanese", "ja"}, {"Korean", "ko"},
                {"Russian", "ru"}, {"Arabic", "ar"}, {"Dutch", "nl"},
                {"Greek", "el"}, {"Hebrew", "he"}, {"Hindi", "hi"},
                {"Polish", "pl"}, {"Turkish", "tr"}, {"Vietnamese", "vi"},
        };

        final String defaultLanguage = loggedInViewModel.getState().getLanguage();
        LanguageItem defaultLanguageItem;
        for (String[] lang : languages) {
            if (lang[0].equals(defaultLanguage)) {
                defaultLanguageItem = new LanguageItem(lang[0], lang[1]);
                model.addElement(defaultLanguageItem);
            }
        }
        for (String[] lang : languages) {
            if (!lang[0].equals(defaultLanguage)) {
                model.addElement(new LanguageItem(lang[0], lang[1]));
            }
        }
        return new JComboBox<>(model);
    }

    /**
     * Set default language.
     * @param defaultLanguage the language
     */
    public void setDefaultLanguage(String defaultLanguage) {
        languagePanel.removeAll();

        inputLanguageComboBox = createInputLanguageComboBox();
        outputLanguageComboBox = createOutputLanguageComboBox();
        languagePanel.add(new JLabel("Input Language:"));
        languagePanel.add(inputLanguageComboBox);
        languagePanel.add(new JLabel("Output Language:"));
        languagePanel.add(outputLanguageComboBox);

        languagePanel.revalidate();
        languagePanel.repaint();
        this.revalidate();
        this.repaint();
    }

    private void setupListeners() {
        translateButton.addActionListener(e -> {
            final String textToTranslate = textArea.getText().trim();
            if (!textToTranslate.isEmpty() && textTranslationController != null) {
                final LanguageItem sourceItem = (LanguageItem) inputLanguageComboBox.getSelectedItem();
                final LanguageItem targetItem = (LanguageItem) outputLanguageComboBox.getSelectedItem();
                if (sourceItem != null && targetItem != null && !sourceItem.code.equals(targetItem.code)) {
                    textTranslationController.translate(textToTranslate, sourceItem.code, targetItem.code,
                            loggedInViewModel.getState().getUsername());
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            "Select valid and different source/target languages.");
                }
            }
        });

        fileUploadButton.addActionListener(e -> {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                final File file = fileChooser.getSelectedFile();
                try {
                    final LanguageItem sourceItem = (LanguageItem) inputLanguageComboBox.getSelectedItem();
                    final LanguageItem targetItem = (LanguageItem) outputLanguageComboBox.getSelectedItem();
                    if (sourceItem != null && targetItem != null) {
                        fileTranslationController.translateFile(file.getAbsolutePath(),
                                sourceItem.code, targetItem.code);
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Select valid languages.");
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error during file translation: " + ex.getMessage());
                }
            }
        });

        voiceInputButton.addActionListener(e -> {
            if (voiceTranslationController != null) {
                final LanguageItem sourceItem = (LanguageItem) inputLanguageComboBox.getSelectedItem();
                final LanguageItem targetItem = (LanguageItem) outputLanguageComboBox.getSelectedItem();
                if (sourceItem != null && sourceItem.code.equals("en") && targetItem != null
                        &&
                        !sourceItem.code.equals(targetItem.code)) {
                    voiceTranslationController.speechToText();
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            "Select valid and different source/target languages. \n"
                                    + "Voice Input only supports English!");
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Speech to Text is not available.");
            }
        });

        chatBotButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(chatBotButton)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        loggedInController.switchToChatBotView(currentState.getUsername());
                    }
                }
        );

        profileButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(profileButton)) {
                        loggedInController.switchToProfileView();
                    }
                }
        );

        historyButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(historyButton)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        loggedInController.switchToHistoryView(currentState.getUsername());
                    }
                }
        );
    }

    @Override
    public void displayTranslation(String translatedText) {
        SwingUtilities.invokeLater(() -> translationTextArea.setText(translatedText));
    }

    @Override
    public void displayError(String error) {
        SwingUtilities.invokeLater(() -> translationTextArea.setText("Error: " + error));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            usernameLabel.setText("Hi, " + state.getUsername() + "!");

            setDefaultLanguage(state.getLanguage());
        }
    }

    /**
     * Displays the recognized text in the text area.
     * Updates the UI using SwingUtilities.invokeLater to ensure thread safety.
     *
     * @param recognizedText the text to display, must not be null
     */
    public void displayRecognizedText(String recognizedText) {
        SwingUtilities.invokeLater(() -> textArea.setText(recognizedText));
    }

    /**
     * Gets the name of this view.
     *
     * @return the view name
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the logged in controller for this view.
     *
     * @param controller the controller to set, must not be null
     */
    public void setLoggedInController(LoggedInController controller) {
        this.loggedInController = controller;
    }

    /**
     * Sets the text translation controller for this view.
     *
     * @param controller the controller to set, must not be null
     */
    public void setTextTranslationController(TextTranslationController controller) {
        this.textTranslationController = controller;
    }

    /**
     * Sets the file translation controller for this view.
     *
     * @param controller the controller to set, must not be null
     */
    public void setFileTranslationController(FileTranslationController controller) {
        this.fileTranslationController = controller;
    }

    /**
     * Sets the voice translation controller for this view.
     *
     * @param controller the controller to set, must not be null
     */
    public void setVoiceTranslationController(VoiceTranslationController controller) {
        this.voiceTranslationController = controller;
    }
}
