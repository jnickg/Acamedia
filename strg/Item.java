package strg;

// TODO Clean up imports!
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.*;

public abstract class Item
	implements Comparable<Item>
{
	/* Variable Members */
	private			String		title; // the name of the item
	private			File		file; // the full location of the related file
	
	private			Set<String> tags; // user-input tags
	private			String		type; // user-input type of item that it is (unrelated to its concrete Class)
	private 		String		uuid; // universally unique identifier (deprecated)
	
	private static final String notag = "none"; // used when there are no members for "tags"
	private static	Set<String> types = new TreeSet<>(); // Stores built-in types of Items
	static
	{
		types.add("literature");
		types.add("course materials");
		types.add("notes");
		types.add("other");
		types.add("n/a");
	}

	/* Constructors */
	Item(String tit, File loc)
	{
		title = tit;
		
		tags = new TreeSet<>();
		tags.add(notag);
		
		type = "n/a";
		
		file = loc;
		
		uuid = UUID.randomUUID().toString();
	}
	
	Item(String tit, File loc, String ... tgs)
	{
		title = tit;
		
		tags = new TreeSet<>();
		for(String s: tgs) tags.add(s);
		
		type = "n/a";
		
		file = loc;
		
		uuid = UUID.randomUUID().toString();
	}
	
	Item(String tit, File loc, String typ, String ... tgs)
	{
		title = tit;
		
		tags = new TreeSet<>();
		for(String s: tgs) tags.add(s);
		
		type = typ;
		types.add(typ);
		
		// puts the file in "loc" which is the location of the folder
		// "fp" is the name of the file.
		file = new File(loc, tit);
		
		uuid = UUID.randomUUID().toString();
	}
	
	/* General Methods */
	
	@Override
	public abstract String toString();
	
	public abstract List<String> getCitation();
	
	public int compareTo(Item other)
	{
		return this.getTitle().compareTo(other.getTitle());
	}
	
	// Deprecated
	public boolean matchTag(String ... tgs)
	{
		for(String s: tgs)	if(tags.contains(s.toLowerCase()))	return true;
		return false;
	}

	/* Title Methods */
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	/* Tags Methods */
	public Set<String>getTagSet()
	{
		return tags;
	}

	// Returns an item's keywords as a single string, separated by
	// commas.
	public String getTags()
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
		types.add(type);
	}
	
	public static Set<String> getTypes()
	{
		return types;
	}
	public static Boolean addType(String type)
	{
		return (types.add(type));
	}
	
	/* Filepath Methods */
	public File getFile()
	{
		return file;
	}
	
	public void setFile(File f)
	{
		file = f;
	}
	
	public void openFile(PrintStream out)
	{
		try
		{
			out.println("OPENING FILE\n\t" + file.toString());
			Desktop.getDesktop().open(file);
		}
		catch(IOException e)
		{
			out.println(e.getMessage());
		}
	}
	
	public void saveFile(PrintStream out)
	{
		out.println("Saving the file '" + this.getTitle() + "'\n\tto path '" + this.getFile().toString() + "'");
	}

	/* UUID Methods */
	public String getUUID()
	{
		return uuid;
	}
}
