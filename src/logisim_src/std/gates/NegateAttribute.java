/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.gates;

import logisim_src.data.Attribute;
import logisim_src.data.Attributes;
import logisim_src.data.Direction;
import logisim_src.util.StringUtil;

class NegateAttribute extends Attribute<Boolean> {
	private static Attribute<Boolean> BOOLEAN_ATTR = Attributes.forBoolean("negateDummy");
	
	int index;
	private Direction side;
	
	public NegateAttribute(int index, Direction side) {
		super("negate" + index, null);
		this.index = index;
		this.side = side;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof NegateAttribute) {
			NegateAttribute o = (NegateAttribute) other;
			return this.index == o.index && this.side == o.side;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return index * 31 + (side == null ? 0 : side.hashCode());
	}
	
	@Override
	public String getDisplayName() {
		String ret = StringUtil.format(Strings.get("gateNegateAttr"), "" + (index + 1));
		if (side != null) {
			ret += " (" + side.toVerticalDisplayString() + ")"; 
		}
		return ret;
	}

	@Override
	public String toDisplayString(Boolean value) {
		return BOOLEAN_ATTR.toDisplayString(value);
	}

	@Override
	public Boolean parse(String value) {
		return BOOLEAN_ATTR.parse(value);
	}
	
	@Override
	public java.awt.Component getCellEditor(Boolean value) {
		return BOOLEAN_ATTR.getCellEditor(null, value);
	}


}
