import inout.InOut;

import java.io.IOException;

import data.Tree;


public class Main {
	
	public static void main(String[] args) throws IOException {
		InOut io = new InOut();
		Tree t = io.readFile(InOut.FILE_IN);
		io.writeFile(InOut.FILE_OUT, t, 150);		
	}
}
