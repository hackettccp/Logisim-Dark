/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools;

import javax.swing.JPopupMenu;

import logisim_src.proj.Project;

public interface MenuExtender {
	public void configureMenu(JPopupMenu menu, Project proj);
}
