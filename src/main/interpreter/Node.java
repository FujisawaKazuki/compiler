package main.interpreter;

public abstract class Node {
	public abstract boolean parse(Context context) throws ParseException, SyntaxException;
}
