package net.jnickg.acamedia.fil;

import java.io.File;

public enum ItemType {
	//The below are already supported
	PDF("application/pdf", "application/x-pdf")
	{
		public Item makeInstance(File f)
		{
			return new PdfItem(f);
		}
	},
	DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	{
		public Item makeInstance(File f)
		{
			return new DocxItem(f);
		}
		
	};
	//The below are not yet supported
	//DOC("application/msword"),
	//PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
	//PPT("application/vnd.ms-powerpoint"),
	//TXT("text/plain","txt/rtf");

	
	// Multipurpose Internet Mail Extension (MIME) Filetype as defined here
	// http://www.ietf.org/rfc/rfc2045.txt
	String [] mime;
	
	ItemType(String ... mime)
	{
		this.mime = mime;
	}
	
	public String[] getMime()
	{
		return mime;
	}
	
	// Used to match MIME type with its corresponding class
	public static ItemType matchType(String t)
		throws IllegalArgumentException
	{
		for(ItemType i: ItemType.values())
		{
			for(String s: i.getMime())
			{
				if(s.equalsIgnoreCase(t)) return i;
			}
		}
		throw new IllegalArgumentException("Unsupported file type " + t);
	}
	
	// Since this enum is used to match MIME types with corresponding classes
	// every ItemType instance must be able to make an instance of its
	// corresponding class.
	public abstract Item makeInstance(File f);
}
