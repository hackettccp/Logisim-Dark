/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.undo;

import java.util.EventListener;

public interface UndoLogListener extends EventListener {
	public void undoLogChanged(UndoLogEvent e);
}
