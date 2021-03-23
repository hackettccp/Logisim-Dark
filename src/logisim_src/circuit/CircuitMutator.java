/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.circuit;

import logisim_src.comp.Component;
import logisim_src.data.Attribute;

public interface CircuitMutator {
	public void clear(Circuit circuit);
	public void add(Circuit circuit, Component comp);
	public void remove(Circuit circuit, Component comp);
	public void replace(Circuit circuit, Component oldComponent, Component newComponent);
	public void replace(Circuit circuit, ReplacementMap replacements);
	public void set(Circuit circuit, Component comp, Attribute<?> attr, Object value);
	public void setForCircuit(Circuit circuit, Attribute<?> attr, Object value);
}
