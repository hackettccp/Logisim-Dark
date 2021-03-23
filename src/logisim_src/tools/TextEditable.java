/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools;

import logisim_src.circuit.Circuit;
import logisim_src.comp.ComponentUserEvent;
import logisim_src.proj.Action;

public interface TextEditable {
	public Caret getTextCaret(ComponentUserEvent event);
	public Action getCommitAction(Circuit circuit, String oldText, String newText);
}
