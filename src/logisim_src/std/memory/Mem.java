/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.std.memory;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.WeakHashMap;

import hex.HexModel;
import hex.HexModelListener;
import logisim_src.circuit.CircuitState;
import logisim_src.data.Attribute;
import logisim_src.data.AttributeSet;
import logisim_src.data.Attributes;
import logisim_src.data.BitWidth;
import logisim_src.data.Bounds;
import logisim_src.data.Direction;
import logisim_src.gui.hex.HexFile;
import logisim_src.gui.hex.HexFrame;
import logisim_src.instance.Instance;
import logisim_src.instance.InstanceFactory;
import logisim_src.instance.InstancePainter;
import logisim_src.instance.InstanceState;
import logisim_src.instance.Port;
import logisim_src.proj.Project;
import logisim_src.tools.MenuExtender;
import logisim_src.tools.key.BitWidthConfigurator;
import logisim_src.tools.key.JoinedConfigurator;
import logisim_src.util.GraphicsUtil;
import logisim_src.util.StringGetter;
import logisim_src.util.StringUtil;

abstract class Mem extends InstanceFactory {
	// Note: The code is meant to be able to handle up to 32-bit addresses, but it
	// hasn't been debugged thoroughly. There are two definite changes I would
	// make if I were to extend the address bits: First, there would need to be some
	// modification to the memory's graphical representation, because there isn't
	// room in the box to include such long memory addresses with the current font
	// size. And second, I'd alter the MemContents class's PAGE_SIZE_BITS constant
	// to 14 so that its "page table" isn't quite so big.
	public static final Attribute<BitWidth> ADDR_ATTR = Attributes.forBitWidth(
			"addrWidth", Strings.getter("ramAddrWidthAttr"), 2, 24);
	public static final Attribute<BitWidth> DATA_ATTR = Attributes.forBitWidth(
			"dataWidth", Strings.getter("ramDataWidthAttr"));
	
	// port-related constants
	static final int DATA = 0;
	static final int ADDR = 1;
	static final int CS = 2;
	static final int MEM_INPUTS = 3;

	// other constants
	static final int DELAY = 10;

	private WeakHashMap<Instance,File> currentInstanceFiles;

	Mem(String name, StringGetter desc, int extraPorts) {
		super(name, desc);
		currentInstanceFiles = new WeakHashMap<Instance,File>();
		setInstancePoker(MemPoker.class);
		setKeyConfigurator(JoinedConfigurator.create(
				new BitWidthConfigurator(ADDR_ATTR, 2, 24, 0),
				new BitWidthConfigurator(DATA_ATTR)));

		setOffsetBounds(Bounds.create(-140, -40, 140, 80));
	}
	
	abstract void configurePorts(Instance instance);
	@Override
	public abstract AttributeSet createAttributeSet();
	abstract MemState getState(InstanceState state);
	abstract MemState getState(Instance instance, CircuitState state);
	abstract HexFrame getHexFrame(Project proj, Instance instance, CircuitState state);
	@Override
	public abstract void propagate(InstanceState state);

	@Override
	protected void configureNewInstance(Instance instance) {
		configurePorts(instance);
	}
	
	void configureStandardPorts(Instance instance, Port[] ps) {
		ps[DATA] = new Port(   0,  0, Port.INOUT, DATA_ATTR);
		ps[ADDR] = new Port(-140,  0, Port.INPUT, ADDR_ATTR);
		ps[CS]   = new Port( -90, 40, Port.INPUT, 1);
		ps[DATA].setToolTip(Strings.getter("memDataTip"));
		ps[ADDR].setToolTip(Strings.getter("memAddrTip"));
		ps[CS].setToolTip(Strings.getter("memCSTip"));
	}

	@Override
	public void paintInstance(InstancePainter painter) {
		Graphics g = painter.getGraphics();
		Bounds bds = painter.getBounds();

		// draw boundary
		painter.drawBounds();

		// draw contents
		if (painter.getShowState()) {
			MemState state = getState(painter);
			state.paint(painter.getGraphics(), bds.getX(), bds.getY());
		} else {
			BitWidth addr = painter.getAttributeValue(ADDR_ATTR);
			int addrBits = addr.getWidth();
			int bytes = 1 << addrBits;
			String label;
			if (this instanceof Rom) {
				if (addrBits >= 30) {
					label = StringUtil.format(Strings.get("romGigabyteLabel"), ""
							+ (bytes >>> 30));
				} else if (addrBits >= 20) {
					label = StringUtil.format(Strings.get("romMegabyteLabel"), ""
							+ (bytes >> 20));
				} else if (addrBits >= 10) {
					label = StringUtil.format(Strings.get("romKilobyteLabel"), ""
							+ (bytes >> 10));
				} else {
					label = StringUtil.format(Strings.get("romByteLabel"), ""
							+ bytes);
				}
			} else {
				if (addrBits >= 30) {
					label = StringUtil.format(Strings.get("ramGigabyteLabel"), ""
							+ (bytes >>> 30));
				} else if (addrBits >= 20) {
					label = StringUtil.format(Strings.get("ramMegabyteLabel"), ""
							+ (bytes >> 20));
				} else if (addrBits >= 10) {
					label = StringUtil.format(Strings.get("ramKilobyteLabel"), ""
							+ (bytes >> 10));
				} else {
					label = StringUtil.format(Strings.get("ramByteLabel"), ""
							+ bytes);
				}
			}
			GraphicsUtil.drawCenteredText(g, label, bds.getX() + bds.getWidth()
					/ 2, bds.getY() + bds.getHeight() / 2);
		}

		// draw input and output ports
		painter.drawPort(DATA, Strings.get("ramDataLabel"), Direction.WEST);
		painter.drawPort(ADDR, Strings.get("ramAddrLabel"), Direction.EAST);
		g.setColor(Color.GRAY);
		painter.drawPort(CS, Strings.get("ramCSLabel"), Direction.SOUTH);
	}
	
	File getCurrentImage(Instance instance) {
		return currentInstanceFiles.get(instance);
	}
	
	void setCurrentImage(Instance instance, File value) {
		currentInstanceFiles.put(instance, value);
	}
	
	public void loadImage(InstanceState instanceState, File imageFile)
			throws IOException { 
		MemState s = this.getState(instanceState);
		HexFile.open(s.getContents(), imageFile);
		this.setCurrentImage(instanceState.getInstance(), imageFile);
	}

	@Override
	protected Object getInstanceFeature(Instance instance, Object key) {
		if (key == MenuExtender.class) return new MemMenu(this, instance);
		return super.getInstanceFeature(instance, key);
	}
	
	static class MemListener implements HexModelListener {
		Instance instance;
		
		MemListener(Instance instance) { this.instance = instance; }
		
		public void metainfoChanged(HexModel source) { }

		public void bytesChanged(HexModel source, long start,
				long numBytes, int[] values) {
			instance.fireInvalidated();
		}
	}
}
