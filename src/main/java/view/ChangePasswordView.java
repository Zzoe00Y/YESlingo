package view;

import interface_adapter.profile.change_password.ChangePasswordController;
import interface_adapter.profile.change_password.ChangePasswordState;
import interface_adapter.profile.change_password.ChangePasswordViewModel;
import interface_adapter.signup.SignupState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChangePasswordView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "changePassword";
    private final ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordController changePasswordController;

    private final JTextField oldPasswordField = new JTextField(15);
    private final JTextField newPasswordField = new JTextField(15);
    private final JTextField repeatNewPasswordField = new JTextField(15);

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {

        this.changePasswordViewModel = changePasswordViewModel;
        changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Change Password");
        final JButton exit = new JButton("Exit");
        final LabelTextPanel oldPassword = new LabelTextPanel(new JLabel("Old password: "), oldPasswordField);
        final LabelTextPanel newPassword = new LabelTextPanel(new JLabel("New password: "), newPasswordField);
        final LabelTextPanel repeatNewPassword = new LabelTextPanel(new JLabel("Repeat new password: "), repeatNewPasswordField);
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

        addOldPasswordListener();
        addNewPasswordListener();
        addRepeatNewPasswordListener();

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        changePasswordController.switchToProfileView();
                    }
                }
        );

        ok.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        //if (evt.getSource().equals(ok)) {
                            final ChangePasswordState currentState = changePasswordViewModel.getState();
                            changePasswordController.execute(currentState.getUsername(),
                                                             currentState.getNewPassword(),
                                                             currentState.getRepeatPassword(),
                                                             currentState.getOldPassword()
                            );
                    }

                }
        );
    }

    private void addOldPasswordListener() {
        oldPasswordField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setOldPassword(oldPasswordField.getText());
                changePasswordViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addNewPasswordListener() {
        newPasswordField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setNewPassword(newPasswordField.getText());
                changePasswordViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addRepeatNewPasswordListener() {
        repeatNewPasswordField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setRepeatPassword(repeatNewPasswordField.getText());
                changePasswordViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
        if (state.getOldPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getOldPasswordError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
