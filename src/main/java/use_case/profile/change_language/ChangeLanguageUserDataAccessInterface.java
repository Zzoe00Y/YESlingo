package use_case.profile.change_language;

import entity.User;
import view.ChangeLanguageView;

public interface ChangeLanguageUserDataAccessInterface {
    void changeOutputLanguage(User user, ChangeLanguageView.LanguageItem selectedLanguage);

    User get(String username);
}
