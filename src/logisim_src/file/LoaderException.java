/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.file;

public class LoaderException extends RuntimeException {
	private boolean shown;
	
	LoaderException(String desc) {
		this(desc, false);
	}
	
	LoaderException(String desc, boolean shown) {
		super(desc);
		this.shown = shown;
	}
	
	public boolean isShown() {
		return shown;
	}
}