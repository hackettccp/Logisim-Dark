/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.actions;

import java.util.Collection;
import java.util.Collections;

import draw.model.CanvasModel;
import draw.model.CanvasObject;
import draw.shapes.Text;

public class ModelEditTextAction extends ModelAction {
	private Text text;
	private String oldValue;
	private String newValue;
	
	public ModelEditTextAction(CanvasModel model, Text text, String newValue) {
		super(model);
		this.text = text;
		this.oldValue = text.getText();
		this.newValue = newValue;
	}
	
	@Override
	public Collection<CanvasObject> getObjects() {
		return Collections.singleton((CanvasObject) text);
	}

	@Override
	public String getName() {
		return Strings.get("actionEditText");
	}
	
	@Override
	void doSub(CanvasModel model) {
		model.setText(text, newValue);
	}
	
	@Override
	void undoSub(CanvasModel model) {
		model.setText(text, oldValue);
	}
}
