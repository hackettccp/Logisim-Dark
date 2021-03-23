/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.gates;

import java.awt.Graphics;

import logisim_src.analyze.model.Expression;
import logisim_src.analyze.model.Expressions;
import logisim_src.data.AttributeSet;
import logisim_src.data.Value;
import logisim_src.instance.Instance;
import logisim_src.instance.InstancePainter;
import logisim_src.instance.InstanceState;
import logisim_src.tools.WireRepairData;
import logisim_src.util.GraphicsUtil;

class XnorGate extends AbstractGate {
	public static XnorGate FACTORY = new XnorGate();

	private XnorGate() {
		super("XNOR Gate", Strings.getter("xnorGateComponent"), true);
		setNegateOutput(true);
		setAdditionalWidth(10);
		setIconNames("xnorGate.gif", "xnorGateRect.gif", "dinXnorGate.gif");
		setPaintInputLines(true);
	}

	@Override
	protected String getRectangularLabel(AttributeSet attrs) {
		return XorGate.FACTORY.getRectangularLabel(attrs);
	}
	
	@Override
	public void paintIconShaped(InstancePainter painter) {
		Graphics g = painter.getGraphics();
		GraphicsUtil.drawCenteredArc(g,   0, - 5, 22, -90,  53);
		GraphicsUtil.drawCenteredArc(g,   0, 23, 22,  90, -53);
		GraphicsUtil.drawCenteredArc(g,  -8,  9, 16, -30, 60);
		GraphicsUtil.drawCenteredArc(g, -10,  9, 16, -30, 60);
		g.drawOval(16, 8, 4, 4);
	}

	@Override
	protected void paintShape(InstancePainter painter, int width, int height) {
		PainterShaped.paintXor(painter, width, height);
	}

	@Override
	protected void paintDinShape(InstancePainter painter, int width, int height,
			int inputs) {
		PainterDin.paintXnor(painter, width, height, false);
	}

	@Override
	protected Value computeOutput(Value[] inputs, int numInputs, InstanceState state) {
		Object behavior = state.getAttributeValue(GateAttributes.ATTR_XOR);
		if (behavior == GateAttributes.XOR_ODD) {
			return GateFunctions.computeOddParity(inputs, numInputs).not();
		} else {
			return GateFunctions.computeExactlyOne(inputs, numInputs).not();
		}
	}

	@Override
	protected boolean shouldRepairWire(Instance instance, WireRepairData data) {
		return !data.getPoint().equals(instance.getLocation());
	}

	@Override
	protected Expression computeExpression(Expression[] inputs, int numInputs) {
		return Expressions.not(XorGate.xorExpression(inputs, numInputs));
	}

	@Override
	protected Value getIdentity() { return Value.FALSE; }
}
