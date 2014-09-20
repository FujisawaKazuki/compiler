package main.node;

import java.util.LinkedList;

import main.interpreter.Context;
import main.interpreter.Logger;
import main.interpreter.Node;
import main.interpreter.ParseException;
import main.interpreter.ParsedLine;
import main.interpreter.SyntaxException;

public class ProgramNode extends Node {
	private LinkedList<Node> program;
	
	@Override
	public boolean parse(Context context) throws ParseException,
			SyntaxException {
		ParsedLine pl = context.nextLine();
		if(pl.type.equals("SPROGRAM")){
			Logger.printLog("NextProcess");
			return true;
		} else {
			throw new SyntaxException();
		}
	}

}
