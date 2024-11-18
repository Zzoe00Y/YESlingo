package view;

import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;

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

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        profileViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Profile");
        final JButton exit = new JButton("Exit");
        final JLabel username = new JLabel("Username: ");
        final JLabel password = new JLabel("Password: ");
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

        exit.addActionListener(this);

        changePassword.addActionListener(this);

        changeLanguage.addActionListener(this);

        logout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ProfileState state = (ProfileState) evt.getNewValue();
//        if (state.getUsername() != null) {
//            JOptionPane.showMessageDialog(this, state.getUsername());
    }

    public String getViewName() {
        return viewName;
    }

    public void setProfileController(ProfileController controller) {
        this.profileController = controller;
    }
}
