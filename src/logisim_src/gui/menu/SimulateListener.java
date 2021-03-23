/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.menu;

import logisim_src.circuit.CircuitState;
import logisim_src.circuit.Simulator;

public interface SimulateListener {
	public void stateChangeRequested(Simulator sim, CircuitState state);
}
