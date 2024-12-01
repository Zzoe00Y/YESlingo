package interface_adapter.loggedin_homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.ChatBotState;
import interface_adapter.chatbot.ChatBotViewModel;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.profile.ProfileViewModel;
import use_case.loggedin.LoggedInOutputBoundary;

/**
 * The Presenter for the Loggedin Use Case.
 */
public class LoggedInPresenter implements LoggedInOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ProfileViewModel profileViewModel;
    private final HistoryViewModel historyViewModel;
    private final ChatBotViewModel chatBotViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             ProfileViewModel profileViewModel, HistoryViewModel historyViewModel,
                             ChatBotViewModel chatBotViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.profileViewModel = profileViewModel;
        this.historyViewModel = historyViewModel;
        this.chatBotViewModel = chatBotViewModel;
    }

    public void switchToProfileView() {
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToChatBotView(String username) {
        final ChatBotState chatBotState = chatBotViewModel.getState();
        chatBotState.setUsername(username);
        chatBotViewModel.setState(chatBotState);
        chatBotViewModel.firePropertyChanged("reset");

        viewManagerModel.setState(chatBotViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHistoryView(String username) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setUsername(username);
        historyViewModel.setState(historyState);
        historyViewModel.firePropertyChanged("reset");

        viewManagerModel.setState(historyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        viewManagerModel.setState(historyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
