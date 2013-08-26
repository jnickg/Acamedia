package cabinet;

import java.util.*;

public class Folder
	implements Comparable<Folder>
{
	private	String					label;
	private	List<Item>				contents;
	private	Set<Folder>				subfolders;
	private Map<String, List<Item>>	kwmap;
	

	// New folder
	public Folder()
	{
		label		= "";
		contents	= new ArrayList<>();
		subfolders	= new TreeSet<>(); 
	}
	// New folder with predefined label
	public Folder(String label)
	{
		this.label	= label;
		contents	= new ArrayList<>();
		subfolders	= new TreeSet<>();
	}
	
	// General methods
	public int compareTo(Folder other)
	{
		return this.getLabel().compareTo(other.getLabel());
	}
	
	public Collection<Item> getAllContents()
	{
		List<Item> allContents = new ArrayList<>(contents);
		if (this.hasSubfolders())
			for (Folder f: subfolders)
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
	
	// Contents operations
	public void addContents(Item ... newItems)
	{
		for(Item i: newItems) contents.add(i);
	}
	
	public List<Item> getContents()
	{
		return contents;
	}
	
	// Subfolders operations
	public Boolean hasSubfolders()
	{
		if (subfolders.isEmpty()) return true;
		else return false;
	}
	
	public Collection<Folder> getSubfolders()
	{
		return subfolders;
	}

}
