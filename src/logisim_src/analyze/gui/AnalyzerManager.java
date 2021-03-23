/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.analyze.gui;

import javax.swing.JFrame;

import logisim_src.util.LocaleListener;
import logisim_src.util.LocaleManager;
import logisim_src.util.WindowMenuItemManager;

public class AnalyzerManager extends WindowMenuItemManager
		implements LocaleListener {
	public static void initialize() {
		analysisManager = new AnalyzerManager();
	}
	
	public static Analyzer getAnalyzer() {
		if (analysisWindow == null) {
			analysisWindow = new Analyzer();
			analysisWindow.pack();
			if (analysisManager != null) analysisManager.frameOpened(analysisWindow);
		}
		return analysisWindow;
	}
	
	private static Analyzer analysisWindow = null;
	private static AnalyzerManager analysisManager = null;

	private AnalyzerManager() {
		super(Strings.get("analyzerWindowTitle"), true);
		LocaleManager.addLocaleListener(this);
	}
	
	@Override
	public JFrame getJFrame(boolean create) {
		if (create) {
			return getAnalyzer();
		} else {
			return analysisWindow;
		}
	}

	public void localeChanged() {
		setText(Strings.get("analyzerWindowTitle"));
	}
}
