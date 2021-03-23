/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.analyze.gui;

import javax.swing.JPanel;

abstract class AnalyzerTab extends JPanel {
	abstract void updateTab();
	abstract void localeChanged();
}
