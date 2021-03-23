/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.circuit;

import java.util.Map;

import logisim_src.analyze.model.Expression;
import logisim_src.data.Location;

public interface ExpressionComputer {
	/**
	 * Propagates expression computation through a circuit.
	 * The parameter is a map from <code>Point</code>s to
	 * <code>Expression</code>s. The method will use this to
	 * determine the expressions coming into the component,
	 * and it should place any output expressions into
	 * the component.
	 * 
	 * If, in fact, no valid expression exists for the component,
	 * it throws <code>UnsupportedOperationException</code>.
	 */
	public void computeExpression(Map<Location,Expression> expressionMap);
}
