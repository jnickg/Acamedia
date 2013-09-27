package net.jnickg.acamedia;
import java.io.File;
import java.io.PrintStream;

import net.jnickg.acamedia.strg.*;

public class CabinetTest
{
	static	File			homedir	= new File("C:\\Users\\Nick\\Desktop\\TestFldr"); // test folder on the desktop
	static	PrintStream		out		= System.out;	// we will be printing to the standard output stream
	
	public static void main(String[] args)
	{	
		//out.println("CLASSPATH: " + System.getProperty("java.class.path"));
		
		Cabinet	cab	= new Cabinet(homedir, "cab");
		cab.printAll(out);
		cab.autoDetectSubs(out);
		cab.printAll(out);
	}
}
