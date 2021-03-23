/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.tools.key;

import java.util.HashMap;
import java.util.Map;

import logisim_src.data.Attribute;

public class KeyConfigurationResult {
	private KeyConfigurationEvent event;
	private Map<Attribute<?>,Object> attrValueMap;
	
	public KeyConfigurationResult(KeyConfigurationEvent event, Attribute<?> attr,
			Object value) {
		this.event = event;
		Map<Attribute<?>,Object> singleMap = new HashMap<Attribute<?>,Object>(1);
		singleMap.put(attr, value); 
		this.attrValueMap = singleMap;
	}
	
	public KeyConfigurationResult(KeyConfigurationEvent event,
			Map<Attribute<?>,Object> values) {
		this.event = event;
		this.attrValueMap = values;
	}
	
	public KeyConfigurationEvent getEvent() {
		return event;
	}
	
	public Map<Attribute<?>,Object> getAttributeValues() {
		return attrValueMap;
	}
}
