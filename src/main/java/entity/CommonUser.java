package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final String inputLan = "English";
    private final String outputLan = "English";
    private final ArrayList<ChatMessage> chatHistoryMessagesDisplay = new ArrayList<>();
    private final ArrayList<ChatMessage> chatHistoryMessagesEng = new ArrayList<>();

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public ArrayList<ChatMessage> getChatHistoryMessagesDisplay() {
        return chatHistoryMessagesDisplay;
    }

    public ArrayList<ChatMessage> getChatHistoryMessagesEng() {
        return chatHistoryMessagesEng;
    }

    public String getOutputLan() {
        return outputLan;
    }

    public String getInputLan() {
        return inputLan;
    }
}
