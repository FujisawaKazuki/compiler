package main.interpreter;

public interface Context {
	public ParsedLine nextLine();
	public ParsedLine currentLine();
}
