/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.undo;

import draw.canvas.ActionDispatcher;

public class UndoLogDispatcher implements ActionDispatcher {
	private UndoLog log;
	
	public UndoLogDispatcher(UndoLog log) {
		this.log = log;
	}
	
	public void doAction(Action action) {
		log.doAction(action);
	}
}
