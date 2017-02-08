package GameEngine;


public class Key implements Comparable<Key>{
	
	private int code;
	private char key;
	
	public Key(int c, char ch){
		code = c;
		key = ch;
	}

	public int getCode() {
		return code;
	}

	public char getKey() {
		return key;
	}

	@Override
	public int compareTo(Key o) {
		return (o.getCode()!=code || o.getKey()!=key)?0:1;
	}
	
	public String toString(){
		return code + "(" + key + ")";
	}

}
