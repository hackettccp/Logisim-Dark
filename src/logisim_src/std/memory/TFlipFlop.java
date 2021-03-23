/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.memory;

import logisim_src.data.Value;

public class TFlipFlop extends AbstractFlipFlop {
	public TFlipFlop() {
		super("T Flip-Flop", "tFlipFlop.gif",
				Strings.getter("tFlipFlopComponent"), 1, false);
	}

	@Override
	protected String getInputName(int index) {
		return "T";
	}

	@Override
	protected Value computeValue(Value[] inputs, Value curValue) {
		if (curValue == Value.UNKNOWN) curValue = Value.FALSE;
		if (inputs[0] == Value.TRUE) {
			return curValue.not();
		} else {
			return curValue;
		}
	}
}
