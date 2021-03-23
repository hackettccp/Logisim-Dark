/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.canvas;

import java.util.EventListener;

public interface SelectionListener extends EventListener {
	public void selectionChanged(SelectionEvent e);
}
