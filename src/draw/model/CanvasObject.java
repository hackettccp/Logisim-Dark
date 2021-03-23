/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.model;

import java.awt.Graphics;
import java.util.List;

import logisim_src.data.Attribute;
import logisim_src.data.AttributeSet;
import logisim_src.data.Bounds;
import logisim_src.data.Location;

public interface CanvasObject {
	public abstract CanvasObject clone();
	public abstract String getDisplayName();
	public abstract AttributeSet getAttributeSet();
	public abstract <V> V getValue(Attribute<V> attr);
	public abstract Bounds getBounds();
	public abstract boolean matches(CanvasObject other);
	public abstract int matchesHashCode();
	public abstract boolean contains(Location loc, boolean assumeFilled);
	public abstract boolean overlaps(CanvasObject other);
	public abstract List<Handle> getHandles(HandleGesture gesture);
	public abstract boolean canRemove();
	public abstract boolean canMoveHandle(Handle handle);
	public abstract Handle canInsertHandle(Location desired);
	public abstract Handle canDeleteHandle(Location desired);
	public abstract void paint(Graphics g, HandleGesture gesture);
	
	public Handle moveHandle(HandleGesture gesture);
	public void insertHandle(Handle desired, Handle previous);
	public Handle deleteHandle(Handle handle);
	public void translate(int dx, int dy);
	public <V> void setValue(Attribute<V> attr, V value);
}
