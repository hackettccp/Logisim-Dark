/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import draw.toolbar.Toolbar;
import draw.toolbar.ToolbarItem;
import draw.toolbar.ToolbarModel;

public class KeyboardToolSelection extends AbstractAction {
	public static void register(Toolbar toolbar) {
		ActionMap amap = toolbar.getActionMap();
		InputMap imap = toolbar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		int mask = toolbar.getToolkit().getMenuShortcutKeyMask();
		for (int i = 0; i < 10; i++) {
			KeyStroke keyStroke = KeyStroke.getKeyStroke((char) ('0' + i), mask);
			int j = (i == 0 ? 10 : i - 1);
			KeyboardToolSelection action = new KeyboardToolSelection(toolbar, j);
			String key = "ToolSelect" + i;
			amap.put(key, action);
			imap.put(keyStroke, key);
		}
	}
	
	private Toolbar toolbar;
	private int index;
	
	public KeyboardToolSelection(Toolbar toolbar, int index) {
		this.toolbar = toolbar;
		this.index = index;
	}
	
	public void actionPerformed(ActionEvent event) {
		ToolbarModel model = toolbar.getToolbarModel();
		int i = -1;
		for (ToolbarItem item : model.getItems()) {
			if (item.isSelectable()) {
				i++;
				if (i == index) {
					model.itemSelected(item);
				}
			}
		}
	}
}
