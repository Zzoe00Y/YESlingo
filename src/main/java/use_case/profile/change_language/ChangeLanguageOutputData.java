package use_case.profile.change_language;

import view.ChangeLanguageView;

public class ChangeLanguageOutputData {

    private String username;
    private ChangeLanguageView.LanguageItem language;

    public ChangeLanguageOutputData(String username, ChangeLanguageView.LanguageItem language) {
        this.username = username;
        this.language = language;
    }

    public String getUsername() {
        return username;
    }

    public ChangeLanguageView.LanguageItem getLanguage() {
        return language;
    }
}
