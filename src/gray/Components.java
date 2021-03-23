/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package gray;

import java.util.Arrays;
import java.util.List;

import logisim_src.tools.AddTool;
import logisim_src.tools.Library;

/** The library of components that the user can access. */
public class Components extends Library {
	/** The list of all tools contained in this library. Technically,
	 * libraries contain tools, which is a slightly more general concept
	 * than components; practically speaking, though, you'll most often want
	 * to create AddTools for new components that can be added into the circuit.
	 */
	private List<AddTool> tools;
	
	/** Constructs an instance of this library. This constructor is how
	 * Logisim accesses first when it opens the JAR file: It looks for
	 * a no-arguments constructor method of the user-designated class.
	 */
	public Components() {
		tools = Arrays.asList(new AddTool[] {
				new AddTool(new GrayIncrementer()),
				new AddTool(new SimpleGrayCounter()),
				new AddTool(new GrayCounter()),
		});
	}
	
	/** Returns the name of the library that the user will see. */ 
	@Override
	public String getDisplayName() {
		return "Gray Tools";
	}
	
	/** Returns a list of all the tools available in this library. */
	@Override
	public List<AddTool> getTools() {
		return tools;
	}
}

