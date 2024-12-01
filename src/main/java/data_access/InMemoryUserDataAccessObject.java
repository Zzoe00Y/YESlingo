package data_access;

import entity.User;
import use_case.profile.change_language.ChangeLanguageUserDataAccessInterface;
import use_case.profile.change_password.ChangePasswordUserDataAccessInterface;
import use_case.chatbot.ChatBotUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import view.ChangeLanguageView;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        ChangeLanguageUserDataAccessInterface,
        ChatBotUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;
    private String currentPassword;
    private String currentLanguage;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user, String password) {
        // Replace the old entry with the new password
        this.currentPassword = password;
        get(user.getName()).setPassword(password);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void changeOutputLanguage(String username, ChangeLanguageView.LanguageItem selectedLanguage) {
        this.currentLanguage = selectedLanguage.getDisplayName();
        get(username).setOutputLan(selectedLanguage.getDisplayName());
    }
}
