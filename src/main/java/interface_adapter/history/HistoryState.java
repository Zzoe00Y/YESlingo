package interface_adapter.history;

import java.util.ArrayList;

public class HistoryState {
    private String username = "";
    private ArrayList<String> historyMessages = new ArrayList<>();

    public HistoryState (){
    }

    public HistoryState (String username, ArrayList<String> historyMessages) {
        this.username = username;
        this.historyMessages = historyMessages;
        }

    public ArrayList<String> getHistoryMessages() {
        return historyMessages;
    }

    public void setHistoryMessages(ArrayList<String> historyMessages) {
        this.historyMessages = historyMessages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
