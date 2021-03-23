/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.circuit;

import java.util.Collection;

public class CircuitTransactionResult {
	private CircuitMutatorImpl mutator;
	
	CircuitTransactionResult(CircuitMutatorImpl mutator) {
		this.mutator = mutator;
	}
	
	public CircuitTransaction getReverseTransaction() {
		return mutator.getReverseTransaction();
	}
	
	public ReplacementMap getReplacementMap(Circuit circuit) {
		ReplacementMap ret = mutator.getReplacementMap(circuit);
		return ret == null ? new ReplacementMap() : ret;
	}
	
	public Collection<Circuit> getModifiedCircuits() {
		return mutator.getModifiedCircuits();
	}
}
