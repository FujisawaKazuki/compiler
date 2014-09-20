package main.node;

import java.util.LinkedList;

import main.interpreter.Context;
import main.interpreter.Logger;
import main.interpreter.Node;
import main.interpreter.ParseException;
import main.interpreter.ParsedLine;
import main.interpreter.SyntaxException;
import main.util.ArgumentParser;

public class ProgramNode extends Node {
	private LinkedList<Node> program;
	private String name;
	private LinkedList<String> args;
	
	@Override
	public boolean parse(Context context) throws ParseException,
			SyntaxException {
		ParsedLine pl = context.nextLine();
		if(pl.type.equals("SPROGRAM")){
			pl = context.nextLine();
			
			if(pl.type.equals("SIDENTIFIER")){
				this.name = pl.token;
			} else {
				throw new SyntaxException();
			}
			
			pl = context.nextLine();
			if(pl.type.equals("SLPAREN")){
				args = ArgumentParser.parse(context);
			}
			
			pl = context.nextLine();
			if(!pl.type.equals("SSEMICOLON")){
				throw new SyntaxException();
			}
			
			
			
			Logger.printLog("NextProcess");
			return true;
		} else {
			throw new SyntaxException();
		}
	}

	@Override
	public String toString() {
		return "PROGRAM name:"+name+" args:"+args;
	}
	
	

}
