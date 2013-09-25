package net.jnickg.acamedia;
import java.io.File;
import java.io.PrintStream;
import java.util.Set;

import net.jnickg.acamedia.fil.*;
import net.jnickg.acamedia.strg.*;

public class CabinetTest
{
	static	File			homedir	= new File("C:\\Users\\Nick\\Desktop\\TestFldr"); // test folder on the desktop
	static	PrintStream		out		= System.out;	// we will be printing to the standard output stream
	
	public static void main(String[] args)
	{
		//Item			item;
		
		
		//File				homedir = new File("C:\\Users\\Nick\\Dropbox\\");
		
		//out.println("CLASSPATH: " + System.getProperty("java.class.path"));
		
		Cabinet	cab	= new Cabinet(homedir, "cab");
		cab.printAll(out);
		cab.autoDetectSubs(out);
		cab.printAll(out);
		
//		Set<Item> cabcnts = cab.getContents();
//		for(Item i: cabcnts)
//		{
//			out.println("TESTING NEW PDF FILE");
//			out.println(i.getFile().toString());
//			PdfItem test = new PdfItem(i.getFile().toString(), out);
//			out.print("\n\n");
//		}
		
//		for(Item i: cab.allContentsForTitle("Delbanco.pdf"))
//		{
//			i.openFile(out);
//		}
//		
//		cab.saveAll(out);
		
		//PdfItem test = new PdfItem("C:\\Users\\Nick\\Desktop\\TestFldr\\cab\\Delbanco.pdf", out);
	}
}
