package test;

import java.io.File;
import java.io.PrintStream;
import java.util.Collection;

import cabinet.*;

public class CabinetTest {

	public static void main(String[] args) {
		PrintStream		out		= System.out;	// we will be printing to the standard output stream
		File			homedir	= new File("C:\\Users\\Nick\\Desktop\\TestFldr"); // test folder on the desktop
		
		Item			item;
		
		Cabinet			cab		= new Cabinet(homedir, "cab", out);
		cab.printAll(out);
	}

}
