/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import draw.model.CanvasModel;
import draw.model.CanvasObject;

public class ModelAddAction extends ModelAction {
	private ArrayList<CanvasObject> added;
	private int addIndex;
	
	public ModelAddAction(CanvasModel model, CanvasObject added) {
		this(model, Collections.singleton(added));
	}

	public ModelAddAction(CanvasModel model, Collection<CanvasObject> added) {
		super(model);
		this.added = new ArrayList<CanvasObject>(added);
		this.addIndex = model.getObjectsFromBottom().size();
	}

	public ModelAddAction(CanvasModel model, Collection<CanvasObject> added,
			int index) {
		super(model);
		this.added = new ArrayList<CanvasObject>(added);
		this.addIndex = index; 
	}
	
	public int getDestinationIndex() {
		return addIndex;
	}
	
	@Override
	public Collection<CanvasObject> getObjects() {
		return Collections.unmodifiableList(added);
	}

	@Override
	public String getName() {
		return Strings.get("actionAdd", getShapesName(added));
	}
	
	@Override
	void doSub(CanvasModel model) {
		model.addObjects(addIndex, added);
	}
	
	@Override
	void undoSub(CanvasModel model) {
		model.removeObjects(added);
	}
}
