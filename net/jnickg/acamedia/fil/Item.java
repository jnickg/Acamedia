package net.jnickg.acamedia.fil;

// TODO Clean up imports!
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.*;

public abstract class Item
	extends File
{
	// For implementation of Serializable interface
	private static final long serialVersionUID = 1L;
	
	/* Variable Members */
	private			Map<String, String>	metadata; // stores the item's metadata for quick retrieval
	private			Set<String> 		tags; // user-input tags
	private			String				type; // user-input type of item that it is (unrelated to its concrete Class)
	private			String				ftype; // file extension, if present
	private 		String				uuid; // universally unique identifier (deprecated)
	
	private static final String notag = "none"; // used when there are no members for "tags"
			//	private static	Set<String> types = new TreeSet<>(); // Stores built-in types of Items
			//	static
			//	{
			//		types.add("literature");
			//		types.add("course materials");
			//		types.add("notes");
			//		types.add("other");
			//		types.add("n/a");
			//	}

	/* Constructors */
	Item(File loc, String tit)
	{
		super(loc, tit);
		if(this.isFile())
		{
			String tmp = this.getName();
			ftype = tmp.substring(tmp.lastIndexOf('.'));
		}
		
		metadata = new HashMap<>();
		
		tags = new TreeSet<>();
		tags.add(notag);
		
		uuid = UUID.randomUUID().toString(); //TODO make this UUID relevant to the file's data
	}
	
	
	
	
	/* General Methods */
	public List<String> getCitation()
	{
		// TODO use metadata to generate citation
		return null;
	}
	
	public int compareTo(Item other)
	{
		return this.getName().compareTo(other.getName());
	}
	
	
	
	
	/* Metadata Methods */
	public Map<String, String> getMetadata()
	{
		return metadata;
	}
	
	public void setMetadata(Map<String, String> metadata)
	{
		this.metadata = metadata;
	}
	
	public void addMetadata(Map<String, String> metadata)
	{
		metadata.putAll(metadata);
	}
	
	
	
	
	/* Tags Methods */
	public Set<String>getTags()
	{
		return tags;
	}

	// Returns an item's keywords as a single string, separated by commas.
	public String getTagstr()
	{
		Boolean first = true;
		StringBuilder tgs = new StringBuilder();
		for(String s: tags)
		{
			tgs.append((first ? "" : ", ") + s);
			if (first) first=false;
		}
		return tgs.toString();
	}
	
	public void setTags(String ... tgs)
	{
		tags.clear();
		for(String s: tgs) tags.add(s);
	}
	
	public void addTags(String ... tgs)
	{
		if(tags.contains("none")) tags.clear();
		for(String s: tgs) tags.add(s);
	}
	
	public Boolean rmvTag(String tag)
	{
		Boolean wasThere = tags.remove(tag);
		if (tags.isEmpty()) tags.add(notag);
		return wasThere;
	}
	
	
	
	
	/* Type Methods */
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	
	
	/* File Methods */
	public void openFile(PrintStream out)
	{
		File dis = this.getAbsoluteFile();
		try
		{
			out.println("OPENING FILE\n\t" + dis.toString());
			Desktop.getDesktop().open(dis);
		}
		catch(IOException e)
		{
			out.println(e.getMessage());
			out.println(e.getStackTrace());
		}
	}
	
	public void saveFile(PrintStream out)
	{
		out.println("Saving the file '" + this.getName() + "'\n\tto path '" + this.toString() + "'");
	}
	
	
	
	
	/* Filetype MEthods */
	public String getFtype()
	{
		return ftype;
	}
	
	
	
	
	/* UUID Methods */
	public String getUUID()
	{
		return uuid;
	}
}
