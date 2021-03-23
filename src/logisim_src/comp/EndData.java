/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.comp;

import logisim_src.data.BitWidth;
import logisim_src.data.Location;

public class EndData {
	public static final int INPUT_ONLY = 1;
	public static final int OUTPUT_ONLY = 2;
	public static final int INPUT_OUTPUT = 3;

	private Location loc;
	private BitWidth width;
	private int i_o;
	private boolean exclusive;

	public EndData(Location loc, BitWidth width, int type, boolean exclusive) {
		this.loc = loc;
		this.width = width;
		this.i_o = type;
		this.exclusive = exclusive;
	}
	
	public EndData(Location loc, BitWidth width, int type) {
		this(loc, width, type, type == OUTPUT_ONLY);
	}

	public boolean isExclusive() { return exclusive; }
	public boolean isInput() { return (i_o & INPUT_ONLY) != 0; }
	public boolean isOutput() { return (i_o & OUTPUT_ONLY) != 0; }
	public Location getLocation() { return loc; }
	public BitWidth getWidth() { return width; }
	public int getType() { return i_o; }
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof EndData)) return false;
		if (other == this) return true;
		EndData o = (EndData) other;
		return o.loc.equals(this.loc) && o.width.equals(this.width)
			&& o.i_o == this.i_o && o.exclusive == this.exclusive;
	}
}
