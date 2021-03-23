/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.generic;

import java.beans.PropertyChangeListener;

public interface ZoomModel {
	public static final String ZOOM = "zoom";
	public static final String SHOW_GRID = "grid";
	
	public void addPropertyChangeListener(String prop, PropertyChangeListener l);
	public void removePropertyChangeListener(String prop, PropertyChangeListener l);
	public boolean getShowGrid();
	public double getZoomFactor();
	public double[] getZoomOptions();
	public void setShowGrid(boolean value);
	public void setZoomFactor(double value);
}
