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

class XorGate extends AbstractGate {
	public static XorGate FACTORY = new XorGate();

	private XorGate() {
		super("XOR Gate", Strings.getter("xorGateComponent"), true);
		setAdditionalWidth(10);
		setIconNames("xorGate.gif", "xorGateRect.gif", "dinXorGate.gif");
		setPaintInputLines(true);
	}

	@Override
	public String getRectangularLabel(AttributeSet attrs) {
		if (attrs == null) return "";
		boolean isOdd = false;
		Object behavior = attrs.getValue(GateAttributes.ATTR_XOR);
		if (behavior == GateAttributes.XOR_ODD) {
			Object inputs = attrs.getValue(GateAttributes.ATTR_INPUTS);
			if (inputs == null || ((Integer) inputs).intValue() != 2) {
				isOdd = true;
			}
		}
		return isOdd ? "2k+1" : "=1";
	}

	@Override
	public void paintIconShaped(InstancePainter painter) {
		Graphics g = painter.getGraphics();
		GraphicsUtil.drawCenteredArc(g,   2, -5, 22, -90,  53);
		GraphicsUtil.drawCenteredArc(g,   2, 23, 22,  90, -53);
		GraphicsUtil.drawCenteredArc(g, -10,  9, 16, -30, 60);
		GraphicsUtil.drawCenteredArc(g, -12,  9, 16, -30, 60);
	}

	@Override
	protected void paintShape(InstancePainter painter, int width, int height) {
		PainterShaped.paintXor(painter, width, height);
	}

	@Override
	protected void paintDinShape(InstancePainter painter, int width, int height,
			int inputs) {
		PainterDin.paintXor(painter, width, height, false);
	}

	@Override
	protected Value computeOutput(Value[] inputs, int numInputs,
			InstanceState state) {
		Object behavior = state.getAttributeValue(GateAttributes.ATTR_XOR);
		if (behavior == GateAttributes.XOR_ODD) {
			return GateFunctions.computeOddParity(inputs, numInputs);
		} else {
			return GateFunctions.computeExactlyOne(inputs, numInputs);
		}
	}

	@Override
	protected boolean shouldRepairWire(Instance instance, WireRepairData data) {
		return !data.getPoint().equals(instance.getLocation());
	}

	@Override
	protected Expression computeExpression(Expression[] inputs, int numInputs) {
		return xorExpression(inputs, numInputs);
	}

	@Override
	protected Value getIdentity() { return Value.FALSE; }
	
	protected static Expression xorExpression(Expression[] inputs, int numInputs) {
		if (numInputs > 2) {
			throw new UnsupportedOperationException("XorGate");
		}
		Expression ret = inputs[0];
		for (int i = 1; i < numInputs; i++) {
			ret = Expressions.xor(ret, inputs[i]);
		}
		return ret;
	}
}
