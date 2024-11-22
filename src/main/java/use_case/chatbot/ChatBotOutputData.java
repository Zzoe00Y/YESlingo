package use_case.chatbot;

import entity.ChatMessage;

/**
 * Output Data for the ChatBot Use Case.
 */
public class ChatBotOutputData {

    private final ChatMessage response;

    public ChatBotOutputData(ChatMessage response) {
        this.response = response;
    }

    public ChatMessage getResponse() {
        return response;
    }
}
