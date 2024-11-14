package view;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.image_translation.ImageTranslationController;
import interface_adapter.translation.TranslationViewInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * The View for when the user is logged into the program, displaying translation options and results.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener, TranslationViewInterface {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private BufferedImage selectedImage;
    private ImageTranslationController imageTranslationController;


    private final JLabel usernameLabel;


    private final JButton imageUploadButton;
    private final JComboBox<String> inputLanguageComboBox;
    private final JComboBox<String> outputLanguageComboBox;
    private final JTextArea textArea;
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

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        inputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});
        outputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});

        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languagePanel.add(new JLabel("Input Language:"));
        languagePanel.add(inputLanguageComboBox);
        languagePanel.add(new JLabel("Output Language:"));
        languagePanel.add(outputLanguageComboBox);

        mainContentPanel.add(languagePanel);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textArea = new JTextArea(3, 20);
        textPanel.add(new JLabel("Text:"));
        textPanel.add(new JScrollPane(textArea));

        mainContentPanel.add(textPanel);

        JPanel translatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        translateButton = new JButton("Translate");
        translatePanel.add(translateButton);

        mainContentPanel.add(translatePanel);

        JPanel inputOptionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageUploadButton = new JButton("Upload Image");
        voiceInputButton = new JButton("Input Voice");
        chatBoxButton = new JButton("ChatBox");
        inputOptionsPanel.add(imageUploadButton);
        inputOptionsPanel.add(voiceInputButton);
        inputOptionsPanel.add(chatBoxButton);

        mainContentPanel.add(inputOptionsPanel);

        this.add(mainContentPanel, BorderLayout.CENTER);

        translationLabel = new JLabel("Translation Result:");
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultPanel.add(translationLabel);
        this.add(resultPanel, BorderLayout.SOUTH);

        setupListeners();
    }

    /**
     * Sets the ImageTranslationController to handle image translation.
     * @param imageTranslationController the ImageTranslationController to set
     */
    public void setImageTranslationController(ImageTranslationController imageTranslationController) {
        this.imageTranslationController = imageTranslationController;
    }

    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                selectedImage = ImageIO.read(file);
                JOptionPane.showMessageDialog(this, "Image loaded successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to load image.");
                ex.printStackTrace();
            }
        }
    }

    public BufferedImage getSelectedImage() {
        return selectedImage;
    }

    public String getSelectedTargetLanguage() {
        return (String) outputLanguageComboBox.getSelectedItem();
    }

    private void setupListeners() {
        translateButton.addActionListener(e -> {
            System.out.println("Translate button clicked");
        });

        imageUploadButton.addActionListener(e -> {
            selectImage(); // Load the selected image
            BufferedImage image = getSelectedImage();
            String targetLanguage = getSelectedTargetLanguage();
            if (image != null && targetLanguage != null && imageTranslationController != null) {
                imageTranslationController.execute(image, targetLanguage);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an image and a target language.");
            }
        });

        voiceInputButton.addActionListener(e -> {
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
        translationLabel.setText("Translation: " + translatedText);
    }

    @Override
    public void displayError(String error) {
        translationLabel.setText("Error: " + error);
    }

    public String getViewName() {
        return viewName;
    }
}