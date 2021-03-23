/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.gui;

import draw.tools.AbstractTool;
import draw.tools.DrawingAttributeSet;
import logisim_src.data.Attribute;
import logisim_src.gui.generic.AttrTableSetException;
import logisim_src.gui.generic.AttributeSetTableModel;

class AttrTableToolModel extends AttributeSetTableModel {
	private DrawingAttributeSet defaults;
	private AbstractTool currentTool;
	
	public AttrTableToolModel(DrawingAttributeSet defaults, AbstractTool tool) {
		super(defaults.createSubset(tool));
		this.defaults = defaults;
		this.currentTool = tool;
	}
	
	public void setTool(AbstractTool value) {
		currentTool = value;
		setAttributeSet(defaults.createSubset(value));
		fireTitleChanged();
	}
	
	@Override
	public String getTitle() {
		return currentTool.getDescription();
	}

	@Override
	public void setValueRequested(Attribute<Object> attr, Object value)
			throws AttrTableSetException {
		defaults.setValue(attr, value);
	}
}
