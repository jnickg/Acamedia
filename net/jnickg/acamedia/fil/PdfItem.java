package net.jnickg.acamedia.fil;

import java.util.*;
import java.io.*;

import com.itextpdf.text.pdf.*;

public class PdfItem
		extends Item
{
	// For implementation of Serializable interface
	private static final long serialVersionUID = 1L;
	
	private	PdfReader	reader;
	private	PdfWriter	writer;
	
	public PdfItem(File f)
	{
		super(f);
	}
	
	public void pullMetadata(PrintStream out)
	{
		try
		{
			reader = new PdfReader(this.toString());
			Map<String, String> info = reader.getInfo();
			this.addMetadata(info);
		}
		catch(Exception e)
		{
			out.println("SOMETHING WENT WRONG!!! EXCEPTION");
			out.println(e.getMessage());
			out.println(e.getLocalizedMessage());
			out.println(e.getCause().toString());
			out.println(e.getStackTrace().toString());
		}
		catch(Error e)
		{
			out.println("SOMETHING WENT WRONG!!! ERROR");
			out.println(e.getMessage());
			out.println(e.getLocalizedMessage());
			out.println(e.getCause().toString());
			out.println(e.getStackTrace().toString());

		}
	}
	
	public void pushMetadata(PrintStream out)
	{
		
	}
	
	public String print()
	{
		return print("");
	}
	
	public String print(String indent)
	{
		StringBuilder prnt = new StringBuilder();
		
		prnt.append(this.getName());
		
		if (this.hasMetadata())
		{
			prnt.append("\n" + indent + "METADATA:\n");
			Map<String, String> md = this.getMetadata();
			for(String s: md.keySet())
			{
				prnt.append(indent + '\t' + s + ": " + md.get(s) + "\n");
			}
		}
		
		return prnt.toString();
	}
}