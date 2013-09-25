package net.jnickg.acamedia.strg;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

import net.jnickg.acamedia.fil.Item;

public class Cabinet
		extends Storage
{
	
	public Cabinet(File loc, String lbl)
	{
		super(loc, lbl);
	}
	
	public void save(PrintStream out)
	{
		this.saveAllSubs(out);
	}

}
