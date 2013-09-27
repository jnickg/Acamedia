package net.jnickg.acamedia.strg;

import java.io.File;

public class Folder
		extends Storage
{

	// For implementation of Serializable interface
	private static final long serialVersionUID = 1L;

	public Folder(File loc, String lbl)
	{
		super(loc, lbl);
	}

}
