/* Copyright (c) 2011, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.main;

import logisim_src.data.Attribute;
import logisim_src.gui.generic.AttributeSetTableModel;
import logisim_src.proj.Project;
import logisim_src.tools.Tool;

public class AttrTableToolModel extends AttributeSetTableModel {
	Project proj;
	Tool tool;
	
	public AttrTableToolModel(Project proj, Tool tool) {
		super(tool.getAttributeSet());
		this.proj = proj;
		this.tool = tool;
	}
	
	@Override
	public String getTitle() {
		return Strings.get("toolAttrTitle", tool.getDisplayName());
	}
	
	public Tool getTool() {
		return tool;
	}
	
	@Override
	public void setValueRequested(Attribute<Object> attr, Object value) {
		proj.doAction(ToolAttributeAction.create(tool, attr, value));
	}
}
