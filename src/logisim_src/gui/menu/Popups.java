/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import logisim_src.circuit.Circuit;
import logisim_src.file.LoadedLibrary;
import logisim_src.file.Loader;
import logisim_src.file.LogisimFile;
import logisim_src.gui.main.Frame;
import logisim_src.gui.main.StatisticsDialog;
import logisim_src.proj.Project;
import logisim_src.tools.AddTool;
import logisim_src.tools.Library;
import logisim_src.tools.Tool;

public class Popups {
	private static class ProjectPopup extends JPopupMenu
			implements ActionListener {
		Project proj;
		JMenuItem add = new JMenuItem(Strings.get("projectAddCircuitItem"));
		JMenu load = new JMenu(Strings.get("projectLoadLibraryItem"));
		JMenuItem loadBuiltin = new JMenuItem(Strings.get("projectLoadBuiltinItem"));
		JMenuItem loadLogisim = new JMenuItem(Strings.get("projectLoadLogisimItem"));
		JMenuItem loadJar = new JMenuItem(Strings.get("projectLoadJarItem"));

		ProjectPopup(Project proj) {
			super(Strings.get("projMenu"));
			this.proj = proj;

			load.add(loadBuiltin); loadBuiltin.addActionListener(this);
			load.add(loadLogisim); loadLogisim.addActionListener(this);
			load.add(loadJar); loadJar.addActionListener(this);

			add(add); add.addActionListener(this);
			add(load);
		}

		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			if (src == add) {
				ProjectCircuitActions.doAddCircuit(proj);
			} else if (src == loadBuiltin) {
				ProjectLibraryActions.doLoadBuiltinLibrary(proj);
			} else if (src == loadLogisim) {
				ProjectLibraryActions.doLoadLogisimLibrary(proj);
			} else if (src == loadJar) {
				ProjectLibraryActions.doLoadJarLibrary(proj);
			}
		}
	}

	private static class LibraryPopup extends JPopupMenu
			implements ActionListener {
		Project proj;
		Library lib;
		JMenuItem unload = new JMenuItem(Strings.get("projectUnloadLibraryItem"));
		JMenuItem reload = new JMenuItem(Strings.get("projectReloadLibraryItem"));

		LibraryPopup(Project proj, Library lib, boolean is_top) {
			super(Strings.get("libMenu"));
			this.proj = proj;
			this.lib = lib;

			add(unload); unload.addActionListener(this);
			add(reload); reload.addActionListener(this);
			unload.setEnabled(is_top);
			reload.setEnabled(is_top && lib instanceof LoadedLibrary);
		}

		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			if (src == unload) {
				ProjectLibraryActions.doUnloadLibrary(proj, lib);
			} else if (src == reload) {
				Loader loader = proj.getLogisimFile().getLoader();
				loader.reload((LoadedLibrary) lib);
			}
		}
	}

	private static class CircuitPopup extends JPopupMenu
			implements ActionListener {
		Project proj;
		Tool tool;
		Circuit circuit;
		JMenuItem analyze = new JMenuItem(Strings.get("projectAnalyzeCircuitItem"));
		JMenuItem stats = new JMenuItem(Strings.get("projectGetCircuitStatisticsItem"));
		JMenuItem main = new JMenuItem(Strings.get("projectSetAsMainItem"));
		JMenuItem remove = new JMenuItem(Strings.get("projectRemoveCircuitItem"));
		JMenuItem editLayout = new JMenuItem(Strings.get("projectEditCircuitLayoutItem"));
		JMenuItem editAppearance = new JMenuItem(Strings.get("projectEditCircuitAppearanceItem"));

		CircuitPopup(Project proj, Tool tool, Circuit circuit) {
			super(Strings.get("circuitMenu"));
			this.proj = proj;
			this.tool = tool;
			this.circuit = circuit;

			add(editLayout); editLayout.addActionListener(this);
			add(editAppearance); editAppearance.addActionListener(this);
			add(analyze); analyze.addActionListener(this);
			add(stats); stats.addActionListener(this);
			addSeparator();
			add(main); main.addActionListener(this);
			add(remove); remove.addActionListener(this);
			
			boolean canChange = proj.getLogisimFile().contains(circuit);
			LogisimFile file = proj.getLogisimFile();
			if (circuit == proj.getCurrentCircuit()) {
				if (proj.getFrame().getEditorView().equals(Frame.EDIT_APPEARANCE)) {
					editAppearance.setEnabled(false);
				} else {
					editLayout.setEnabled(false);
				}
			}
			main.setEnabled(canChange && file.getMainCircuit() != circuit);
			remove.setEnabled(canChange && file.getCircuitCount() > 1
					&& proj.getDependencies().canRemove(circuit));
		}

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == editLayout) {
				proj.setCurrentCircuit(circuit);
				proj.getFrame().setEditorView(Frame.EDIT_LAYOUT);
			} else if (source == editAppearance) {
				proj.setCurrentCircuit(circuit);
				proj.getFrame().setEditorView(Frame.EDIT_APPEARANCE);
			} else if (source == analyze) {
				ProjectCircuitActions.doAnalyze(proj, circuit);
			} else if (source == stats) {
				JFrame frame = (JFrame) SwingUtilities.getRoot(this);
				StatisticsDialog.show(frame, proj.getLogisimFile(), circuit);
			} else if (source == main) {
				ProjectCircuitActions.doSetAsMainCircuit(proj, circuit);
			} else if (source == remove) {
				ProjectCircuitActions.doRemoveCircuit(proj, circuit);
			}
		}
	}
	
	public static JPopupMenu forCircuit(Project proj, AddTool tool, Circuit circ) {
		return new CircuitPopup(proj, tool, circ);
	}
	
	public static JPopupMenu forTool(Project proj, Tool tool) {
		return null;
	}
	
	public static JPopupMenu forProject(Project proj) {
		return new ProjectPopup(proj);
	}
	
	public static JPopupMenu forLibrary(Project proj, Library lib, boolean isTop) {
		return new LibraryPopup(proj, lib, isTop);
	}

}
