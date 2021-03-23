/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.actions;

import java.util.Collection;
import java.util.Collections;

import draw.model.CanvasModel;
import draw.model.CanvasObject;
import draw.model.Handle;

public class ModelDeleteHandleAction extends ModelAction {
	private Handle handle;
	private Handle previous;
	
	public ModelDeleteHandleAction(CanvasModel model, Handle handle) {
		super(model);
		this.handle = handle;
	}

	@Override
	public Collection<CanvasObject> getObjects() {
		return Collections.singleton(handle.getObject());
	}

	@Override
	public String getName() {
		return Strings.get("actionDeleteHandle");
	}
	
	@Override
	void doSub(CanvasModel model) {
		previous = model.deleteHandle(handle);
	}
	
	@Override
	void undoSub(CanvasModel model) {
		model.insertHandle(handle, previous);
	}
}
