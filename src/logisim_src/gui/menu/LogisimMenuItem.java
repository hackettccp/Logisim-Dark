/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.menu;

public class LogisimMenuItem {
	private String name;
	
	LogisimMenuItem(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
