/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package hex;

public interface HexModelListener {
	public void metainfoChanged(HexModel source);
	public void bytesChanged(HexModel source, long start, long numBytes,
			int[] oldValues);
}
