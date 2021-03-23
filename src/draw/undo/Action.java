/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.undo;

public abstract class Action {
	public boolean isModification() { return true; }

	public abstract String getName();

	public abstract void doIt();

	public abstract void undo();

	public boolean shouldAppendTo(Action other) { return false; }

	public Action append(Action other) {
		return new ActionUnion(this, other);
	}
}
