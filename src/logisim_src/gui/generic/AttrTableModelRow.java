/* Copyright (c) 2011, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.generic;

import java.awt.Component;
import java.awt.Window;

public interface AttrTableModelRow {
	public String getLabel();
	public String getValue();
	public boolean isValueEditable();
	public Component getEditor(Window parent);
	public void setValue(Object value) throws AttrTableSetException;
}
