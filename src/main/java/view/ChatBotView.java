package view;

import entity.ChatMessage;
import interface_adapter.chatbot.ChatBotState;
import interface_adapter.chatbot.ChatBotController;
import interface_adapter.chatbot.ChatBotViewModel;
import space.dynomake.libretranslate.Language;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logging into the program.
 */
public class ChatBotView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "chatbot default";
    private final ChatBotViewModel chatbotViewModel;

    private ChatBotController chatbotController;

    private JPanel messagePanel;
    private JScrollPane chatHistoryPanel;

    private String[] inputLanguages;
    private String[] outputLanguages;

    public ChatBotView(ChatBotViewModel chatbotViewModel) {

        this.chatbotViewModel = chatbotViewModel;
        this.chatbotViewModel.addPropertyChangeListener(this);

        final Language[] languages = Language.values();
        final int len = languages.length;
        inputLanguages = new String[len];
        outputLanguages = new String[len];
        for (int i = 0; i < len; i++) {
            inputLanguages[i] = languages[i].toString();
            outputLanguages[i] = languages[i].toString();
        }

        setViewComponents();
    }

    /**
     * Set the components in the view: Remove all existing, then replace with new generated panels.
     */
    private void setViewComponents() {
        this.removeAll();

        final JPanel headerPane = createHeaderPane();

        // Create the ContentPanel that contains chat history and user input panels
        final JPanel contentPane = createContentPane();
        contentPane.setPreferredSize(new Dimension(500, 500));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.black));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(headerPane);
        this.add(contentPane);

        this.revalidate();
        this.repaint();
    }

    /**
     * Return a Header Panel which contains exit button and input/output language selection.
     * in accordance to the currentState
     * @return the Header JPanel
     */
    private JPanel createHeaderPane() {
        final ChatBotState currentState = chatbotViewModel.getState();
        final JLabel titleLabel = new JLabel("CHATBOT");

        // create the input/output language selection panel
        final JPanel languageSelectPanel = new JPanel();
        final JLabel toLabel = new JLabel("to");
        final JComboBox<String> inputLanguageComboBox = new JComboBox<>(inputLanguages);
        final JComboBox<String> outputLanguageComboBox = new JComboBox<>(outputLanguages);
        inputLanguageComboBox.addItemListener(evt -> {
            if (evt.getSource().equals(inputLanguageComboBox)) {
                currentState.setInputLan(inputLanguageComboBox.getSelectedItem().toString());
            }
        });
        outputLanguageComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getSource().equals(outputLanguageComboBox)) {
                    currentState.setOutputLan(outputLanguageComboBox.getSelectedItem().toString());
                }
            }
        });

        // set the current input/output language to the user's preference
        inputLanguageComboBox.setSelectedItem(currentState.getInputLan());
        outputLanguageComboBox.setSelectedItem(currentState.getOutputLan());

        languageSelectPanel.add(inputLanguageComboBox);
        languageSelectPanel.add(toLabel);
        languageSelectPanel.add(outputLanguageComboBox);

        // create the exit button
        final JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(exitButton)) {
                            chatbotController.switchToLoggedInView();
                        }
                    }
                }
        );

        // add the components to the panels
        final JPanel headerPane = new JPanel(new BorderLayout());
        headerPane.add(titleLabel, BorderLayout.WEST);
        headerPane.add(exitButton, BorderLayout.EAST);
        headerPane.add(languageSelectPanel, BorderLayout.CENTER);
        headerPane.setPreferredSize(new Dimension(500, 50));

        return headerPane;
    }

    /**
     * Return a UserInput Panel which contains the input text area and sent button.
     */
    private JPanel createUserInputPanel() {
        // create the user input text field
        JTextArea userInputArea = new JTextArea(3,35);
        userInputArea.setLineWrap(true);
        userInputArea.setWrapStyleWord(true);
        final JScrollPane inputScrollPanel = new JScrollPane(userInputArea);

        userInputArea.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChatBotState currentState = chatbotViewModel.getState();
                currentState.setUserInput(userInputArea.getText());
                chatbotViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        // create the sent button
        final JButton userInputSentButton = new JButton("sent");
        userInputSentButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(userInputSentButton)) {
                            final ChatBotState currentState = chatbotViewModel.getState();
                            final String message = currentState.getUserInput();
                            // if user input is not blank, initiate sentChat usecase
                            if (!message.isBlank()) {
                                // clear inputArea, display message in chatHistoryPanel
                                userInputArea.setText("");
                                currentState.setUserInput("");
                                addNewMessage(new ChatMessage("USER", message));

                                chatbotController.sendChat(message,
                                        currentState.getInputLan(),
                                        currentState.getOutputLan(),
                                        currentState.getUsername());
                            }
                            // else do nothing
                        }
                    }
                }
        );

        // add components to the userInputPanel
        final JPanel userInputPanel = new JPanel();
        userInputPanel.add(inputScrollPanel);
        userInputPanel.add(userInputSentButton);

        return userInputPanel;
    }

    /**
     * Return a chat history panel that displays existing messages (if any).
     * @return the JScrollPane that displays previous chat history
     */
    private JScrollPane createChatHistoryPanel() {

        final ChatBotState currentState = chatbotViewModel.getState();

        // if there's no previous messages in the current state
        if (currentState.getChatHistoryMessages().isEmpty()) {
            final String defaultMessage = "Select an existing Chat Channel, or create a new Chat";
            final JLabel noContentLabel = new JLabel(defaultMessage);
            messagePanel.add(noContentLabel, BorderLayout.CENTER);
        }
        // else, add the messages in the panel
        else {
            for (ChatMessage message: currentState.getChatHistoryMessages()) {
                addNewMessage(message);
            }
        }
        return new JScrollPane(messagePanel);
    }

    /**
     * Return a contentPane which contains the chat history and user input field.
     */
    private JPanel createContentPane() {
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        chatHistoryPanel = new JScrollPane(messagePanel);
        chatHistoryPanel = createChatHistoryPanel();
        chatHistoryPanel.setPreferredSize(new Dimension(500, 425));
        final JPanel userInputPanel = createUserInputPanel();

        contentPane.add(chatHistoryPanel, BorderLayout.PAGE_START);
        contentPane.add(userInputPanel, BorderLayout.PAGE_END);

        return contentPane;
    }

    /**
     * Add new message in chat history panel.
     */
    private void addNewMessage(ChatMessage message) {
        final String text = message.getMessage();
        final JTextArea textArea = new JTextArea(text);
        textArea.setColumns(35);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        final JPanel textPanel = new JPanel(new BorderLayout());

        textPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (message.getRole().equals("USER")) {
            textPanel.add(textArea, BorderLayout.WEST);
        }
        else if (message.getRole().equals("CHATBOT")) {
            textPanel.add(textArea, BorderLayout.EAST);
        }
        else {
            textPanel.add(textArea, BorderLayout.CENTER);
        }

        messagePanel.add(textPanel);

        messagePanel.revalidate();
        messagePanel.repaint();
        chatHistoryPanel.revalidate();
        chatHistoryPanel.repaint();
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChatBotState state = (ChatBotState) evt.getNewValue();

        if ("state".equals(evt.getPropertyName())) {
        }

        // if get called by the sentChat use case
        if ("sentChat".equals(evt.getPropertyName())) {
            if(state.getNewResponse() != null){
                addNewMessage(state.getNewResponse());
                state.setNewResponse(null);
            }
        }

        // If the view needs to get updated info from user according to the current state's username
        if ("reset".equals(evt.getPropertyName())) {
            chatbotController.pullUser(state.getUsername());
            setViewComponents();
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChatBotController(ChatBotController controller) {
        this.chatbotController = controller;
    }
}
