package view;

import interface_adapter.profile.change_password.ChangePasswordController;
import interface_adapter.profile.change_password.ChangePasswordState;
import interface_adapter.profile.change_password.ChangePasswordViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChangePasswordView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "changePassword";
    private final ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordController changePasswordController;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {

        this.changePasswordViewModel = changePasswordViewModel;
        changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Change Password");
        final JButton exit = new JButton("Exit");
        final LabelTextPanel oldPassword = new LabelTextPanel(new JLabel("Old password: "), new JTextField(15));
        final LabelTextPanel newPassword = new LabelTextPanel(new JLabel("New password: "), new JTextField(15));
        final LabelTextPanel repeatNewPassword = new LabelTextPanel(new JLabel("Repeat new password: "), new JTextField(15));
        final JButton ok = new JButton("OK");

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
        this.add(oldPassword, gbc);
        gbc.gridy = 2;
        this.add(newPassword, gbc);
        gbc.gridy = 3;
        this.add(repeatNewPassword, gbc);
        gbc.gridy = 4;
        this.add(ok, gbc);

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        changePasswordController.switchToProfileView();
                    }
                }
        );

        ok.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChangePasswordState state = (ChangePasswordState) evt.getSource();
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
