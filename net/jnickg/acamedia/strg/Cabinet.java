package net.jnickg.acamedia.strg;

import java.io.File;
import java.io.PrintStream;

public class Cabinet
		extends Storage
{
	
	// For implementation of Serializable interface
	private static final long serialVersionUID = 1L;

	public Cabinet(File loc, String lbl)
	{
		super(loc, lbl);
	}
	
	public void save(PrintStream out)
	{
		this.saveAllSubs(out);
	}

}
