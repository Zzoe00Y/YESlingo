package view;

import interface_adapter.profile.change_language.ChangeLanguageController;
import interface_adapter.profile.change_language.ChangeLanguageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user want to change preferred language.
 */
public class ChangeLanguageView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "changeLanguage";
    private final ChangeLanguageViewModel changeLanguageViewModel;
    private ChangeLanguageController changeLanguageController;

    public ChangeLanguageView(ChangeLanguageViewModel changeLanguageViewModel) {
        this.changeLanguageViewModel = changeLanguageViewModel;
        changeLanguageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Change Language");
        final JButton exit = new JButton("Exit");
        final JComboBox<String> defaultOutputLanguage = new JComboBox<>(new String[]{"English", "Spanish", "French"});
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
        this.add(defaultOutputLanguage, gbc);
        gbc.gridy = 2;
        this.add(ok, gbc);

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        changeLanguageController.switchToProfileView();
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

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangeLanguageController(ChangeLanguageController changeLanguageController) {
        this.changeLanguageController = changeLanguageController;
    }
}
