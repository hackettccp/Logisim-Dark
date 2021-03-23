/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.file;

import logisim_src.tools.Library;

interface LibraryLoader {
	public Library loadLibrary(String desc);
	public String getDescriptor(Library lib);
	public void showError(String description);
}
