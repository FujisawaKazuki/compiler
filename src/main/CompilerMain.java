package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import main.interpreter.Context;
import main.interpreter.FileContext;
import main.interpreter.Logger;
import main.interpreter.Node;
import main.node.ProgramNode;
import main.util.SpaceTokenizer;

public class CompilerMain {

	public static void main(String[] args) throws Exception{
		Context ctx = new FileContext(new File(args[0]));
		
		Node initialNode = new ProgramNode();
		initialNode.parse(ctx);
		Logger.printLog(""+initialNode);
	}

}
