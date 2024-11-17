package view;

import interface_adapter.chatbot.ChatBotDefaultState;
import interface_adapter.chatbot.ChatBotDefaultController;
import interface_adapter.chatbot.ChatBotDefaultState;
import interface_adapter.chatbot.ChatBotDefaultViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logging into the program.
 */
public class ChatBotDefaultView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "chatbot default";
    private final ChatBotDefaultViewModel chatbotViewModel;

    private ChatBotDefaultController chatbotController;

    public ChatBotDefaultView(ChatBotDefaultViewModel chatbotViewModel) {

        this.chatbotViewModel = chatbotViewModel;
        this.chatbotViewModel.addPropertyChangeListener(this);

        // Create the ChannelPanel

//        JButton newChatButton = new JButton("New Chat");
//        newChatButton.setPreferredSize(new Dimension(25,25));
//
//        JPanel channelPanel = new JPanel();
//        channelPanel.setLayout(new BorderLayout());
//        channelPanel.add(newChatButton, BorderLayout.PAGE_START);
//        channelPanel.setPreferredSize(new Dimension(100,500));
//        channelPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        channelPanel.setBorder(BorderFactory.createLineBorder(Color.black));
//
//        newChatButton.addActionListener(
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        JPanel inputPanel = new JPanel();
//                        String s = (String)JOptionPane.showInputDialog(
//                                this,
//                                inputPanel,
//                                "Create a New Chat",
//                                JOptionPane.PLAIN_MESSAGE,
//                                icon,
//                                possibilities,
//                                "ham");

//                        chatbotController.switchToSignupView();
//                    }
//                }
//        );

        // Create the ContentPanel
        final JPanel contentPane = createContentPane(chatbotViewModel);
        contentPane.setPreferredSize(new Dimension(500,500));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.black));


        // Create the HeaderPanel
        final JLabel titleLabel = new JLabel("CHATBOT");

        JPanel languageSelectPanel = new JPanel();
        final JLabel toLabel = new JLabel("to");
        JComboBox<String> inputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});
        JComboBox<String> outputLanguageComboBox = new JComboBox<>(new String[]{"English", "Spanish", "French"});
        languageSelectPanel.add(inputLanguageComboBox);
        languageSelectPanel.add(toLabel);
        languageSelectPanel.add(outputLanguageComboBox);

        JButton exitButton = new JButton("Exit");

        JPanel headerPane = new JPanel(new BorderLayout());
        headerPane.add(titleLabel, BorderLayout.WEST);
        headerPane.add(exitButton, BorderLayout.EAST);
        headerPane.add(languageSelectPanel, BorderLayout.CENTER);
        headerPane.setPreferredSize(new Dimension(500, 50));

        exitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(exitButton)) {
                            final ChatBotDefaultState currentState = chatbotViewModel.getState();

                            chatbotController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.add(headerPane);
        chatPanel.add(contentPane);

//        this.add(channelPanel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(headerPane);
        this.add(chatPanel);
    }

    @NotNull
    private static JPanel createContentPane(ChatBotDefaultViewModel chatbotViewModel) {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        String defaultMessage = "Select an existing Chat Channel, or create a new Chat";
        final JLabel noContentLabel = new JLabel(defaultMessage);
        contentPane.add(noContentLabel, BorderLayout.CENTER);

        JTextArea userInputArea = new JTextArea(3,35);
        System.out.println(userInputArea.getPreferredScrollableViewportSize());
        userInputArea.setLineWrap(true);
        userInputArea.setWrapStyleWord(true);
        JScrollPane inputScrollPanel = new JScrollPane(userInputArea);

        JButton userInputSentButton = new JButton("sent");
        JPanel userInputPanel = new JPanel();
        userInputPanel.add(inputScrollPanel);
        userInputPanel.add(userInputSentButton);

        contentPane.add(userInputPanel, BorderLayout.PAGE_END);

        JPanel chatHistoryPanel = new JPanel();
        JTextArea chatTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        chatTextArea.setEditable(false);

        userInputArea.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChatBotDefaultState currentState = chatbotViewModel.getState();
                currentState.setUsername(userInputArea.getText());
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
        return contentPane;
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
        final ChatBotDefaultState state = (ChatBotDefaultState) evt.getNewValue();
        setFields(state);

        // If new chat channel created
//        if (state.getLoginError() != null) {
//            JOptionPane.showMessageDialog(this, state.getLoginError());
//        }

        if ("state".equals(evt.getPropertyName())) {
        }

        // If selected chat channel: update contentPanel

        // If display default chatbot view
        if ("defaultChatBot".equals(evt.getPropertyName())) {
//            final ChatBotDefaultState state = (ChatBotDefaultState) evt.getNewValue();
//            usernameLabel.setText("Hi, " + state.getUsername() + "!");
        }
    }

    private void updateChannelPane(ChatBotDefaultState state) {
//
    }
    private void setFields(ChatBotDefaultState state) {
//        usernameInputField.setText(state.getUsername());
//        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setChatBotDefaultController(ChatBotDefaultController chatbotController) {
        this.chatbotController = chatbotController;
    }
}
