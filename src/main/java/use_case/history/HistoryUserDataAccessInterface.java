package use_case.history;

import entity.User;

/**
 * DAO for the History Use Case.
 */
public interface HistoryUserDataAccessInterface {
    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user.
     * @param username the username of the user
     * @return the User object
     */
    User get(String username);
}
