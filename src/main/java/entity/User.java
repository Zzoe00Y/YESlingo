package entity;

import java.util.ArrayList;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    void setPassword(String password);

    /**
     * Returns the output language of the user.
     * @return the output language of the user.
     */
    String getOutputLan();

    void setOutputLan(String outputLan);

    String getInputLan();

    void setInputLan(String inputLan);

    ArrayList<ChatMessage> getChatHistoryMessagesDisplay();

    void addChatHistoryMessagesDisplay(ChatMessage newMessagesDisplay);

    ArrayList<ChatMessage> getChatHistoryMessagesEng();

    void addChatHistoryMessagesEng(ChatMessage newMessagesEng);

    ArrayList<String> getHistory();

    void setHistory(ArrayList<String> history);
}
