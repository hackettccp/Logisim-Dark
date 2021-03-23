/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.analyze.gui;

import logisim_src.util.LocaleManager;
import logisim_src.util.StringGetter;

class Strings {
	private static LocaleManager source
		= new LocaleManager("resources/logisim", "analyze");

	public static String get(String key) {
		return source.get(key);
	}
	public static StringGetter getter(String key) {
		return source.getter(key);
	}
}
