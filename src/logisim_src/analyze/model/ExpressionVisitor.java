/* Copyright (c) 2010, Carl Burch. License information is located in the
 * logisim_src.Main source code and at www.cburch.com/logisim/. */

package logisim_src.analyze.model;


public interface ExpressionVisitor<T> {
	public T visitAnd(Expression a, Expression b);
	public T visitOr(Expression a, Expression b);
	public T visitXor(Expression a, Expression b);
	public T visitNot(Expression a);
	public T visitVariable(String name);
	public T visitConstant(int value);
}
