package use_case.chatbot;

/**
 * The Input Data for the ChatBot Use Case.
 */
public class ChatBotInputData {

    private final String message;
    private final String inputLan;
    private final String outputLan;
    private final String username;
    private final String channelID;

    public ChatBotInputData(String message1, String inputLan1, String outputLan1, String channelID, String username) {
        this.username = username;
        this.message = message1;
        this.inputLan = inputLan1;
        this.outputLan = outputLan1;
        this.channelID = channelID;
    }


    public String getMessage() {
        return message;
    }

    public String getInputLan() {
        return inputLan;
    }

    public String getOutputLan() {
        return outputLan;
    }

    public String getUsername() {
        return username;
    }

    public String getChannelID() {
        return channelID;
    }
}
