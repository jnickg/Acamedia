package net.jnickg.acamedia.fil;

public enum ItemType {
	PDF("application/pdf", "application/x-pdf"),
	DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	DOC("application/msword"),
	PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
	PPT("application/vnd.ms-powerpoint"),
	TXT("text/plain","txt/rtf");

	String [] mime;
	
	ItemType(String ... mime)
	{
		this.mime = mime;
	}
	
	public String[] getMime()
	{
		return mime;
	}
	
	public static ItemType matchType(String t)
	{
		for(ItemType i: ItemType.values())
		{
			for(String s: i.getMime())
			{
				if(s.equalsIgnoreCase(t)) return i;
			}
		}
		return null;
	}
}
