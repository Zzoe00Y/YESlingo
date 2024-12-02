package use_case.chatbot;

import entity.User;

/**
 * DAO for the ChatBot Use Case.
 */
public interface ChatBotUserDataAccessInterface {

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Return the user.
     * @param username the username of the user
     * @return the User object
     */
    User get(String username);
}
