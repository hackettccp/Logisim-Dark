/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.tools;

import java.awt.Graphics;
import java.util.List;

import javax.swing.Icon;

import draw.model.CanvasObject;
import draw.shapes.DrawAttr;
import draw.shapes.Rectangle;
import logisim_src.data.Attribute;
import logisim_src.util.Icons;

public class RectangleTool extends RectangularTool {
	private DrawingAttributeSet attrs;
	
	public RectangleTool(DrawingAttributeSet attrs) {
		this.attrs = attrs;
	}
	
	@Override
	public Icon getIcon() {
		return Icons.getIcon("drawrect.gif");
	}
	
	@Override
	public List<Attribute<?>> getAttributes() {
		return DrawAttr.getFillAttributes(attrs.getValue(DrawAttr.PAINT_TYPE));
	}
	
	@Override
	public CanvasObject createShape(int x, int y, int w, int h) {
		return attrs.applyTo(new Rectangle(x, y, w, h));
	}

	@Override
	public void drawShape(Graphics g, int x, int y, int w, int h) {
		g.drawRect(x, y, w, h);
	}

	@Override
	public void fillShape(Graphics g, int x, int y, int w, int h) {
		g.fillRect(x, y, w, h);
	}
}
