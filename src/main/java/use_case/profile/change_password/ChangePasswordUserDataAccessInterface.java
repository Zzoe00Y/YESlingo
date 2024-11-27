package use_case.profile.change_password;

import entity.User;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     *
     * @param user the user whose password is to be updated
     * @param newPassword the new password
     */
    void changePassword(User user, String newPassword);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);
}
