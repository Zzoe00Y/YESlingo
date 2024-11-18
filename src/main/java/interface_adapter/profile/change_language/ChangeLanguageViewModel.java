package interface_adapter.profile.change_language;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Change Language View.
 */
public class ChangeLanguageViewModel extends ViewModel<ChangeLanguageState> {

    public ChangeLanguageViewModel() {
        super("changeLanguage");
        setState(new ChangeLanguageState());
    }
}
