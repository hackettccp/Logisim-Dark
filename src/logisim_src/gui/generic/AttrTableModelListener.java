/* Copyright (c) 2011, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.generic;

public interface AttrTableModelListener {
	public void attrTitleChanged(AttrTableModelEvent event);
	public void attrStructureChanged(AttrTableModelEvent event);
	public void attrValueChanged(AttrTableModelEvent event);
}
