/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.instance;

import logisim_src.data.Value;

public abstract class InstanceLogger {
	public Object[] getLogOptions(InstanceState state) { return null; }
	public abstract String getLogName(InstanceState state, Object option);
	public abstract Value getLogValue(InstanceState state, Object option);
}
