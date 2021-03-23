/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.prefs;

import logisim_src.util.LocaleManager;
import logisim_src.util.StringGetter;
import logisim_src.util.StringUtil;

class Strings {
	private static LocaleManager source
		= new LocaleManager("resources/logisim", "proj");

	public static String get(String key) {
		return source.get(key);
	}
	public static String get(String key, String arg) {
		return StringUtil.format(source.get(key), arg);
	}
	public static StringGetter getter(String key) {
		return source.getter(key);
	}
}
