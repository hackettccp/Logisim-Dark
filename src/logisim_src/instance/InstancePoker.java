/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.instance;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import logisim_src.data.Bounds;

public abstract class InstancePoker {
	public boolean init(InstanceState state, MouseEvent e) { return true; }
	public Bounds getBounds(InstancePainter painter) {
		return painter.getInstance().getBounds();
	}
	public void paint(InstancePainter painter) { }
	public void mousePressed(InstanceState state, MouseEvent e) { }
	public void mouseReleased(InstanceState state, MouseEvent e) { }
	public void mouseDragged(InstanceState state, MouseEvent e) { }
	public void keyPressed(InstanceState state, KeyEvent e) { }
	public void keyReleased(InstanceState state, KeyEvent e) { }
	public void keyTyped(InstanceState state, KeyEvent e) { }
	public void stopEditing(InstanceState state) { }
}
