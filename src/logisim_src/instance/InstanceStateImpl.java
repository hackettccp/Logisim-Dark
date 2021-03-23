/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.instance;

import logisim_src.circuit.Circuit;
import logisim_src.circuit.CircuitState;
import logisim_src.comp.Component;
import logisim_src.comp.EndData;
import logisim_src.data.Attribute;
import logisim_src.data.AttributeSet;
import logisim_src.data.Location;
import logisim_src.data.Value;
import logisim_src.proj.Project;

class InstanceStateImpl implements InstanceState {
	private CircuitState circuitState;
	private Component component;
	
	public InstanceStateImpl(CircuitState circuitState, Component component) {
		this.circuitState = circuitState;
		this.component = component;
	}
	
	public void repurpose(CircuitState circuitState, Component component) {
		this.circuitState = circuitState;
		this.component = component;
	}
	
	CircuitState getCircuitState() {
		return circuitState;
	}
	
	public Project getProject() {
		return circuitState.getProject();
	}
	
	public Instance getInstance() {
		if (component instanceof InstanceComponent) {
			return ((InstanceComponent) component).getInstance();
		} else {
			return null;
		}
	}
	
	public InstanceFactory getFactory() {
		if (component instanceof InstanceComponent) {
			InstanceComponent comp = (InstanceComponent) component;
			return (InstanceFactory) comp.getFactory();
		} else {
			return null;
		}
	}
	
	public AttributeSet getAttributeSet() {
		return component.getAttributeSet();
	}
	
	public <E> E getAttributeValue(Attribute<E> attr) {
		return component.getAttributeSet().getValue(attr);
	}
	
	public Value getPort(int portIndex) {
		EndData data = component.getEnd(portIndex);
		return circuitState.getValue(data.getLocation());
	}
	
	public boolean isPortConnected(int index) {
		Circuit circ = circuitState.getCircuit();
		Location loc = component.getEnd(index).getLocation();
		return circ.isConnected(loc, component);
	}
	
	public void setPort(int portIndex, Value value, int delay) {
		EndData end = component.getEnd(portIndex);
		circuitState.setValue(end.getLocation(), value, component, delay);
	}
	
	public InstanceData getData() {
		return (InstanceData) circuitState.getData(component);
	}
	
	public void setData(InstanceData value) {
		circuitState.setData(component, value);
	}
	
	public void fireInvalidated() {
		if (component instanceof InstanceComponent) {
			((InstanceComponent) component).fireInvalidated();
		}
	}
	
	public boolean isCircuitRoot() {
		return !circuitState.isSubstate();
	}
	
	public long getTickCount() {
		return circuitState.getPropagator().getTickCount();
	}
}
