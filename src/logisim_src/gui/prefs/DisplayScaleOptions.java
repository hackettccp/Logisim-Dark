/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.prefs;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logisim_src.prefs.AppPreferences;

class DisplayScaleOptions extends OptionsPanel {
    private JLabel restart = new JLabel();
    private PrefOptionList displayScale;

    public DisplayScaleOptions(PreferencesFrame window) {
        super(window);

        displayScale = new PrefOptionList(AppPreferences.DISPLAY_SCALE,
                Strings.getter("Display Scale"),
                new PrefOption[]{
                        new PrefOption(AppPreferences.SCALE_100, Strings.getter("100% (Default)")),
                        new PrefOption(AppPreferences.SCALE_125, Strings.getter("125%")),
                        new PrefOption(AppPreferences.SCALE_150, Strings.getter("150%"))
                });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(displayScale.getJLabel(), BorderLayout.LINE_START);
        panel.add(displayScale.getJComboBox(), BorderLayout.CENTER);
        panel.add(restart, BorderLayout.PAGE_END);
        restart.setFont(restart.getFont().deriveFont(Font.ITALIC));
        JPanel panel2 = new JPanel();
        panel2.add(panel);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createGlue());
        add(panel2);
        add(Box.createGlue());

    }

    @Override
    public String getTitle() {
        return Strings.get("displayScaleTitle");
    }

    @Override
    public String getHelpText() {
        return Strings.get("displayScaleHelp");
    }

    @Override
    public void localeChanged() {
        displayScale.localeChanged();
        restart.setText(Strings.get("restartLabel"));
    }

}
