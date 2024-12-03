package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.profile.change_language.ChangeLanguageController;
import interface_adapter.profile.change_language.ChangeLanguageState;
import interface_adapter.profile.change_language.ChangeLanguageViewModel;

/**
 * The View for when the user wants to change preferred language.
 */
public class ChangeLanguageView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * The name of this view.
     */
    private final String viewName = "changeLanguage";

    /**
     * The view model for language changes.
     */
    private final ChangeLanguageViewModel changeLanguageViewModel;

    /**
     * The controller for language changes.
     */
    private ChangeLanguageController changeLanguageController;

    private static final int INSET_SIZE = 5;

    /**
     * Creates a new ChangeLanguageView.
     * @param changeLanguageViewModel the view model for language changes, must not be null
     */
    public ChangeLanguageView(ChangeLanguageViewModel changeLanguageViewModel) {
        this.changeLanguageViewModel = changeLanguageViewModel;
        changeLanguageViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Change Language");
        final JButton exit = new JButton("Exit");
        final JComboBox<LanguageItem> defaultOutputLanguage = createLanguageComboBox();
        final JButton ok = new JButton("OK");

        this.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
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
                evt -> changeLanguageController.switchToProfileView()
        );

        ok.addActionListener(
                evt -> {
                    final ChangeLanguageState currentState = changeLanguageViewModel.getState();
                    changeLanguageController.execute(currentState.getUsername(),
                            (LanguageItem) defaultOutputLanguage.getSelectedItem());
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    /**
     * Gets the name of this view.
     * @return the view name
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the controller for this view.
     * @param changeLanguageController the controller to set, must not be null
     */
    public void setChangeLanguageController(ChangeLanguageController changeLanguageController) {
        this.changeLanguageController = changeLanguageController;
    }

    /**
     * Represents a language option with display name and language code.
     */
    public static class LanguageItem {
        private final String displayName;
        private final String code;

        /**
         * Creates a new LanguageItem.
         * @param displayName the display name of the language, must not be null
         * @param code the language code, must not be null
         */
        public LanguageItem(String displayName, String code) {
            this.displayName = displayName;
            this.code = code;
        }

        @Override
        public String toString() {
            return displayName;
        }

        /**
         * Gets the display name of the language.
         * @return the display name
         */
        public String getDisplayName() {
            return displayName;
        }
    }

    private JComboBox<LanguageItem> createLanguageComboBox() {
        final DefaultComboBoxModel<LanguageItem> model = new DefaultComboBoxModel<>();
        final String[][] languages = {
                {"English", "en"}, {"Spanish", "es"}, {"French", "fr"},
                {"German", "de"}, {"Italian", "it"}, {"Portuguese", "pt"},
                {"Chinese", "zh-CN"}, {"Japanese", "ja"}, {"Korean", "ko"},
                {"Russian", "ru"}, {"Arabic", "ar"}, {"Dutch", "nl"},
                {"Greek", "el"}, {"Hebrew", "he"}, {"Hindi", "hi"},
                {"Polish", "pl"}, {"Turkish", "tr"}, {"Vietnamese", "vi"},
        };
        for (String[] lang : languages) {
            model.addElement(new LanguageItem(lang[0], lang[1]));
        }
        return new JComboBox<>(model);
    }
}
