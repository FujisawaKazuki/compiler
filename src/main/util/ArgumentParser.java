package main.util;

import java.util.LinkedList;

import main.interpreter.Context;
import main.interpreter.Logger;
import main.interpreter.ParsedLine;
import main.interpreter.SyntaxException;

public class ArgumentParser {
	public static LinkedList<String> parse(Context context) throws SyntaxException{
		LinkedList<String> ret = new LinkedList<String>();
		
		ParsedLine pl = context.nextLine();
		while(pl.type.equals("SIDENTIFIER")){
			ret.add(pl.token);
			pl = context.nextLine();
			if(!pl.type.equals("SCOMMA")){
			} else if(pl.type.equals("SRPAREN")){
				break;
			} else {
				throw new SyntaxException();
			}
			pl = context.nextLine();
		}
		
		return ret;
	}
}
