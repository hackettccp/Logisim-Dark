/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.file;

public interface LibraryEventSource {
	public void addLibraryListener(LibraryListener listener);
	public void removeLibraryListener(LibraryListener listener);
}
