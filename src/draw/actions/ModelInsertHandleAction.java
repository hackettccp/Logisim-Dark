/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.actions;

import java.util.Collection;
import java.util.Collections;

import draw.model.CanvasModel;
import draw.model.CanvasObject;
import draw.model.Handle;

public class ModelInsertHandleAction extends ModelAction {
	private Handle desired;
	
	public ModelInsertHandleAction(CanvasModel model, Handle desired) {
		super(model);
		this.desired = desired;
	}

	@Override
	public Collection<CanvasObject> getObjects() {
		return Collections.singleton(desired.getObject());
	}

	@Override
	public String getName() {
		return Strings.get("actionInsertHandle");
	}
	
	@Override
	void doSub(CanvasModel model) {
		model.insertHandle(desired, null);
	}
	
	@Override
	void undoSub(CanvasModel model) {
		model.deleteHandle(desired);
	}
}
