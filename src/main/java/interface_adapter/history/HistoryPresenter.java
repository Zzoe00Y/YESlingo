package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.chatbot.ChatBotState;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import use_case.history.HistoryOutputBoundary;

/**
 * The Presenter for the History Use Case.
 */
public class HistoryPresenter implements HistoryOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public HistoryPresenter(ViewManagerModel viewManagerModel,
                            LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void pullUser(HistoryState newState) {
        historyViewModel.setState(newState);
    }

    @Override
    public void clearAll(HistoryState newState) {
        historyViewModel.setState(newState);
        historyViewModel.firePropertyChanged("clearAll");
    }
}
