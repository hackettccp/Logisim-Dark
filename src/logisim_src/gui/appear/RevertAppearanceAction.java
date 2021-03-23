/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.appear;

import java.util.ArrayList;

import draw.model.CanvasObject;
import logisim_src.circuit.Circuit;
import logisim_src.circuit.appear.CircuitAppearance;
import logisim_src.proj.Action;
import logisim_src.proj.Project;

public class RevertAppearanceAction extends Action {
	private Circuit circuit;
	private ArrayList<CanvasObject> old;
	private boolean wasDefault;
	
	public RevertAppearanceAction(Circuit circuit) {
		this.circuit = circuit;
	}
	
	@Override
	public String getName() {
		return Strings.get("revertAppearanceAction");
	}

	@Override
	public void doIt(Project proj) {
		CircuitAppearance appear = circuit.getAppearance();
		wasDefault = appear.isDefaultAppearance();
		old = new ArrayList<CanvasObject>(appear.getObjectsFromBottom());
		appear.setDefaultAppearance(true);
	}

	@Override
	public void undo(Project proj) {
		CircuitAppearance appear = circuit.getAppearance();
		appear.setObjectsForce(old);
		appear.setDefaultAppearance(wasDefault);
	}
}
