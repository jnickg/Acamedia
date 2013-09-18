import java.io.File;
import java.io.PrintStream;

import strg.*;

public class CabinetTest
{
	static	File			homedir	= new File("C:\\Users\\Nick\\Desktop\\TestFldr"); // test folder on the desktop
	static	PrintStream		out		= System.out;	// we will be printing to the standard output stream
	
	public static void main(String[] args)
	{
		//Item			item;
		
		
		//File				homedir = new File("C:\\Users\\Nick\\Dropbox\\");
		
		//out.println("CLASSPATH: " + System.getProperty("java.class.path"));
		
		Cabinet	cab	= new Cabinet(homedir, "cab", out);
		cab.printAll(out);
		
		for(Item i: cab.allContentsForTitle("Delbanco.pdf"))
		{
			i.openFile(out);
		}
		
		cab.saveAll(out);
	}
}
