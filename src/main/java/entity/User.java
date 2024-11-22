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

    String getOutputLan();

    void setOutputLan(String outputLan);

    String getInputLan();

    void setInputLan(String inputLan);

    ArrayList<ChatMessage> getChatHistoryMessagesDisplay();

    void setChatHistoryMessagesDisplay(ArrayList<ChatMessage> chatHistoryMessagesDisplay);

    ArrayList<ChatMessage> getChatHistoryMessagesEng();

    void setChatHistoryMessagesEng(ArrayList<ChatMessage> chatHistoryMessagesEng);
}
