/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.base;

import java.util.Arrays;
import java.util.List;

import logisim_src.tools.Library;
import logisim_src.tools.MenuTool;
import logisim_src.tools.PokeTool;
import logisim_src.tools.SelectTool;
import logisim_src.tools.TextTool;
import logisim_src.tools.AddTool;
import logisim_src.tools.EditTool;
import logisim_src.tools.Tool;
import logisim_src.tools.WiringTool;

public class Base extends Library {
	private List<Tool> tools = null;

	public Base() {
		SelectTool select = new SelectTool();
		WiringTool wiring = new WiringTool();
		
		tools = Arrays.asList(new Tool[] {
			new PokeTool(),
			new EditTool(select, wiring),
			select,
			wiring,
			new TextTool(),
			new MenuTool(),
			new AddTool(Text.FACTORY),
		});
	}

	@Override
	public String getName() { return "Base"; }

	@Override
	public String getDisplayName() { return Strings.get("baseLibrary"); }

	@Override
	public List<Tool> getTools() {
		return tools;
	}
}
