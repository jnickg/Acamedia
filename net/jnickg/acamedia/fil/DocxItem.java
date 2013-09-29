package net.jnickg.acamedia.fil;

import java.io.File;
import java.io.PrintStream;

public class DocxItem extends Item {

	// For implementation of Serializable interface
	private static final long serialVersionUID = 1L;

	public DocxItem(File f)
	{
		super(f);
		// TODO Auto-generated constructor stub
	}

	public void pullMetadata(PrintStream out)
	{
		
	}
	
	public String print()
	{
		return "DOCX item unsupported for now";
	}
}
