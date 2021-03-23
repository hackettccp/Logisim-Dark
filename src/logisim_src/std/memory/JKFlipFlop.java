/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.memory;

import logisim_src.data.Value;

public class JKFlipFlop extends AbstractFlipFlop {
	public JKFlipFlop() {
		super("J-K Flip-Flop", "jkFlipFlop.gif",
				Strings.getter("jkFlipFlopComponent"), 2, false);
	}

	@Override
	protected String getInputName(int index) {
		return index == 0 ? "J" : "K";
	}

	@Override
	protected Value computeValue(Value[] inputs, Value curValue) {
		if (inputs[0] == Value.FALSE) {
			if (inputs[1] == Value.FALSE) {
				return curValue;
			} else if (inputs[1] == Value.TRUE) {
				return Value.FALSE;
			}
		} else if (inputs[0] == Value.TRUE) {
			if (inputs[1] == Value.FALSE) {
				return Value.TRUE;
			} else if (inputs[1] == Value.TRUE) {
				return curValue.not();
			}
		}
		return Value.UNKNOWN;
	}
}
