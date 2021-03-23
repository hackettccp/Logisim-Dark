/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.data;

public interface AttributeListener {
	public void attributeListChanged(AttributeEvent e);
	public void attributeValueChanged(AttributeEvent e);
}
