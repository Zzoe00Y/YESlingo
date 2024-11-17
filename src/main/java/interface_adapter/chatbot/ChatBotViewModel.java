package interface_adapter.chatbot;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class ChatBotViewModel extends ViewModel<ChatBotState> {

    public ChatBotViewModel() {
        super("chatbot default");
        setState(new ChatBotState());
    }

}
