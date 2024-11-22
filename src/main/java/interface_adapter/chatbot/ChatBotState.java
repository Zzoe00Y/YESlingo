package interface_adapter.chatbot;

import java.util.ArrayList;
import entity.ChatMessage;

/**
 * The state for the ChatBot View Model.
 */
public class ChatBotState {
    private String username = "";
    private String userInput = "";
    private String inputLan = "English";
    private String outputLan = "English";
    private String currentChatID = "";
    private ArrayList<String> chatHistoryIds;
    private ArrayList<ChatMessage> chatHistoryMessages = new ArrayList<>();
    private ChatMessage newResponse;

    public ChatBotState() {
        chatHistoryMessages.add(new ChatMessage("SYSTEM", "Select an existing Chat Channel, or create a new Chat"));
    }

    public ChatBotState(String username, String inputLan, String outputLan, ArrayList<ChatMessage> chatHistoryMessages) {
        this.username = username;
        this.inputLan = inputLan;
        this.outputLan = outputLan;
        this.chatHistoryMessages = chatHistoryMessages;
    }

    public ArrayList<ChatMessage> getChatHistoryMessages() {
        return chatHistoryMessages;
    }

    public void setChatHistoryMessages(ArrayList<ChatMessage> chatHistoryMessages) {
        this.chatHistoryMessages = chatHistoryMessages;
    }

    public void addChatHistoryMessage(ChatMessage message) {
        this.chatHistoryMessages.add(message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getChatHistoryIds() {
        return chatHistoryIds;
    }

    public void setChatHistoryIds(ArrayList<String> chatHistoryIds) {
        this.chatHistoryIds = chatHistoryIds;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getInputLan() {
        return inputLan;
    }

    public void setInputLan(String inputLan) {
        this.inputLan = inputLan;
    }

    public String getOutputLan() {
        return outputLan;
    }

    public void setOutputLan(String outputLan) {
        this.outputLan = outputLan;
    }

    public String getCurrentChatID() {
        return currentChatID;
    }

    public void setCurrentChatID(String currentChatID) {
        this.currentChatID = currentChatID;
    }

    public ChatMessage getNewResponse() {
        return newResponse;
    }

    public void setNewResponse(ChatMessage newResponse) {
        this.newResponse = newResponse;
    }
}
