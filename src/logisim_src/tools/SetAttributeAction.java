/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools;

import java.util.ArrayList;
import java.util.List;

import logisim_src.circuit.Circuit;
import logisim_src.circuit.CircuitMutation;
import logisim_src.circuit.CircuitTransaction;
import logisim_src.circuit.CircuitTransactionResult;
import logisim_src.comp.Component;
import logisim_src.data.Attribute;
import logisim_src.data.AttributeSet;
import logisim_src.proj.Action;
import logisim_src.proj.Project;
import logisim_src.util.StringGetter;

public class SetAttributeAction extends Action {
	private StringGetter nameGetter;
	private Circuit circuit;
	private List<Component> comps;
	private List<Attribute<Object>> attrs;
	private List<Object> values;
	private List<Object> oldValues;
	private CircuitTransaction xnReverse;
	
	public SetAttributeAction(Circuit circuit, StringGetter nameGetter) {
		this.nameGetter = nameGetter;
		this.circuit = circuit;
		this.comps = new ArrayList<Component>();
		this.attrs = new ArrayList<Attribute<Object>>();
		this.values = new ArrayList<Object>();
		this.oldValues = new ArrayList<Object>();
	}
	
	public void set(Component comp, Attribute<?> attr, Object value) {
		@SuppressWarnings("unchecked")
		Attribute<Object> a = (Attribute<Object>) attr;
		comps.add(comp);
		attrs.add(a);
		values.add(value);
	}
	
	public boolean isEmpty() {
		return comps.isEmpty();
	}
	
	@Override
	public String getName() {
		return nameGetter.get();
	}
	
	@Override
	public void doIt(Project proj) {
		CircuitMutation xn = new CircuitMutation(circuit);
		int len = values.size();
		oldValues.clear();
		for (int i = 0; i < len; i++) {
			Component comp = comps.get(i);
			Attribute<Object> attr = attrs.get(i);
			Object value = values.get(i);
			if (circuit.contains(comp)) {
				oldValues.add(null);
				xn.set(comp, attr, value);
			} else {
				AttributeSet compAttrs = comp.getAttributeSet();
				oldValues.add(compAttrs.getValue(attr));
				compAttrs.setValue(attr, value);    
			}
		}
		
		if (!xn.isEmpty()) {
			CircuitTransactionResult result = xn.execute();
			xnReverse = result.getReverseTransaction();
		}
	}

	@Override
	public void undo(Project proj) {
		if (xnReverse != null) xnReverse.execute();
		for (int i = oldValues.size() - 1; i >= 0; i--) {
			Component comp = comps.get(i);
			Attribute<Object> attr = attrs.get(i);
			Object value = oldValues.get(i);
			if (value != null) {
				comp.getAttributeSet().setValue(attr, value);
			}
		}
	}
}
