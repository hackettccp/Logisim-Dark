/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.file;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import draw.model.AbstractCanvasObject;
import logisim_src.circuit.Circuit;
import logisim_src.circuit.CircuitMutator;
import logisim_src.circuit.CircuitTransaction;
import logisim_src.circuit.Wire;
import logisim_src.comp.Component;
import logisim_src.comp.ComponentFactory;
import logisim_src.data.AttributeSet;
import logisim_src.data.Location;
import logisim_src.tools.AddTool;
import logisim_src.tools.Library;
import logisim_src.tools.Tool;

public class XmlCircuitReader extends CircuitTransaction {
	private XmlReader.ReadContext reader;
	private List<XmlReader.CircuitData> circuitsData;
	
	public XmlCircuitReader(XmlReader.ReadContext reader,
			List<XmlReader.CircuitData> circDatas) {
		this.reader = reader;
		this.circuitsData = circDatas;
	}

	@Override
	protected Map<Circuit, Integer> getAccessedCircuits() {
		HashMap<Circuit, Integer> access = new HashMap<Circuit,Integer>();
		for (XmlReader.CircuitData data : circuitsData) {
			access.put(data.circuit, READ_WRITE);
		}
		return access;
	}

	@Override
	protected void run(CircuitMutator mutator) {
		for (XmlReader.CircuitData circuitData : circuitsData) {
			buildCircuit(circuitData, mutator);
		}
	}

	private void buildCircuit(XmlReader.CircuitData circData, CircuitMutator mutator) {
		Element elt = circData.circuitElement;
		Circuit dest = circData.circuit;
		Map<Element, Component> knownComponents = circData.knownComponents;
		if (knownComponents == null) knownComponents = Collections.emptyMap();
		try {
			reader.initAttributeSet(circData.circuitElement, dest.getStaticAttributes(), null);
		} catch (XmlReaderException e) {
			reader.addErrors(e, circData.circuit.getName() + ".static");
		}

		for (Element sub_elt : XmlIterator.forChildElements(elt)) {
			String sub_elt_name = sub_elt.getTagName();
			if (sub_elt_name.equals("comp")) {
				try {
					Component comp = knownComponents.get(sub_elt);
					if (comp == null) {
						comp = getComponent(sub_elt, reader);
					}
					mutator.add(dest, comp);
				} catch (XmlReaderException e) {
					reader.addErrors(e, circData.circuit.getName() + "." + toComponentString(sub_elt));
				}
			} else if (sub_elt_name.equals("wire")) {
				try {
					addWire(dest, mutator, sub_elt);
				} catch (XmlReaderException e) {
					reader.addErrors(e, circData.circuit.getName() + "." + toWireString(sub_elt));
				}
			}
		}
		
		List<AbstractCanvasObject> appearance = circData.appearance;
		if (appearance != null && !appearance.isEmpty()) {
			dest.getAppearance().setObjectsForce(appearance);
			dest.getAppearance().setDefaultAppearance(false);
		}
	}
	
	private String toComponentString(Element elt) {
		String name = elt.getAttribute("name");
		String loc = elt.getAttribute("loc");
		return name + "(" + loc + ")";
	}
	
	private String toWireString(Element elt) {
		String from = elt.getAttribute("from");
		String to = elt.getAttribute("to");
		return "w" + from + "-" + to;
	}

	void addWire(Circuit dest, CircuitMutator mutator, Element elt)
			throws XmlReaderException {
		Location pt0;
		try {
			String str = elt.getAttribute("from");
			if (str == null || str.equals("")) {
				throw new XmlReaderException(Strings.get("wireStartMissingError"));
			}
			pt0 = Location.parse(str);
		} catch (NumberFormatException e) {
			throw new XmlReaderException(Strings.get("wireStartInvalidError"));
		}

		Location pt1;
		try {
			String str = elt.getAttribute("to");
			if (str == null || str.equals("")) {
				throw new XmlReaderException(Strings.get("wireEndMissingError"));
			}
			pt1 = Location.parse(str);
		} catch (NumberFormatException e) {
			throw new XmlReaderException(Strings.get("wireEndInvalidError"));
		}

		mutator.add(dest, Wire.create(pt0, pt1));
	}
	
	static Component getComponent(Element elt, XmlReader.ReadContext reader)
			throws XmlReaderException {
		// Determine the factory that creates this element
		String name = elt.getAttribute("name");
		if (name == null || name.equals("")) {
			throw new XmlReaderException(Strings.get("compNameMissingError"));
		}

		String libName = elt.getAttribute("lib");
		Library lib = reader.findLibrary(libName);
		if (lib == null) {
			throw new XmlReaderException(Strings.get("compUnknownError", "no-lib"));
		}

		Tool tool = lib.getTool(name);
		if (tool == null || !(tool instanceof AddTool)) {
			if (libName == null || libName.equals("")) {
				throw new XmlReaderException(Strings.get("compUnknownError", name));
			} else {
				throw new XmlReaderException(Strings.get("compAbsentError", name, libName));
			}
		}
		ComponentFactory source = ((AddTool) tool).getFactory();

		// Determine attributes
		String loc_str = elt.getAttribute("loc");
		AttributeSet attrs = source.createAttributeSet();
		reader.initAttributeSet(elt, attrs, source);

		// Create component if location known
		if (loc_str == null || loc_str.equals("")) {
			throw new XmlReaderException(Strings.get("compLocMissingError", source.getName()));
		} else {
			try {
				Location loc = Location.parse(loc_str);
				return source.createComponent(loc, attrs);
			} catch (NumberFormatException e) {
				throw new XmlReaderException(Strings.get("compLocInvalidError",
					source.getName(), loc_str));
			}
		}
	}
}
