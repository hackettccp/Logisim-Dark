/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.gui.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

import draw.toolbar.ToolbarItem;
import logisim_src.gui.menu.LogisimMenuItem;
import logisim_src.util.Icons;
import logisim_src.util.StringGetter;

class LogisimToolbarItem implements ToolbarItem {
	private MenuListener menu;
	private Icon icon;
	private LogisimMenuItem action;
	private StringGetter toolTip;
	
	public LogisimToolbarItem(MenuListener menu, String iconName,
			LogisimMenuItem action, StringGetter toolTip) {
		this.menu = menu;
		this.icon = Icons.getIcon(iconName);
		this.action = action;
		this.toolTip = toolTip;
	}
	
	public void setIcon(String iconName) {
		this.icon = Icons.getIcon(iconName);
	}
	
	public void setToolTip(StringGetter toolTip) {
		this.toolTip = toolTip;
	}
	
	public void doAction() {
		if (menu != null && menu.isEnabled(action)) {
			menu.doAction(action);
		}
	}
	
	public boolean isSelectable() {
		return menu != null && menu.isEnabled(action);
	}
	
	public void paintIcon(Component destination, Graphics g) {
		if (!isSelectable() && g instanceof Graphics2D) {
			Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
			((Graphics2D) g).setComposite(c);
		}

		if (icon == null) {
			g.setColor(new Color(255, 128, 128));
			g.fillRect(4, 4, 8, 8);
			g.setColor(Color.BLACK);
			g.drawLine(4, 4, 12, 12);
			g.drawLine(4, 12, 12, 4);
			g.drawRect(4, 4, 8, 8);
		} else {
			icon.paintIcon(destination, g, 0, 1);
		}
	}
	
	public String getToolTip() {
		if (toolTip != null) {
			return toolTip.get();
		} else {
			return null;
		}
	}
	
	public Dimension getDimension(Object orientation) {
		if (icon == null) {
			return new Dimension(16, 16);
		} else {
			int w = icon.getIconWidth();
			int h = icon.getIconHeight();
			return new Dimension(w, h + 2);
		}
	}
}
