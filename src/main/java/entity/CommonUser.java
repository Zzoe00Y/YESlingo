package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private String password;
    private String inputLan = "ENGLISH";
    private String outputLan = "ENGLISH";
    private ArrayList<ChatMessage> chatHistoryMessagesDisplay = new ArrayList<>();
    private ArrayList<ChatMessage> chatHistoryMessagesEng = new ArrayList<>();
    private ArrayList<String> history = new ArrayList<>();

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

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getOutputLan() {
        return outputLan;
    }

    @Override
    public void setOutputLan(String outputLan) {
        this.outputLan = outputLan;
    }

    @Override
    public String getInputLan() {
        return inputLan;
    }

    @Override
    public void setInputLan(String inputLan) {
        this.inputLan = inputLan;
    }

    @Override
    public ArrayList<ChatMessage> getChatHistoryMessagesDisplay() {
        return chatHistoryMessagesDisplay;
    }

    @Override
    public void addChatHistoryMessagesDisplay(ChatMessage newMessagesDisplay) {
        this.chatHistoryMessagesDisplay.add(newMessagesDisplay);
    }

    @Override
    public ArrayList<ChatMessage> getChatHistoryMessagesEng() {
        return chatHistoryMessagesEng;
    }

    @Override
    public void addChatHistoryMessagesEng(ChatMessage newMessagesEng) {
        this.chatHistoryMessagesEng.add(newMessagesEng);
    }

    @Override
    public ArrayList<String> getHistory() {
        return history;
    }

    @Override
    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }
}
