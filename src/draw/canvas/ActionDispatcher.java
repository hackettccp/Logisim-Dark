/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.canvas;

import draw.undo.Action;

public interface ActionDispatcher {
	public void doAction(Action action);
}
