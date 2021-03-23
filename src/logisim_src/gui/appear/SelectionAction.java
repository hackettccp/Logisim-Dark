/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.appear;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import draw.canvas.Selection;
import draw.model.CanvasModel;
import draw.model.CanvasObject;
import draw.util.ZOrder;
import logisim_src.circuit.appear.AppearanceAnchor;
import logisim_src.data.Direction;
import logisim_src.data.Location;
import logisim_src.proj.Action;
import logisim_src.proj.Project;
import logisim_src.util.StringGetter;

class SelectionAction extends Action {
	private StringGetter displayName;
	private AppearanceCanvas canvas;
	private CanvasModel canvasModel;
	private Map<CanvasObject, Integer> toRemove;
	private Collection<CanvasObject> toAdd;
	private Collection<CanvasObject> oldSelection;
	private Collection<CanvasObject> newSelection;
	private Location anchorNewLocation;
	private Direction anchorNewFacing;
	private Location anchorOldLocation;
	private Direction anchorOldFacing;
	
	public SelectionAction(AppearanceCanvas canvas, StringGetter displayName,
			Collection<CanvasObject> toRemove, Collection<CanvasObject> toAdd,
			Collection<CanvasObject> newSelection, Location anchorLocation,
			Direction anchorFacing) {
		this.canvas = canvas;
		this.canvasModel = canvas.getModel();
		this.displayName = displayName;
		this.toRemove = toRemove == null ? null : ZOrder.getZIndex(toRemove, canvasModel);
		this.toAdd = toAdd;
		this.oldSelection = new ArrayList<CanvasObject>(canvas.getSelection().getSelected());
		this.newSelection = newSelection;
		this.anchorNewLocation = anchorLocation;
		this.anchorNewFacing = anchorFacing;
	}

	@Override
	public String getName() {
		return displayName.get();
	}
	
	@Override
	public void doIt(Project proj) {
		Selection sel = canvas.getSelection();
		sel.clearSelected();
		if (toRemove != null) canvasModel.removeObjects(toRemove.keySet());
		int dest = AppearanceCanvas.getMaxIndex(canvasModel) + 1;
		if (toAdd != null) canvasModel.addObjects(dest, toAdd);

		AppearanceAnchor anchor = findAnchor(canvasModel);
		if (anchor != null && anchorNewLocation != null) {
			anchorOldLocation = anchor.getLocation();
			anchor.translate(anchorNewLocation.getX() - anchorOldLocation.getX(),
					anchorNewLocation.getY() - anchorOldLocation.getY());
		}
		if (anchor != null && anchorNewFacing != null) {
			anchorOldFacing = anchor.getFacing();
			anchor.setValue(AppearanceAnchor.FACING, anchorNewFacing);
		}
		sel.setSelected(newSelection, true);
		canvas.repaint();
	}
	
	private AppearanceAnchor findAnchor(CanvasModel canvasModel) {
		for (Object o : canvasModel.getObjectsFromTop()) {
			if (o instanceof AppearanceAnchor) {
				return (AppearanceAnchor) o;
			}
		}
		return null;
	}
	
	@Override
	public void undo(Project proj) {
		AppearanceAnchor anchor = findAnchor(canvasModel);
		if (anchor != null && anchorOldLocation != null) {
			anchor.translate(anchorOldLocation.getX() - anchorNewLocation.getX(),
					anchorOldLocation.getY() - anchorNewLocation.getY());
		}
		if (anchor != null && anchorOldFacing != null) {
			anchor.setValue(AppearanceAnchor.FACING, anchorOldFacing);
		}
		Selection sel = canvas.getSelection();
		sel.clearSelected();
		if (toAdd != null) canvasModel.removeObjects(toAdd);
		if (toRemove != null) canvasModel.addObjects(toRemove);
		sel.setSelected(oldSelection, true);
		canvas.repaint();
	}
}
