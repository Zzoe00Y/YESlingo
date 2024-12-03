package use_case.history;

import java.util.List;

/**
 * Output data class for the History use case.
 * Contains the user's translation history data.
 */
public class HistoryOutputData {
    private final String username;
    private final List<String> historyMessages;

    /**
     * Creates a new HistoryOutputData instance.
     *
     * @param username the username of the user, must not be null
     * @param historyMessages list of translation history messages, must not be null
     */
    public HistoryOutputData(String username, List<String> historyMessages) {
        this.username = username;
        this.historyMessages = historyMessages;
    }

    /**
     * Gets the username associated with this history.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the list of translation history messages.
     *
     * @return list of history messages
     */
    public List<String> getHistoryMessages() {
        return historyMessages;
    }
}
