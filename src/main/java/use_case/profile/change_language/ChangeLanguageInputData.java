package use_case.profile.change_language;

import view.ChangeLanguageView;

public class ChangeLanguageInputData {
    private ChangeLanguageView.LanguageItem language;
    private String username;

    public ChangeLanguageInputData(String username,
                                   ChangeLanguageView.LanguageItem language) {
        this.username = username;
        this.language = language;
    }
    public ChangeLanguageView.LanguageItem getLanguage() {
        return language;
    }

    public String getUsername() {
        return username;
    }
}
