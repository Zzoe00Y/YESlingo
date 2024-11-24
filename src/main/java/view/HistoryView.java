package view;

import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.profile.ProfileState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user want to check history.
 */
public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "history";
    private final HistoryViewModel historyViewModel;
    private HistoryController historyController;

    public HistoryView(HistoryViewModel historyViewModel) {

        this.historyViewModel = historyViewModel;
        historyViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("History");
        final JButton clearAll = new JButton("Clear all");
        final JButton exit = new JButton("Exit");


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
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(clearAll, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(exit, gbc);

        setHistoryController(historyController);

        clearAll.addActionListener(this);

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        historyController.switchToLoggedInView();
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
        final ProfileState state = (ProfileState) evt.getNewValue();
    }

    public String getViewName() {
        return viewName;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }
}
