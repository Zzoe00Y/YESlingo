package interface_adapter.chatbot;

import interface_adapter.ViewModel;
import interface_adapter.chatbot.ChatBotDefaultState;

/**
 * The View Model for the Login View.
 */
public class ChatBotDefaultViewModel extends ViewModel<ChatBotDefaultState> {

    public ChatBotDefaultViewModel() {
        super("chatbot default");
        setState(new ChatBotDefaultState());
    }

}
