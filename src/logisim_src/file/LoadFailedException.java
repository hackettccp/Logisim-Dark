/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.file;

public class LoadFailedException extends Exception {
	private boolean shown;
	
	LoadFailedException(String desc) {
		this(desc, false);
	}
	
	LoadFailedException(String desc, boolean shown) {
		super(desc);
		this.shown = shown;
	}
	
	public boolean isShown() {
		return shown;
	}
}