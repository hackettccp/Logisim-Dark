/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools;

import java.util.ArrayList;
import java.util.List;

import logisim_src.comp.ComponentFactory;
import logisim_src.data.Attribute;
import logisim_src.data.AttributeEvent;
import logisim_src.data.AttributeListener;
import logisim_src.data.AttributeSet;
import logisim_src.data.AttributeSets;

class FactoryAttributes implements AttributeSet, AttributeListener, Cloneable {
	private Class<? extends Library> descBase;
	private FactoryDescription desc;
	private ComponentFactory factory;
	private AttributeSet baseAttrs;
	private ArrayList<AttributeListener> listeners;
	
	public FactoryAttributes(Class<? extends Library> descBase,
			FactoryDescription desc) {
		this.descBase = descBase;
		this.desc = desc;
		this.factory = null;
		this.baseAttrs = null;
		this.listeners = new ArrayList<AttributeListener>();
	}
	
	public FactoryAttributes(ComponentFactory factory) {
		this.descBase = null;
		this.desc = null;
		this.factory = factory;
		this.baseAttrs = null;
		this.listeners = new ArrayList<AttributeListener>();
	}
	
	boolean isFactoryInstantiated() {
		return baseAttrs != null;
	}
	
	AttributeSet getBase() {
		AttributeSet ret = baseAttrs;
		if (ret == null) {
			ComponentFactory fact = factory;
			if (fact == null) {
				fact = desc.getFactory(descBase);
				factory = fact;
			}
			if (fact == null) {
				ret = AttributeSets.EMPTY;
			} else {
				ret = fact.createAttributeSet();
				ret.addAttributeListener(this);
			}
			baseAttrs = ret;
		}
		return ret;
	}

	public void addAttributeListener(AttributeListener l) {
		listeners.add(l);
	}

	public void removeAttributeListener(AttributeListener l) {
		listeners.remove(l);
	}
	
	@Override
	public AttributeSet clone() {
		return (AttributeSet) getBase().clone();
	}

	public boolean containsAttribute(Attribute<?> attr) {
		return getBase().containsAttribute(attr);
	}

	public Attribute<?> getAttribute(String name) {
		return getBase().getAttribute(name);
	}

	public List<Attribute<?>> getAttributes() {
		return getBase().getAttributes();
	}

	public <V> V getValue(Attribute<V> attr) {
		return getBase().getValue(attr);
	}

	public boolean isReadOnly(Attribute<?> attr) {
		return getBase().isReadOnly(attr);
	}
	
	public boolean isToSave(Attribute<?> attr) {
		return getBase().isToSave(attr);
	}

	public void setReadOnly(Attribute<?> attr, boolean value) {
		getBase().setReadOnly(attr, value);
	}

	public <V> void setValue(Attribute<V> attr, V value) {
		getBase().setValue(attr, value);
	}

	public void attributeListChanged(AttributeEvent baseEvent) {
		AttributeEvent e = null;
		for (AttributeListener l : listeners) {
			if (e == null) {
				e = new AttributeEvent(this, baseEvent.getAttribute(),
						baseEvent.getValue());
			}
			l.attributeListChanged(e);
		}
	}

	public void attributeValueChanged(AttributeEvent baseEvent) {
		AttributeEvent e = null;
		for (AttributeListener l : listeners) {
			if (e == null) {
				e = new AttributeEvent(this, baseEvent.getAttribute(),
						baseEvent.getValue());
			}
			l.attributeValueChanged(e);
		}
	}
}
