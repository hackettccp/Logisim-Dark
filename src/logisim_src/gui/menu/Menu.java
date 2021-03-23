/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.menu;

import javax.swing.JMenu;

abstract class Menu extends JMenu {
	abstract void computeEnabled();
}
