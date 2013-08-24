package cabinet;

import java.util.*;

public abstract class Item
	implements Comparable<Item>
{
	/* Variable Members */
	private		String		title;
	private		Set<String> keywords;
	
	private		String		filepath;

	/* Constructors */
	Item()
	{
		title = "";
		
		keywords = new TreeSet<>();
		keywords.add("none");
		
		filepath = "";
	}
	
	Item(String tit, String ... kw)
	{
		title = tit;
		
		keywords = new TreeSet<>();
		for(String s: kw) keywords.add(s);
		
		filepath = "";
	}
	
	/* General Methods */
	
	// All items must know how to print themselves
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
