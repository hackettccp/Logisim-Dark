/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.circuit;

public class SimulatorEvent {
	private Simulator source;

	public SimulatorEvent(Simulator source) {
		this.source = source;
	}

	public Simulator getSource() {
		return source;
	}
}
