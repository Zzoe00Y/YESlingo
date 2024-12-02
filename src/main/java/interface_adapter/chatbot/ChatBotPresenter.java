package interface_adapter.chatbot;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import use_case.chatbot.ChatBotOutputBoundary;
import use_case.chatbot.ChatBotOutputData;

/**
 * The Presenter for the ChatBot Use Case.
 */
public class ChatBotPresenter implements ChatBotOutputBoundary {

    private final ChatBotViewModel chatbotViewModel;
    private final LoggedInViewModel loggedinViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChatBotPresenter(ViewManagerModel viewManagerModel,
                            ChatBotViewModel chatbotViewModel,
                            LoggedInViewModel loggedinViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chatbotViewModel = chatbotViewModel;
        this.loggedinViewModel = loggedinViewModel;
    }

    @Override
    public void displayResponse(ChatBotOutputData outputData) {
        final ChatBotState currentState = chatbotViewModel.getState();
        currentState.setNewResponse(outputData.getResponse());
        chatbotViewModel.firePropertyChanged("sentChat");
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedinViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void pullUser(ChatBotState newState) {
        chatbotViewModel.setState(newState);
    }
}
