package cabinet;

import java.io.*;
import java.util.*;


public class Cabinet
{
	// Constructors
	public Cabinet()
	{
		// TODO Auto-generated constructor stub
	}
	
	// General Methods
	public Collection<Item> itemsForKeyword(String keyword)
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
