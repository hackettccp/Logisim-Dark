/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.data;

import logisim_src.LogisimVersion;

public interface AttributeDefaultProvider {
	public boolean isAllDefaultValues(AttributeSet attrs, LogisimVersion ver);
	public Object getDefaultAttributeValue(Attribute<?> attr, LogisimVersion ver);
}
