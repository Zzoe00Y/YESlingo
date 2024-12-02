package use_case.history;

import java.util.List;

public class HistoryOutputData {
    private final String username;
    private final List<String> historyMessages;

    public HistoryOutputData(String username, List<String> historyMessages) {
        this.username = username;
        this.historyMessages = historyMessages;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getHistoryMessages() {
        return historyMessages;
    }
}
