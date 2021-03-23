/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import javax.swing.JFrame;

import logisim_src.analyze.gui.AnalyzerManager;
import logisim_src.file.LibraryEvent;
import logisim_src.file.LibraryListener;
import logisim_src.gui.prefs.PreferencesFrame;
import logisim_src.proj.Project;
import logisim_src.proj.ProjectEvent;
import logisim_src.proj.ProjectListener;
import logisim_src.proj.Projects;
import logisim_src.util.WindowMenuItemManager;

public class WindowManagers {
	private WindowManagers() { }
	
	public static void initialize() {
		if (!initialized) {
			initialized = true;
			AnalyzerManager.initialize();
			PreferencesFrame.initializeManager();
			Projects.addPropertyChangeListener(Projects.projectListProperty, myListener);
			computeListeners();
		}
	}
	
	private static boolean initialized = false;
	private static MyListener myListener = new MyListener();
	private static HashMap<Project,ProjectManager> projectMap
		= new LinkedHashMap<Project,ProjectManager>();
	
	private static class MyListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent event) {
			computeListeners();
		}
	}

	private static class ProjectManager extends WindowMenuItemManager
			implements ProjectListener, LibraryListener {
		private Project proj;
		
		ProjectManager(Project proj) {
			super(proj.getLogisimFile().getName(), false);
			this.proj = proj;
			proj.addProjectListener(this);
			proj.addLibraryListener(this);
			frameOpened(proj.getFrame());
		}
		
		@Override
		public JFrame getJFrame(boolean create) {
			return proj.getFrame();
		}
		
		public void projectChanged(ProjectEvent event) {
			if (event.getAction() == ProjectEvent.ACTION_SET_FILE) {
				setText(proj.getLogisimFile().getName());
			}
		}

		public void libraryChanged(LibraryEvent event) {
			if (event.getAction() == LibraryEvent.SET_NAME) {
				setText((String) event.getData());
			}           
		}
	}
	
	private static void computeListeners() {
		List<Project> nowOpen = Projects.getOpenProjects();
		
		HashSet<Project> closed = new HashSet<Project>(projectMap.keySet());
		closed.removeAll(nowOpen);
		for (Project proj : closed) {
			ProjectManager manager = projectMap.get(proj);
			manager.frameClosed(manager.getJFrame(false));
			projectMap.remove(proj);
		}
		
		HashSet<Project> opened = new LinkedHashSet<Project>(nowOpen);
		opened.removeAll(projectMap.keySet());
		for (Project proj : opened) {
			ProjectManager manager = new ProjectManager(proj);
			projectMap.put(proj, manager);
		}
	}
}
