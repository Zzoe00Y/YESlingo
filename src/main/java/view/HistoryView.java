package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;

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

        this.setLayout(new BorderLayout());
        buildView();

        setHistoryController(historyController);
    }

    private JScrollPane returnScrollPanel() {
        final HistoryState currentState = historyViewModel.getState();

        final JPanel textsPanel = new JPanel();
        textsPanel.setLayout(new BoxLayout(textsPanel, BoxLayout.Y_AXIS));

        if (currentState.getHistoryMessages().isEmpty()) {
            final String defaultMessage = "Translation history is empty";
            final JLabel noContentLabel = new JLabel(defaultMessage);
            textsPanel.add(noContentLabel, BorderLayout.CENTER);
        }
        else {
            for (String message: currentState.getHistoryMessages()) {
                final String text = message;
                final JTextArea textArea = new JTextArea(text);
                textArea.setColumns(50);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);

                final JPanel textPanel = new JPanel(new BorderLayout());

                textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                textPanel.add(textArea, BorderLayout.CENTER);

                textsPanel.add(textPanel);
            }
        }
        return new JScrollPane(textsPanel);
    }

    private void buildView() {
        this.removeAll();

        final JPanel headerPanel = returnHeaderPanel();
        this.add(headerPanel, BorderLayout.NORTH);

        final JScrollPane scrollPane = returnScrollPanel();
        this.add(scrollPane);

        this.revalidate();
        this.repaint();
    }

    private JPanel returnHeaderPanel() {
        final JLabel title = new JLabel("History");
        final JButton clearAll = new JButton("Clear all");
        final JButton exit = new JButton("Exit");

        final JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        headerPanel.add(title, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        headerPanel.add(clearAll, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        headerPanel.add(exit, gbc);

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        historyController.switchToLoggedInView();
                    }
                }
        );

        clearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                historyController.clearAll(historyViewModel.getState().getUsername());
            }
        });

        return headerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HistoryState state = (HistoryState) evt.getNewValue();

        // If the view needs to get updated info from user according to the current state's username
        if ("reset".equals(evt.getPropertyName())) {
            historyController.pullUser(state.getUsername());
            buildView();
        }

        // If the view needs to get updated info from user according to the current state's username
        if ("clearAll".equals(evt.getPropertyName())) {
            buildView();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }
}
