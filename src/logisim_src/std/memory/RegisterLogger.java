/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.memory;

import logisim_src.data.BitWidth;
import logisim_src.data.Value;
import logisim_src.instance.InstanceLogger;
import logisim_src.instance.InstanceState;
import logisim_src.instance.StdAttr;

public class RegisterLogger extends InstanceLogger {
	@Override
	public String getLogName(InstanceState state, Object option) {
		String ret = state.getAttributeValue(StdAttr.LABEL);
		return ret != null && !ret.equals("") ? ret : null;
	}

	@Override
	public Value getLogValue(InstanceState state, Object option) {
		BitWidth dataWidth = state.getAttributeValue(StdAttr.WIDTH);
		if (dataWidth == null) dataWidth = BitWidth.create(0);
		RegisterData data = (RegisterData) state.getData();
		if (data == null) return Value.createKnown(dataWidth, 0);
		return Value.createKnown(dataWidth, data.value);
	}
}
