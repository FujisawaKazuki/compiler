package main.interpreter;

public class ParsedLine {
	public String token;
	public String type;
	public int typeNum;
	public int lineNum;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+token+" "+type+" "+typeNum+" "+lineNum;
	}
}
