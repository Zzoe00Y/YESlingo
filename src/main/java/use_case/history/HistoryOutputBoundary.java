package use_case.history;

import interface_adapter.chatbot.ChatBotState;
import interface_adapter.history.HistoryState;

public interface HistoryOutputBoundary {

    /**
     * Switches to the LoggedIn View.
     */
    void switchToLoggedInView();

    void pullUser(HistoryState newState);

    void clearAll(HistoryState newState);
}
