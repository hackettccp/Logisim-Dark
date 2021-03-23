/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.log;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import logisim_src.proj.Project;

abstract class LogPanel extends JPanel {
	private LogFrame logFrame;
	
	public LogPanel(LogFrame frame) {
		super();
		this.logFrame = frame;
	}
	
	public LogPanel(LogFrame frame, LayoutManager manager) {
		super(manager);
		this.logFrame = frame;
	}
	
	public abstract String getTitle();
	public abstract String getHelpText();
	public abstract void localeChanged();
	public abstract void modelChanged(Model oldModel, Model newModel);
	
	LogFrame getLogFrame() {
		return logFrame;
	}
	
	Project getProject() {
		return logFrame.getProject();
	}
	
	Model getModel() {
		return logFrame.getModel();
	}
	
	Selection getSelection() {
		return logFrame.getModel().getSelection();
	}
}
