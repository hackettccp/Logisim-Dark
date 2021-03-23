/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.instance;

import logisim_src.comp.ComponentState;

public interface InstanceData extends ComponentState {
	public Object clone();
}
