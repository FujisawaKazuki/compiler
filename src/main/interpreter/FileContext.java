package main.interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.util.SpaceTokenizer;

public class FileContext implements Context {
	private int lineNumber = 0;
	private BufferedReader breader;
	private ParsedLine currentLine;
	
	public FileContext(File file) throws IOException{
		// TODO Auto-generated constructor stub
		breader = new BufferedReader(new FileReader(file));
	}
	
	@Override
	public ParsedLine nextLine() {
		// TODO Auto-generated method stub
		try {
			String line = breader.readLine();
			if(line == null) return null;
			
			SpaceTokenizer st = new SpaceTokenizer(line);
			ParsedLine pl = new ParsedLine();
			pl.token = st.nextToken();
			pl.type = st.nextToken();
			pl.typeNum = Integer.parseInt(st.nextToken());
			pl.lineNum = Integer.parseInt(st.nextToken());
			
			currentLine = pl;
			return pl;
		} catch (IOException e){
			Logger.printLog(e.toString());
		}
		return null;
	}

	@Override
	public ParsedLine currentLine() {
		// TODO Auto-generated method stub
		return currentLine;
	}
}
