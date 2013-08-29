package cabinet;

import java.util.*;

public abstract class Item
	implements Comparable<Item>
{
	/* Variable Members */
	private			String		title; // the name of the item
	private			Set<String> tags; // user-input tags
	private			String		filepath; // the location of the related file
	private			String		type; // user-input type of item that it is (unrelated to its concrete Class)
	
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
	Item()
	{
		title = "";
		
		tags = new TreeSet<>();
		tags.add(notag);
		
		type = "n/a";
		
		filepath = "";
	}
	
	Item(String tit, String ... tgs)
	{
		title = tit;
		
		tags = new TreeSet<>();
		for(String s: tgs) tags.add(s);
		
		type = "n/a";
		
		filepath = "";
	}
	
	Item(String tit, String typ, String fp, String ... tgs)
	{
		title = tit;
		
		tags = new TreeSet<>();
		for(String s: tgs) tags.add(s);
		
		type = typ;
		types.add(typ);
		
		filepath = fp;
	}
	
	/* General Methods */
	
	@Override
	public abstract String toString();
	
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
	public String getFilepath()
	{
		return filepath;
	}
	
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}

}
