/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.memory;

import logisim_src.instance.InstanceData;

class RegisterData extends ClockState implements InstanceData {
	int value;

	public RegisterData() {
		value = 0;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}