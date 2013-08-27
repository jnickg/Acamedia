package cabinet;

import java.io.*;
import java.util.*;


public class Cabinet
{
	private	List<Item>				contents;
	private	Set<Folder>				folders;
	private Map<String, List<Item>>	kwmap;
	
	// Constructors
	public Cabinet()
	{
		contents	= new ArrayList<>();
		folders		= new TreeSet<>();
		kwmap		= new HashMap<>();
	}
	
	// General Methods
	public Collection<Item> contentsForKeyword(String keyword)
	{
		// TODO return items in this level only, which fit the kw
		return null;
	}
	
	public Collection<Item> allContentsForKeyword(String keyword)
	{
		// TODO return all items in Cabinet which fit the kw
		return null;
	}
	
	public Collection<Item> getAllContents()
	{
		List<Item> allContents = new ArrayList<>(contents);
		if (this.hasFolders())
			for (Folder f: folders)
			{
				Collection<Item> subcontents = f.getAllContents();
				for(Item i: subcontents)
				{
					allContents.add(i);
				}
			}
		return allContents;
	}
	
	public void printItem(PrintStream out, Item item)
	{
		// TODO Auto-generated constructor stub
	}

	private void addToMap(Map<String, Set<Item>> disMap, Item disValue, String... disKey)
	{
		for(String k: disKey)
		{
			if(!(disMap.containsKey(k)))
			{
				Set<Item> temp = new TreeSet<>();
				temp.add(disValue);
				disMap.put(k, temp);
			}
			else
			{
				Set<Item> temp = disMap.get(k);
				temp.add(disValue);
				disMap.put(k, temp);
			}
		}
	}
	
	// Folders Methods
	public Boolean hasFolders()
	{
		if (folders.isEmpty()) return true;
		else return false;
	}
	
	
	// Sorting Methods
	public void sortContentsByTitle()
	{
		
	}
}
