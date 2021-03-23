/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import draw.model.CanvasModel;
import draw.model.CanvasObject;
import draw.util.ZOrder;

public class ModelRemoveAction extends ModelAction {
	private Map<CanvasObject, Integer> removed;

	public ModelRemoveAction(CanvasModel model, CanvasObject removed) {
		this(model, Collections.singleton(removed));
	}	
	
	public ModelRemoveAction(CanvasModel model, Collection<CanvasObject> removed) {
		super(model);
		this.removed = ZOrder.getZIndex(removed, model);
	}
	
	@Override
	public Collection<CanvasObject> getObjects() {
		return Collections.unmodifiableSet(removed.keySet());
	}

	@Override
	public String getName() {
		return Strings.get("actionRemove", getShapesName(removed.keySet()));
	}
	
	@Override
	void doSub(CanvasModel model) {
		model.removeObjects(removed.keySet());
	}
	
	@Override
	void undoSub(CanvasModel model) {
		model.addObjects(removed);
	}
}
