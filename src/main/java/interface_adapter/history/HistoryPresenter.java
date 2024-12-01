package interface_adapter.history;

import interface_adapter.ViewManagerModel;
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
}
