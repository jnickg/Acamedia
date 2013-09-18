package net.jnickg.acamedia.strg;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

import net.jnickg.acamedia.fil.Item;

public class Cabinet
		extends Storage
{
	
	public Cabinet(File loc, String lbl, PrintStream out)
	{
		super(loc, lbl, out);
	}
	
	@Override
	public void saveAll(PrintStream out)
	{
		out.println("SAVING CABINET\n\n");
		// Save own-level contents
		for(Item i: this.getContents())
		{
			i.saveFile(out);
		}
		
		// invoke save method for each folder
		for(Folder f: this.getSubfolders())
		{
			f.saveAll(out);
		}
	}

}
