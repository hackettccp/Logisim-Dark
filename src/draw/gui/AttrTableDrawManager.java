/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import draw.canvas.Canvas;
import draw.tools.AbstractTool;
import draw.tools.DrawingAttributeSet;
import draw.tools.SelectTool;
import logisim_src.gui.generic.AttrTable;

public class AttrTableDrawManager implements PropertyChangeListener {
	private Canvas canvas;
	private AttrTable table;
	private AttrTableSelectionModel selectionModel;
	private AttrTableToolModel toolModel;
	
	public AttrTableDrawManager(Canvas canvas, AttrTable table, DrawingAttributeSet attrs) {
		this.canvas = canvas;
		this.table = table;
		this.selectionModel = new AttrTableSelectionModel(canvas);
		this.toolModel = new AttrTableToolModel(attrs, null);
		
		canvas.addPropertyChangeListener(Canvas.TOOL_PROPERTY, this);
		updateToolAttributes();
	}
	
	public void attributesSelected() {
		updateToolAttributes();
	}

	//
	// PropertyChangeListener method
	//
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (prop.equals(Canvas.TOOL_PROPERTY)) {
			updateToolAttributes();
		}
	}
	
	private void updateToolAttributes() {
		Object tool = canvas.getTool();
		if (tool instanceof SelectTool) {
			table.setAttrTableModel(selectionModel);
		} else if (tool instanceof AbstractTool) {
			toolModel.setTool((AbstractTool) tool);
			table.setAttrTableModel(toolModel);
		} else {
			table.setAttrTableModel(null);
		}
	}
}
