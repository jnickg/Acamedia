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
	
	private Map<String, Set<Item>>	uuidMap; // Allows key collisions by storing Items in a Set
	private Map<String, Set<Item>>	titleMap;
	private Map<String, Set<Item>>	tagMap;
	
	
	// Constructors
	
	public Storage(File loc, String lbl)
	{
		label = lbl;
		location = new File(loc, lbl);
		contents = new ArrayList<>();
		folders = new TreeSet<>();
		
		uuidMap = new HashMap<>();
		titleMap = new HashMap<>();
	}
	
	
	
	// General Methods
	
	public abstract int compareTo(Storage other);
	
	private void addToSetMap(Map<String, Set<Item>> disMap, Item disValue, String... disKey)
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
	
	
	
	// New Item Methods
	
	public Item newText(String tit, File loc, String pub, String pbc,
			Integer yr,	String typ,	String isbn)
	{
		// Create the basic Text
		Text new1 = new Text(tit, loc);
		
		// Add other data
		new1.setType(typ);
		new1.setISBN(isbn);
		new1.setPublisher(pub, pbc);
		new1.setYear(yr);
		
		// Add to master list(s)
		contents.add(new1);
		
		// Add to other maps
		addToSetMap(uuidMap, new1, new1.getUUID());
		addToSetMap(titleMap, new1, tit);
		
		// Return reference to new Text
		return new1;
	}
	
	public Item newArticle(String tit, File loc, String pub, String pbc,
			Integer yr,	String typ,	String isbn, String ... cntrb)
	{
		Article new1 = new Article(tit, loc);
		
		// Add other data
		new1.setType(typ);
		new1.setISBN(isbn);
		new1.setContributors(cntrb);
		new1.setPublisher(pub, pbc);
		new1.setYear(yr);
		
		// Add to master list(s)
		contents.add(new1);
		
		// Add to other maps
		addToSetMap(uuidMap, new1, new1.getUUID());
		addToSetMap(titleMap, new1, tit);
		
		// Return reference to new Text
		return new1;
	}
	
	
	
	// Sorting Methods
	
	public void sortContentsByTitle()
	{
		
	}

}
