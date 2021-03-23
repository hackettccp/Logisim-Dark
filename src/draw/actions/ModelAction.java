/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.actions;

import java.util.Collection;
import java.util.Collections;

import draw.model.CanvasModel;
import draw.model.CanvasObject;
import draw.undo.Action;

public abstract class ModelAction extends Action {
	private CanvasModel model;
	
	public ModelAction(CanvasModel model) {
		this.model = model;
	}
	
	public Collection<CanvasObject> getObjects() {
		return Collections.emptySet();
	}

	@Override
	public abstract String getName();
	
	abstract void doSub(CanvasModel model);
	
	abstract void undoSub(CanvasModel model);

	@Override
	public final void doIt() {
		doSub(model);
	}

	@Override
	public final void undo() {
		undoSub(model);
	}
	
	public CanvasModel getModel() {
		return model;
	}
	
	static String getShapesName(Collection<CanvasObject> coll) {
		if (coll.size() != 1) {
			return Strings.get("shapeMultiple");
		} else {
			CanvasObject shape = coll.iterator().next();
			return shape.getDisplayName();
		}
	}
}
