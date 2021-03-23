/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.analyze.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;

import logisim_src.analyze.model.Entry;
import logisim_src.analyze.model.TruthTable;

interface TruthTablePanel {
	public static final Color ERROR_COLOR = new Color(255, 128, 128);

	public TruthTable getTruthTable();
	public int getOutputColumn(MouseEvent event);
	public int getRow(MouseEvent event);
	public void setEntryProvisional(int row, int col, Entry value);
}
