/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.analyze.model;

public interface TruthTableListener {
	public void cellsChanged(TruthTableEvent event);
	public void structureChanged(TruthTableEvent event);
}
