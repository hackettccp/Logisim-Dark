/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.appear;

import draw.canvas.Canvas;
import draw.gui.AttrTableDrawManager;
import draw.toolbar.ToolbarModel;
import draw.tools.DrawingAttributeSet;
import draw.tools.SelectTool;
import logisim_src.circuit.CircuitState;
import logisim_src.data.AttributeSet;
import logisim_src.gui.generic.AttrTable;
import logisim_src.gui.generic.BasicZoomModel;
import logisim_src.gui.generic.CanvasPane;
import logisim_src.gui.generic.ZoomModel;
import logisim_src.gui.main.EditHandler;
import logisim_src.prefs.AppPreferences;
import logisim_src.proj.Project;

import java.awt.*;

public class AppearanceView {
	private static final double[] ZOOM_OPTIONS = { 100, 150, 200, 300, 400, 600, 800 };

	private DrawingAttributeSet attrs;
	private AppearanceCanvas canvas;
	private CanvasPane canvasPane;
	private AppearanceToolbarModel toolbarModel;
	private AttrTableDrawManager attrTableManager;
	private ZoomModel zoomModel;
	private AppearanceEditHandler editHandler;
	
	public AppearanceView() {
		attrs = new DrawingAttributeSet();
		SelectTool selectTool = new SelectTool();
		canvas = new AppearanceCanvas(selectTool);
		canvas.setBackground(Color.BLACK);
		toolbarModel = new AppearanceToolbarModel(selectTool, canvas, attrs);
		zoomModel = new BasicZoomModel(AppPreferences.APPEARANCE_SHOW_GRID,
				AppPreferences.APPEARANCE_ZOOM, ZOOM_OPTIONS);
		canvas.getGridPainter().setZoomModel(zoomModel);
		attrTableManager = null;
		canvasPane = new CanvasPane(canvas);
		canvasPane.setZoomModel(zoomModel);

		editHandler = new AppearanceEditHandler(canvas);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public CanvasPane getCanvasPane() {
		return canvasPane;
	}
	
	public ToolbarModel getToolbarModel() {
		return toolbarModel;
	}
	
	public ZoomModel getZoomModel() {
		return zoomModel;
	}
	
	public EditHandler getEditHandler() {
		return editHandler;
	}
	
	public AttributeSet getAttributeSet() {
		return attrs;
	}
	
	public AttrTableDrawManager getAttrTableDrawManager(AttrTable table) {
		AttrTableDrawManager ret = attrTableManager;
		if (ret == null) {
			ret = new AttrTableDrawManager(canvas, table, attrs);
			attrTableManager = ret;
		}
		return ret;
	}
	
	public void setCircuit(Project proj, CircuitState circuitState) {
		canvas.setCircuit(proj, circuitState);
	}
}
