/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.comp;

import logisim_src.circuit.CircuitState;
import logisim_src.gui.main.Canvas;


public class ComponentUserEvent {
	private Canvas canvas;
	private int x = 0;
	private int y = 0;

	ComponentUserEvent(Canvas canvas) {
		this.canvas = canvas;
	}

	public ComponentUserEvent(Canvas canvas, int x, int y) {
		this.canvas = canvas;
		this.x = x;
		this.y = y;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public CircuitState getCircuitState() {
		return canvas.getCircuitState();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
