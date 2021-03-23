/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.analyze.model;

import logisim_src.util.StringGetter;

public  class ParserException extends Exception {
	private StringGetter message;
	private int start;
	private int length;
	
	public ParserException(StringGetter message, int start, int length) {
		super(message.get());
		this.message = message;
		this.start = start;
		this.length = length;
	}
	
	@Override
	public String getMessage() {
		return message.get();
	}
	
	public StringGetter getMessageGetter() {
		return message;
	}
	
	public int getOffset() {
		return start;
	}
	
	public int getEndOffset() {
		return start + length;
	}
}