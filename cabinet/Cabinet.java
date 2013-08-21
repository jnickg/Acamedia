package cabinet;

import java.io.*;
import java.util.*;


public class Cabinet
{
	private	List<Item>				unsorted;
	private	Set<Folder>				folders;
	private Map<String, List<Item>>	kwmap;
	
	// Constructors
	public Cabinet()
	{
		unsorted	= new ArrayList<>();
		folders		= new TreeSet<>();
		kwmap		= new HashMap<>();
	}
	
	// General Methods
	public Collection<Item> allItemsForKeyword(String keyword)
	{
		// TODO Auto-generated constructor stub
		return null;
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
}
