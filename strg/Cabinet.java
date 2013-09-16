package strg;

import java.io.File;
import java.io.PrintStream;

public class Cabinet
		extends Storage
{
	
	public Cabinet(File loc, String lbl, PrintStream out)
	{
		super(loc, lbl, out);
	}

}
