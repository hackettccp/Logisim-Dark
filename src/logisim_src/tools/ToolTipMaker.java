/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools;

import logisim_src.comp.ComponentUserEvent;

public interface ToolTipMaker {
	public String getToolTip(ComponentUserEvent event);
}
