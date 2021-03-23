/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.log;

import logisim_src.circuit.CircuitState;
import logisim_src.data.Value;

public interface Loggable {
	public Object[] getLogOptions(CircuitState state);
	public String getLogName(Object option);
	public Value getLogValue(CircuitState state, Object option);
}
