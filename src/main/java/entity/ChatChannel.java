package entity;

import java.util.ArrayList;

/**
 * The representation of a chat channel in our program.
 */
public interface ChatChannel {
    /**
     * Returns the input language of the channel.
     * @return the input language of the channel.
     */
    String getInputLang();

    /**
     * Returns the output language of the channel.
     * @return the output language of the channel.
     */
    String getOutPutLang();

    /**
     * Returns the chat history in this channel.
     * @return the chat history in this channel.
     */
    ArrayList<String> getChatHistory();

    /**
     * Returns the channel ID in this channel.
     * @return the channel ID in this channel.
     */
    String getChannelID();
}
