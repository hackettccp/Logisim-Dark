/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.appear;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import draw.canvas.Canvas;
import draw.toolbar.AbstractToolbarModel;
import draw.toolbar.ToolbarItem;
import draw.tools.AbstractTool;
import draw.tools.CurveTool;
import draw.tools.DrawingAttributeSet;
import draw.tools.LineTool;
import draw.tools.OvalTool;
import draw.tools.PolyTool;
import draw.tools.RectangleTool;
import draw.tools.RoundRectangleTool;
import draw.tools.TextTool;
import draw.tools.ToolbarToolItem;

class AppearanceToolbarModel extends AbstractToolbarModel
		implements PropertyChangeListener {
	private Canvas canvas;
	private List<ToolbarItem> items;
	
	public AppearanceToolbarModel(AbstractTool selectTool, Canvas canvas,
			DrawingAttributeSet attrs) {
		this.canvas = canvas;
		
		AbstractTool[] tools = {
				selectTool,
				new TextTool(attrs),
				new LineTool(attrs),
				new CurveTool(attrs),
				new PolyTool(false, attrs),
				new RectangleTool(attrs),
				new RoundRectangleTool(attrs),
				new OvalTool(attrs),
				new PolyTool(true, attrs),
			};

		ArrayList<ToolbarItem> rawItems = new ArrayList<ToolbarItem>();
		for (AbstractTool tool : tools) {
			rawItems.add(new ToolbarToolItem(tool));
		}
		items = Collections.unmodifiableList(rawItems);
		canvas.addPropertyChangeListener(Canvas.TOOL_PROPERTY, this);
	}
	
	AbstractTool getFirstTool() {
		ToolbarToolItem item = (ToolbarToolItem) items.get(0);
		return item.getTool();
	}

	@Override
	public List<ToolbarItem> getItems() {
		return items;
	}
	
	@Override
	public boolean isSelected(ToolbarItem item) {
		if (item instanceof ToolbarToolItem) {
			AbstractTool tool = ((ToolbarToolItem) item).getTool();
			return canvas != null && tool == canvas.getTool();
		} else {
			return false;
		}
	}

	@Override
	public void itemSelected(ToolbarItem item) {
		if (item instanceof ToolbarToolItem) {
			AbstractTool tool = ((ToolbarToolItem) item).getTool();
			canvas.setTool(tool);
			fireToolbarAppearanceChanged();
		}
	}

	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();
		if (Canvas.TOOL_PROPERTY.equals(prop)) {
			fireToolbarAppearanceChanged();
		}
	}
}
