package cabinet;

import java.io.File;
import java.util.*;

public abstract class Storage
		implements Comparable<Storage>
{
	private	String					label;
	private final File				location;
	private	List<Item>				contents;
	private	Set<Folder>				folders;
	//private Map<String, List<Item>>	kwmap;
	
	
	// Constructors
	
	public Storage(File loc, String lbl)
	{
		label = lbl;
		location = new File(loc, lbl);
		contents = new ArrayList<>();
		folders = new TreeSet<>();
	}
	
	
	
	// General Methods
	
	public abstract int compareTo(Storage other);
	
//	private void addToMap(Map<String, Set<Item>> disMap, Item disValue, String... disKey)
//	{
//		for(String k: disKey)
//		{
//			if(!(disMap.containsKey(k)))
//			{
//				Set<Item> temp = new TreeSet<>();
//				temp.add(disValue);
//				disMap.put(k, temp);
//			}
//			else
//			{
//				Set<Item> temp = disMap.get(k);
//				temp.add(disValue);
//				disMap.put(k, temp);
//			}
//		}
//	}
	
	
	
	// Contents methods
	
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
	
	public List<Item> getContents()
	{
		return contents;
	}
	
	public Collection<Item> getAllContents()
	{
		List<Item> allContents = new ArrayList<>(contents);
		if (this.hasSubfolders())
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
	
	
	
	// Label methods
	
	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	
	
	// File methods
	
	public File getLocation()
	{
		return location;
	}
	
	
	
	// Folders Methods
	
	public Boolean hasSubfolders()
	{
		if (folders.isEmpty()) return true;
		else return false;
	}
	public Collection<Folder> getSubfolders()
	{
		return folders;
	}
	public Folder newSubfolder(String lbl)
	{
		Folder nf = new Folder(location, lbl);
		folders.add(nf);
		return nf;
	}
	
	
	
	// Sorting Methods
	
	public void sortContentsByTitle()
	{
		
	}

}
