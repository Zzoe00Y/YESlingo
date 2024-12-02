package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

import java.util.ArrayList;

/**
 * The Presenter for the History Use Case.
 */
public class HistoryPresenter implements HistoryOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final HistoryViewModel historyViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public HistoryPresenter(ViewManagerModel viewManagerModel,
                            HistoryViewModel historyViewModel,
                            LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.historyViewModel = historyViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void pullUser(HistoryOutputData outputData) {
        // Transforming HistoryOutputData to HistoryState
        HistoryState newState = new HistoryState(outputData.getUsername(),
                new ArrayList<>(outputData.getHistoryMessages()));
        historyViewModel.setState(newState);
    }

    @Override
    public void clearAll(HistoryOutputData outputData) {
        // Transforming HistoryOutputData to HistoryState
        HistoryState newState = new HistoryState(outputData.getUsername(),
                new ArrayList<>(outputData.getHistoryMessages()));
        historyViewModel.setState(newState);
        historyViewModel.firePropertyChanged("clearAll");
    }
}
