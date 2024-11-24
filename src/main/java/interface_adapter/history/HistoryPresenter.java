package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin_homepage.LoggedInViewModel;
import use_case.history.HistoryOutputBoundary;

public class HistoryPresenter implements HistoryOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    private final HistoryViewModel historyViewModel;

    public HistoryPresenter(ViewManagerModel viewManagerModel,
                            LoggedInViewModel loggedInViewModel,
                            HistoryViewModel historyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.historyViewModel = historyViewModel;
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
