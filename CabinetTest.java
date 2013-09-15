import cabinet.*;
import java.io.File;
import java.io.PrintStream;

public class CabinetTest
{
	
	
	public static void main(String[] args)
	{
		//Item			item;
		PrintStream		out		= System.out;	// we will be printing to the standard output stream
		File				homedir	= new File("C:\\Users\\Nick\\Desktop\\TestFldr"); // test folder on the desktop
		
		out.println("CLASSPATH: " + System.getProperty("java.class.path"));
		
		Cabinet	cab	= new Cabinet(homedir, "cab", out);
		cab.printAll(out);
	}
}