package cabinet;

import java.util.*;

public abstract class Item
	implements Comparable<Item>
{
	/* Variable Members */
	private			String		title;
	private			Set<String> keywords;
	private			String		filepath;
	private			String		type;
	
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
		
		keywords = new TreeSet<>();
		keywords.add("none");
		
		type = "n/a";
		
		filepath = "";
	}
	
	Item(String tit, String ... kw)
	{
		title = tit;
		
		keywords = new TreeSet<>();
		for(String s: kw) keywords.add(s);
		
		type = "n/a";
		
		filepath = "";
	}
	
	Item(String tit, String typ, String fp, String ... kw)
	{
		title = tit;
		
		keywords = new TreeSet<>();
		for(String s: kw) keywords.add(s);
		
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
	public boolean matchKw(String ... kw)
	{
		for(String s: kw)	if(keywords.contains(s.toLowerCase()))	return true;
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

	/* Keywords Methods */
	public Set<String>getKWset()
	{
		return keywords;
	}

	// Returns an item's keywords as a single string, separated by
	// commas.
	public String getKeywords()
	{
		Boolean first = true;
		StringBuilder kw = new StringBuilder();
		for(String s: keywords)
		{
			kw.append((first ? "" : ", ") + s);
			if (first) first=false;
		}
		return kw.toString();
	}
	
	public void setKeywords(String ... kw)
	{
		keywords.clear();
		for(String s: kw) keywords.add(s);
	}
	
	public void addKeywords(String ... kw)
	{
		if(keywords.contains("none")) keywords.clear();
		for(String s: kw) keywords.add(s);
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
