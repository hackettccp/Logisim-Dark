/* Copyright (c) 2011, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.generic;

public interface AttrTableModel {
	public void addAttrTableModelListener(AttrTableModelListener listener);
	public void removeAttrTableModelListener(AttrTableModelListener listener);
	
	public String getTitle();
	public int getRowCount();
	public AttrTableModelRow getRow(int rowIndex);
}
