package use_case.profile.change_language;

import entity.User;
import view.ChangeLanguageView;

public interface ChangeLanguageUserDataAccessInterface {

    void changeOutputLanguage(String username, ChangeLanguageView.LanguageItem selectedLanguage);

    void save(User user);

}
