package use_case.history;

/**
 * Input data for the history use case.
 */
public class HistoryInputData {
    private final String username;

    public HistoryInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
