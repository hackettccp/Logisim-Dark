/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.comp;

public class ComponentEvent {
	private Component source;
	private Object oldData;
	private Object newData;

	public ComponentEvent(Component source) {
		this(source, null, null);
	}
	
	public ComponentEvent(Component source, Object oldData, Object newData) {
		this.source = source;
		this.oldData = oldData;
		this.newData = newData;
	}

	public Component getSource() {
		return source;
	}

	public Object getData() {
		return newData;
	}

	public Object getOldData() {
		return oldData;
	}
}
