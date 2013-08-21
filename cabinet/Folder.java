package cabinet;

import java.util.*;

public class Folder
{
	private	String					label;
	private	List<Item>				contents;
	private	List<Folder>			subfolders;
	private Map<String, List<Item>>	kwmap;
	

	// New folder
	public Folder()
	{
		label		= "";
		contents	= new ArrayList<>();
		subfolders	= new ArrayList<>(); 
	}
	// New folder with predefined label
	public Folder(String label)
	{
		this.label	= label;
		contents	= new ArrayList<>();
		subfolders	= new ArrayList<>();
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
	
	public List<Folder> getSubfolders()
	{
		return subfolders;
	}

}
