package view;

import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.login.LoginOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user want to check profile.
 */
public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "profile";
    private final ProfileViewModel profileViewModel;
    private ProfileController profileController;
    private final JLabel username;
    private final JLabel password;

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        profileViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Profile");
        final JButton exit = new JButton("Exit");
        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        final JButton changePassword = new JButton("Change Password");
        final JLabel preferredLanguage = new JLabel("Preferred Language: ");
        final JButton changeLanguage = new JButton("Change Language");
        final JButton logout = new JButton("Log Out");

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(title, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(exit, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(username, gbc);
        gbc.gridy = 2;
        this.add(password, gbc);
        gbc.gridy = 3;
        this.add(changePassword, gbc);
        gbc.gridy = 4;
        this.add(preferredLanguage, gbc);
        gbc.gridy = 5;
        this.add(changeLanguage, gbc);
        gbc.gridy = 6;
        this.add(logout, gbc);

        setProfileController(profileController);

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        profileController.switchToLoggedInView();
                    }
                }
        );

        changePassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        profileController.switchToChangePasswordView();
                    }
                }
        );

        changeLanguage.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        profileController.switchToChangeLanguageView();
                    }
                }
        );

        logout.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        profileController.switchToLogInView();
                    }
                }
        );
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //if ("state".equals(evt.getPropertyName())) {
            ProfileState state = (ProfileState) evt.getNewValue();
            username.setText("Username: " + state.getUsername());
            password.setText("Password: " + state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
}
