/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.log;

import logisim_src.data.Value;

interface ModelListener {
	public void selectionChanged(ModelEvent event);
	public void entryAdded(ModelEvent event, Value[] values);
	public void filePropertyChanged(ModelEvent event);
}
