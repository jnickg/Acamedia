package strg;

import java.io.File;
import java.io.PrintStream;

public class Folder
		extends Storage
{

	public Folder(File loc, String lbl, PrintStream out)
	{
		super(loc, lbl, out);
	}

}
