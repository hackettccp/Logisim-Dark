/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.main;

import javax.swing.JPopupMenu;

import logisim_src.circuit.Circuit;
import logisim_src.circuit.SubcircuitFactory;
import logisim_src.comp.ComponentFactory;
import logisim_src.data.AttributeEvent;
import logisim_src.data.AttributeListener;
import logisim_src.data.AttributeSet;
import logisim_src.file.LibraryEvent;
import logisim_src.file.LibraryEventSource;
import logisim_src.file.LibraryListener;
import logisim_src.file.LogisimFile;
import logisim_src.file.LogisimFileActions;
import logisim_src.gui.generic.AttrTableModel;
import logisim_src.gui.menu.ProjectCircuitActions;
import logisim_src.gui.menu.ProjectLibraryActions;
import logisim_src.gui.menu.Popups;
import logisim_src.proj.Project;
import logisim_src.proj.ProjectEvent;
import logisim_src.proj.ProjectListener;
import logisim_src.tools.AddTool;
import logisim_src.tools.Library;
import logisim_src.tools.Tool;

class ToolboxManip implements ProjectExplorer.Listener {
	private class MyListener
			implements ProjectListener, LibraryListener, AttributeListener {
		private LogisimFile curFile = null;
		
		public void projectChanged(ProjectEvent event) {
			int action = event.getAction();
			if (action == ProjectEvent.ACTION_SET_FILE) {
				setFile((LogisimFile) event.getOldData(),
						(LogisimFile) event.getData());
				explorer.repaint();
			}
		}
		
		private void setFile(LogisimFile oldFile, LogisimFile newFile) {
			if (oldFile != null) {
				removeLibrary(oldFile);
				for (Library lib : oldFile.getLibraries()) {
					removeLibrary(lib);
				}
			}
			curFile = newFile;
			if (newFile != null) {
				addLibrary(newFile);
				for (Library lib : newFile.getLibraries()) {
					addLibrary(lib);
				}
			}
		}

		public void libraryChanged(LibraryEvent event) {
			int action = event.getAction();
			if (action == LibraryEvent.ADD_LIBRARY) {
				if (event.getSource() == curFile) {
					addLibrary((Library) event.getData());
				}
			} else if (action == LibraryEvent.REMOVE_LIBRARY) {
				if (event.getSource() == curFile) {
					removeLibrary((Library) event.getData());
				}
			} else if (action == LibraryEvent.ADD_TOOL) {
				Tool tool = (Tool) event.getData();
				AttributeSet attrs = tool.getAttributeSet();
				if (attrs != null) attrs.addAttributeListener(this);
			} else if (action == LibraryEvent.REMOVE_TOOL) {
				Tool tool = (Tool) event.getData();
				AttributeSet attrs = tool.getAttributeSet();
				if (attrs != null) attrs.removeAttributeListener(this);
			}
			explorer.repaint();
		}
		
		private void addLibrary(Library lib) {
			if (lib instanceof LibraryEventSource) {
				((LibraryEventSource) lib).addLibraryListener(this);
			}
			for (Tool tool : lib.getTools()) {
				AttributeSet attrs = tool.getAttributeSet();
				if (attrs != null) attrs.addAttributeListener(this);
			}
		}
		
		private void removeLibrary(Library lib) {
			if (lib instanceof LibraryEventSource) {
				((LibraryEventSource) lib).removeLibraryListener(this);
			}
			for (Tool tool : lib.getTools()) {
				AttributeSet attrs = tool.getAttributeSet();
				if (attrs != null) attrs.removeAttributeListener(this);
			}
		}


		public void attributeListChanged(AttributeEvent e) { }

		public void attributeValueChanged(AttributeEvent e) {
			explorer.repaint();
		}
		
	}
	
	private Project proj;
	private ProjectExplorer explorer;
	private MyListener myListener = new MyListener();
	private Tool lastSelected = null;
	
	ToolboxManip(Project proj, ProjectExplorer explorer) {
		this.proj = proj;
		this.explorer = explorer;
		proj.addProjectListener(myListener);
		myListener.setFile(null, proj.getLogisimFile());
	}

	public void selectionChanged(ProjectExplorer.Event event) {
		Object selected = event.getTarget();
		if (selected instanceof Tool) {
			if (selected instanceof AddTool) {
				AddTool addTool = (AddTool) selected;
				ComponentFactory source = addTool.getFactory();
				if (source instanceof SubcircuitFactory) {
					SubcircuitFactory circFact = (SubcircuitFactory) source;
					Circuit circ = circFact.getSubcircuit();
					if (proj.getCurrentCircuit() == circ) {
						AttrTableModel m = new AttrTableCircuitModel(proj, circ);
						proj.getFrame().setAttrTableModel(m);
						return;
					}
				}
			}
			
			lastSelected = proj.getTool();
			Tool tool = (Tool) selected;
			proj.setTool(tool);
			proj.getFrame().viewAttributes(tool);
		}
	}

	public void doubleClicked(ProjectExplorer.Event event) {
		Object clicked = event.getTarget();
		if (clicked instanceof AddTool) {
			AddTool tool = (AddTool) clicked;
			ComponentFactory source = tool.getFactory();
			if (source instanceof SubcircuitFactory) {
				SubcircuitFactory circFact = (SubcircuitFactory) source;
				proj.setCurrentCircuit(circFact.getSubcircuit());
				proj.getFrame().setEditorView(Frame.EDIT_LAYOUT);
				if (lastSelected != null) proj.setTool(lastSelected);
			}
		}
	}
	
	public void moveRequested(ProjectExplorer.Event event, AddTool dragged, AddTool target) {
		LogisimFile file = proj.getLogisimFile();
		int draggedIndex = file.getTools().indexOf(dragged);
		int targetIndex = file.getTools().indexOf(target);
		if (targetIndex > draggedIndex) targetIndex++;
		proj.doAction(LogisimFileActions.moveCircuit(dragged, targetIndex));
	}
	
	public void deleteRequested(ProjectExplorer.Event event) {
		Object request = event.getTarget();
		if (request instanceof Library) {
			ProjectLibraryActions.doUnloadLibrary(proj, (Library) request);
		} else if (request instanceof AddTool) {
			ComponentFactory factory = ((AddTool) request).getFactory();
			if (factory instanceof SubcircuitFactory) {
				SubcircuitFactory circFact = (SubcircuitFactory) factory;
				ProjectCircuitActions.doRemoveCircuit(proj, circFact.getSubcircuit());
			}
		}
	}

	public JPopupMenu menuRequested(ProjectExplorer.Event event) {
		Object clicked = event.getTarget();
		if (clicked instanceof AddTool) {
			AddTool tool = (AddTool) clicked;
			ComponentFactory source = tool.getFactory();
			if (source instanceof SubcircuitFactory) {
				Circuit circ = ((SubcircuitFactory) source).getSubcircuit();
				return Popups.forCircuit(proj, tool, circ);
			} else {
				return null;
			}
		} else if (clicked instanceof Tool) {
			return null;
		} else if (clicked == proj.getLogisimFile()) {
			return Popups.forProject(proj);
		} else if (clicked instanceof Library) {
			boolean is_top = event.getTreePath().getPathCount() <= 2;
			return Popups.forLibrary(proj, (Library) clicked, is_top);
		} else {
			return null;
		}
	}

}
