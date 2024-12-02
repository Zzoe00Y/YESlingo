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

    /**
     * Sets the password of the user.
     * @param password the password
     */
    void setPassword(String password);

    /**
     * Returns the output language of the user.
     * @return the output language of the user.
     */
    String getOutputLan();

    /**
     * Sets the output language of the user.
     * @param outputLan the output language
     */
    void setOutputLan(String outputLan);

    /**
     * Returns the input language of the user.
     * @return the input language of the user.
     */
    String getInputLan();

    /**
     * Sets the input language of the user.
     * @param inputLan the password
     */
    void setInputLan(String inputLan);

    /**
     * Returns the display chat history of the user.
     * @return the display chat history of the user.
     */
    ArrayList<ChatMessage> getChatHistoryMessagesDisplay();

    /**
     * Sets the display chat history of the user.
     * @param newMessagesDisplay the display chat history
     */
    void addChatHistoryMessagesDisplay(ChatMessage newMessagesDisplay);

    /**
     * Returns the chat history of the user.
     * @return the chat history of the user.
     */
    ArrayList<ChatMessage> getChatHistoryMessagesEng();

    /**
     * Sets the chat history of the user.
     * @param newMessagesEng the chat history
     */
    void addChatHistoryMessagesEng(ChatMessage newMessagesEng);

    /**
     * Returns the history of the user.
     * @return the history of the user.
     */
    ArrayList<String> getHistory();

    /**
     * Sets the history of the user.
     * @param history the history
     */
    void setHistory(ArrayList<String> history);
}
