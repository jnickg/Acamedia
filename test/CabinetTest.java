package test;

import java.io.File;
import java.io.PrintStream;

import cabinet.*;

public class CabinetTest
{
	static PrintStream		out		= System.out;	// we will be printing to the standard output stream
	static File				homedir	= new File("C:\\Users\\Nick\\Desktop\\TestFldr"); // test folder on the desktop
	
	public static void main(String[] args) {
		//Item			item;
		
		Cabinet			cab		= new Cabinet(homedir, "cab", out);
		cab.printAll(out);
	}
}
