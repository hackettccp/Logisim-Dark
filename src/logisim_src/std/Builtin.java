/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import logisim_src.std.arith.Arithmetic;
import logisim_src.std.base.Base;
import logisim_src.std.gates.Gates;
import logisim_src.std.io.Io;
import logisim_src.std.memory.Memory;
import logisim_src.std.plexers.Plexers;
import logisim_src.std.wiring.Wiring;
import logisim_src.tools.Library;
import logisim_src.tools.Tool;

public class Builtin extends Library {
	private List<Library> libraries = null;

	public Builtin() {
		libraries = Arrays.asList(new Library[] {
			new Base(),
			new Gates(),
			new Wiring(),
			new Plexers(),
			new Arithmetic(),
			new Memory(),
			new Io(),
		});
	}

	@Override
	public String getName() { return "Builtin"; }

	@Override
	public String getDisplayName() { return Strings.get("builtinLibrary"); }

	@Override
	public List<Tool> getTools() { return Collections.emptyList(); }
	
	@Override
	public List<Library> getLibraries() {
		return libraries;
	}
}
