package view;

import interface_adapter.file_translation.FileTranslationController;
import interface_adapter.loggedin_homepage.LoggedInState;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import interface_adapter.loggedin_homepage.LoggedInController;
import interface_adapter.file_translation.FileTranslationController;
import interface_adapter.text_translation.TextTranslationController;
import interface_adapter.translation.TranslationViewInterface;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class LoggedInView extends JPanel implements PropertyChangeListener, TranslationViewInterface {
    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private LoggedInController loggedInController;
    private FileTranslationController fileTranslationController;
    private TextTranslationController textTranslationController;

    private final JLabel usernameLabel;
    private final JComboBox<LanguageItem> inputLanguageComboBox;
    private final JComboBox<LanguageItem> outputLanguageComboBox;
    private final JTextArea textArea;
    private final JButton fileUploadButton;
    private final JButton voiceInputButton;
    private final JButton translateButton;
    private final JLabel translationLabel;
    private final JButton profileButton;
    private final JButton historyButton;
    private final JButton chatBoxButton;

    // Inner class to hold language information
    private static class LanguageItem {
        final String displayName;
        final String code;

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
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameLabel = new JLabel("Hi, ");
        welcomePanel.add(usernameLabel);

        JPanel topButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profileButton = new JButton("Profile");
        historyButton = new JButton("History");
        topButtonsPanel.add(profileButton);
        topButtonsPanel.add(historyButton);

        topPanel.add(welcomePanel, BorderLayout.WEST);
        topPanel.add(topButtonsPanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        // Language Selection Panel with complete list of supported languages
        String[][] languages = {
                {"English", "en"},
                {"Spanish", "es"},
                {"French", "fr"},
                {"German", "de"},
                {"Italian", "it"},
                {"Portuguese", "pt"},
                {"Chinese", "zh-CN"},
                {"Japanese", "ja"},
                {"Korean", "ko"},
                {"Russian", "ru"},
                {"Arabic", "ar"},
                {"Dutch", "nl"},
                {"Greek", "el"},
                {"Hebrew", "he"},
                {"Hindi", "hi"},
                {"Polish", "pl"},
                {"Turkish", "tr"},
                {"Vietnamese", "vi"}
        };

        DefaultComboBoxModel<LanguageItem> inputModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<LanguageItem> outputModel = new DefaultComboBoxModel<>();

        for (String[] lang : languages) {
            LanguageItem item = new LanguageItem(lang[0], lang[1]);
            inputModel.addElement(item);
            outputModel.addElement(item);
        }

        inputLanguageComboBox = new JComboBox<>(inputModel);
        outputLanguageComboBox = new JComboBox<>(outputModel);

        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languagePanel.add(new JLabel("Input Language:"));
        languagePanel.add(inputLanguageComboBox);
        languagePanel.add(new JLabel("Output Language:"));
        languagePanel.add(outputLanguageComboBox);

        mainContentPanel.add(languagePanel);

        // Text Input Panel
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textArea = new JTextArea(5, 30); // Increased size for better usability
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textPanel.add(new JLabel("Text:"));
        textPanel.add(scrollPane);

        mainContentPanel.add(textPanel);

        // Translate Button Panel
        JPanel translatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        translateButton = new JButton("Translate");
        translatePanel.add(translateButton);

        mainContentPanel.add(translatePanel);

        // Input Options Panel
        JPanel inputOptionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fileUploadButton = new JButton("Upload File");
        voiceInputButton = new JButton("Input Voice");
        chatBoxButton = new JButton("ChatBox");
        inputOptionsPanel.add(fileUploadButton);
        inputOptionsPanel.add(voiceInputButton);
        inputOptionsPanel.add(chatBoxButton);

        mainContentPanel.add(inputOptionsPanel);

        this.add(mainContentPanel, BorderLayout.CENTER);

        // Translation Result Panel
        translationLabel = new JLabel("Translation Result:");
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultPanel.add(translationLabel, BorderLayout.CENTER);
        this.add(resultPanel, BorderLayout.SOUTH);

        setupListeners();
    }

    public void setFileTranslationController(FileTranslationController controller) {
        this.fileTranslationController = controller;
    }

    public void setLoggedInController(LoggedInController controller) {
        this.loggedInController = controller;
    }

    private void setupListeners() {
        translateButton.addActionListener(e -> {
            System.out.println("Translate button clicked");
            String textToTranslate = textArea.getText().trim();
            if (!textToTranslate.isEmpty() && textTranslationController != null) {
                LanguageItem sourceItem = (LanguageItem) inputLanguageComboBox.getSelectedItem();
                LanguageItem targetItem = (LanguageItem) outputLanguageComboBox.getSelectedItem();

                if (sourceItem == null || targetItem == null) {
                    JOptionPane.showMessageDialog(this, "Please select both source and target languages");
                    return;
                }

                String sourceLang = sourceItem.code;
                String targetLang = targetItem.code;

                // Don't translate if source and target languages are the same
                if (sourceLang.equals(targetLang)) {
                    JOptionPane.showMessageDialog(this,
                            "Source and target languages must be different");
                    return;
                }

                System.out.println("Translating: " + textToTranslate);
                System.out.println("From: " + sourceLang + " To: " + targetLang);

                textTranslationController.translate(textToTranslate, sourceLang, targetLang);
            } else {
                String error = textTranslationController == null ?
                        "Translation controller not initialized" :
                        "Please enter text to translate and ensure all languages are selected.";
                JOptionPane.showMessageDialog(this, error);
            }
        });

        fileUploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Text Files", "txt"));

            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    LanguageItem sourceItem = (LanguageItem) inputLanguageComboBox.getSelectedItem();
                    LanguageItem targetItem = (LanguageItem) outputLanguageComboBox.getSelectedItem();

                    if (sourceItem == null || targetItem == null) {
                        JOptionPane.showMessageDialog(this, "Please select both source and target languages.");
                        return;
                    }

                    String sourceLang = sourceItem.code;
                    String targetLang = targetItem.code;

                    fileTranslationController.translateFile(file.getAbsolutePath(), sourceLang, targetLang);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error during file translation: " + ex.getMessage());
                }
            }
        });


    }

    public void setTextTranslationController(TextTranslationController controller) {
        this.textTranslationController = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property change event: " + evt.getPropertyName());
        if ("state".equals(evt.getPropertyName())) {
            LoggedInState state = (LoggedInState) evt.getNewValue();
            usernameLabel.setText("Hi, " + state.getUsername() + "!");
        }
        if ("translation".equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(() -> {
                String translatedText = (String) evt.getNewValue();
                System.out.println("Received translation: " + translatedText);
                translationLabel.setText("Translation: " + translatedText);
            });
        }
    }

    @Override
    public void displayTranslation(String translatedText) {
        System.out.println("Displaying translation: " + translatedText);
        SwingUtilities.invokeLater(() -> {
            translationLabel.setText("Translation: " + translatedText);
        });
    }

    @Override
    public void displayError(String error) {
        System.out.println("Displaying error: " + error);
        SwingUtilities.invokeLater(() -> {
            translationLabel.setText("Error: " + error);
            JOptionPane.showMessageDialog(this, error);
        });
    }

    public String getViewName() {
        return viewName;
    }
}
