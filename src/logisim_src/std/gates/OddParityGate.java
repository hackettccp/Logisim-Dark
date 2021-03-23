/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.gates;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import logisim_src.analyze.model.Expression;
import logisim_src.analyze.model.Expressions;
import logisim_src.data.Value;
import logisim_src.instance.InstancePainter;
import logisim_src.instance.InstanceState;
import logisim_src.util.GraphicsUtil;

class OddParityGate extends AbstractGate {
	public static OddParityGate FACTORY = new OddParityGate();

	private OddParityGate() {
		super("Odd Parity", Strings.getter("oddParityComponent"));
		setRectangularLabel("2k+1");
		setIconNames("parityOddGate.gif");
	}

	@Override
	public void paintIconShaped(InstancePainter painter) {
		paintIconRectangular(painter);
	}
	
	@Override
	public void paintIconRectangular(InstancePainter painter) {
		Graphics g = painter.getGraphics();
		g.setColor(new Color(190, 190, 190));
		g.drawRect(1, 2, 16, 16);
		Font old = g.getFont();
		g.setFont(old.deriveFont(9.0f));
		GraphicsUtil.drawCenteredText(g, "2k", 9,  6);
		GraphicsUtil.drawCenteredText(g, "+1", 9, 13);
		g.setFont(old);
	}

	@Override
	protected void paintShape(InstancePainter painter, int width, int height) {
		paintRectangular(painter, width, height);
	}

	@Override
	protected void paintDinShape(InstancePainter painter, int width, int height,
			int inputs) {
		paintRectangular(painter, width, height);
	}

	@Override
	protected Value computeOutput(Value[] inputs, int numInputs,
			InstanceState state) {
		return GateFunctions.computeOddParity(inputs, numInputs);
	}

	@Override
	protected Expression computeExpression(Expression[] inputs, int numInputs) {
		Expression ret = inputs[0];
		for (int i = 1; i < numInputs; i++) {
			ret = Expressions.xor(ret, inputs[i]);
		}
		return ret;
	}

	@Override
	protected Value getIdentity() { return Value.FALSE; }

}
