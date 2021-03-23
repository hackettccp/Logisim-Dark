/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.main;

import logisim_src.circuit.Circuit;
import logisim_src.circuit.CircuitMutation;
import logisim_src.data.Attribute;
import logisim_src.gui.generic.AttrTableSetException;
import logisim_src.gui.generic.AttributeSetTableModel;
import logisim_src.proj.Project;

public class AttrTableCircuitModel extends AttributeSetTableModel {
	private Project proj;
	private Circuit circ;
	
	public AttrTableCircuitModel(Project proj, Circuit circ) {
		super(circ.getStaticAttributes());
		this.proj = proj;
		this.circ = circ;
	}

	@Override
	public String getTitle() {
		return Strings.get("circuitAttrTitle", circ.getName());
	}
	
	@Override
	public void setValueRequested(Attribute<Object> attr, Object value)
			throws AttrTableSetException {
		if (!proj.getLogisimFile().contains(circ)) {
			String msg = Strings.get("cannotModifyCircuitError");
			throw new AttrTableSetException(msg);
		} else {
			CircuitMutation xn = new CircuitMutation(circ);
			xn.setForCircuit(attr, value);
			proj.doAction(xn.toAction(Strings.getter("changeCircuitAttrAction")));
		}
	}
}

