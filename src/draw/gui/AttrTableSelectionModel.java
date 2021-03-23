/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.gui;

import java.util.HashMap;
import java.util.Map;

import draw.actions.ModelChangeAttributeAction;
import draw.canvas.Canvas;
import draw.canvas.Selection;
import draw.canvas.SelectionEvent;
import draw.canvas.SelectionListener;
import draw.model.AttributeMapKey;
import draw.model.CanvasModel;
import draw.model.CanvasObject;
import logisim_src.data.Attribute;
import logisim_src.data.AttributeSet;
import logisim_src.gui.generic.AttrTableSetException;
import logisim_src.gui.generic.AttributeSetTableModel;

class AttrTableSelectionModel extends AttributeSetTableModel
		implements SelectionListener {
	private Canvas canvas;
	
	public AttrTableSelectionModel(Canvas canvas) {
		super(new SelectionAttributes(canvas.getSelection()));
		this.canvas = canvas;
		canvas.getSelection().addSelectionListener(this);
	}
	
	@Override
	public String getTitle() {
		Selection sel = canvas.getSelection();
		Class<? extends CanvasObject> commonClass = null;
		int commonCount = 0;
		CanvasObject firstObject = null;
		int totalCount = 0;
		for (CanvasObject obj : sel.getSelected()) {
			if (firstObject == null) {
				firstObject = obj;
				commonClass = obj.getClass();
				commonCount = 1;
			} else if (obj.getClass() == commonClass) {
				commonCount++;
			} else {
				commonClass = null;
			}
			totalCount++;
		}
		
		if (firstObject == null) {
			return null;
		} else if (commonClass == null) {
			return Strings.get("selectionVarious", "" + totalCount);
		} else if (commonCount == 1) {
			return Strings.get("selectionOne", firstObject.getDisplayName());
		} else {
			return Strings.get("selectionMultiple", firstObject.getDisplayName(),
					"" + commonCount);
		}
	}

	@Override
	public void setValueRequested(Attribute<Object> attr, Object value)
			throws AttrTableSetException {
		SelectionAttributes attrs = (SelectionAttributes) getAttributeSet();
		HashMap<AttributeMapKey, Object> oldVals;
		oldVals = new HashMap<AttributeMapKey, Object>();
		HashMap<AttributeMapKey, Object> newVals;
		newVals = new HashMap<AttributeMapKey, Object>();
		for (Map.Entry<AttributeSet, CanvasObject> ent : attrs.entries()) {
			AttributeMapKey key = new AttributeMapKey(attr, ent.getValue());
			oldVals.put(key, ent.getKey().getValue(attr));
			newVals.put(key, value);
		}
		CanvasModel model = canvas.getModel();
		canvas.doAction(new ModelChangeAttributeAction(model, oldVals, newVals));
	}

	//
	// SelectionListener method
	//
	public void selectionChanged(SelectionEvent e) {
		fireTitleChanged();
	}
}
