/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.tools;

import java.awt.Graphics;
import java.util.List;

import javax.swing.Icon;

import draw.model.CanvasObject;
import draw.shapes.DrawAttr;
import draw.shapes.Oval;
import logisim_src.data.Attribute;
import logisim_src.util.Icons;

public class OvalTool extends RectangularTool {
	private DrawingAttributeSet attrs;
	
	public OvalTool(DrawingAttributeSet attrs) {
		this.attrs = attrs;
	}
	
	@Override
	public Icon getIcon() {
		return Icons.getIcon("drawoval.gif");
	}
	
	@Override
	public List<Attribute<?>> getAttributes() {
		return DrawAttr.getFillAttributes(attrs.getValue(DrawAttr.PAINT_TYPE));
	}

	@Override
	public CanvasObject createShape(int x, int y, int w, int h) {
		return attrs.applyTo(new Oval(x, y, w, h));
	}

	@Override
	public void drawShape(Graphics g, int x, int y, int w, int h) {
		g.drawOval(x, y, w, h);
	}

	@Override
	public void fillShape(Graphics g, int x, int y, int w, int h) {
		g.fillOval(x, y, w, h);
	}
}
