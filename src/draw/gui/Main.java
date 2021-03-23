/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package draw.gui;

import java.awt.BorderLayout;
import java.util.Collections;

import javax.swing.JFrame;

import draw.canvas.Canvas;
import draw.model.CanvasObject;
import draw.model.Drawing;
import draw.shapes.Rectangle;
import draw.tools.DrawingAttributeSet;
import draw.undo.UndoLog;
import draw.undo.UndoLogDispatcher;
import logisim_src.gui.generic.AttrTable;
import logisim_src.util.HorizontalSplitPane;
import logisim_src.util.VerticalSplitPane;

public class Main {
	public static void main(String[] args) {
		DrawingAttributeSet attrs = new DrawingAttributeSet();
		Drawing model = new Drawing();
		CanvasObject rect = attrs.applyTo(new Rectangle(25, 25, 50, 50));
		model.addObjects(0, Collections.singleton(rect));

		showFrame(model, "Drawing 1");
		showFrame(model, "Drawing 2");
	}
	
	private static void showFrame(Drawing model, String title) {
		JFrame frame = new JFrame(title);
		DrawingAttributeSet attrs = new DrawingAttributeSet();

		Canvas canvas = new Canvas();
		Toolbar toolbar = new Toolbar(canvas, attrs);
		canvas.setModel(model, new UndoLogDispatcher(new UndoLog()));
		canvas.setTool(toolbar.getDefaultTool());
		
		AttrTable table = new AttrTable(frame);
		AttrTableDrawManager manager = new AttrTableDrawManager(canvas, table, attrs);
		manager.attributesSelected();
		HorizontalSplitPane west = new HorizontalSplitPane(toolbar, table, 0.5);
		VerticalSplitPane all = new VerticalSplitPane(west, canvas, 0.3);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(all, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
