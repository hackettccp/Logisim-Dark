/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.toolbar;

public interface ToolbarModelListener {
	public void toolbarContentsChanged(ToolbarModelEvent event);
	public void toolbarAppearanceChanged(ToolbarModelEvent event);
}
