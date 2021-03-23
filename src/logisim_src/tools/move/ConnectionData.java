/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools.move;

import java.util.List;

import logisim_src.circuit.Wire;
import logisim_src.data.Direction;
import logisim_src.data.Location;

class ConnectionData {
	private Location loc;
	
	private Direction dir;
	
	/** The list of wires leading up to this point - we may well want to
	 * truncate this path somewhat. */
	private List<Wire> wirePath;
	
	private Location wirePathStart;
	
	public ConnectionData(Location loc, Direction dir, List<Wire> wirePath,
			Location wirePathStart) {
		this.loc = loc;
		this.dir = dir;
		this.wirePath = wirePath;
		this.wirePathStart = wirePathStart;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public Direction getDirection() {
		return dir;
	}
	
	public List<Wire> getWirePath() {
		return wirePath;
	}
	
	public Location getWirePathStart() {
		return wirePathStart;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof ConnectionData) {
			ConnectionData o = (ConnectionData) other;
			return this.loc.equals(o.loc) && this.dir.equals(o.dir);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return loc.hashCode() * 31 + (dir == null ? 0 : dir.hashCode());
	}
}
