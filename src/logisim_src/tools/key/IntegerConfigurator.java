/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools.key;

import logisim_src.data.Attribute;

public class IntegerConfigurator extends NumericConfigurator<Integer> {
	public IntegerConfigurator(Attribute<Integer> attr, int min, int max, int modifiersEx) {
		super(attr, min, max, modifiersEx);
	}
	
	public IntegerConfigurator(Attribute<Integer> attr, int min, int max,
			int modifiersEx, int radix) {
		super(attr, min, max, modifiersEx, radix);
	}
	
	@Override
	protected Integer createValue(int val) {
		return Integer.valueOf(val);
	}
}
