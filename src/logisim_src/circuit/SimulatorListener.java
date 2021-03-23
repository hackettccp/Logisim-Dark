/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.circuit;

public interface SimulatorListener {
	public void propagationCompleted(SimulatorEvent e);
	public void tickCompleted(SimulatorEvent e);
	public void simulatorStateChanged(SimulatorEvent e);
}
