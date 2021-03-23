/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools.move;

public interface MoveRequestListener {
	public void requestSatisfied(MoveGesture gesture, int dx, int dy);
}
