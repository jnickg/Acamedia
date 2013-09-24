package net.jnickg.acamedia.strg;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

import net.jnickg.acamedia.fil.Article;
import net.jnickg.acamedia.fil.Item;
import net.jnickg.acamedia.fil.Text;

public abstract class Storage
		implements Comparable<Storage>
{
	private	String					label; // Used both to name the storage and specify its location
	private	File					location; // The location of the storage
	private	Set<Item>				contents; // Items held directly inside the storage
	private	Set<Folder>				folders; // All labels must be unique
	
	
	private Map<String, Set<Item>>	uuidMap; // Allows key collisions by storing Items in a Set
	private Map<String, Set<Item>>	titleMap;
	//private Map<String, Set<Item>>	tagMap;
	
	
	/* Constructors */
	public Storage(File loc, String lbl)
	{
		label = lbl;
		
		location = new File(loc, lbl);
		if(!location.exists()) location.mkdir();
		
		contents = new TreeSet<>();
		
		folders = new TreeSet<>();
		
		
		uuidMap = new HashMap<>();
		
		titleMap = new HashMap<>();
	}
	
	/* General methods */
	protected void saveAll(PrintStream out)
	{
		// Save own-level contents
		for(Item i: contents)
		{
			i.saveFile(out);
		}
		
		// invoke save method for each folder
		for(Folder f: folders)
		{
			f.saveAll(out);
		}
		
	}
	
	public void autoDetectSubs(PrintStream out)
	{
		for(File f: location.listFiles())
		{
			if (f.isDirectory())
			{
				newSubfolder(f.getName()).autoDetectSubs(out);;
			}
			else if (f.isFile())
			{
				newItem(f);
			}
		}
	}
	
	public int compareTo(Storage other)
	{
		return this.getLabel().compareTo(other.getLabel());
	}
	
	public String toString()
	{
		return label;
	}
	
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
	
	public void printAll(PrintStream out)
	{
		printAll(out, "");
	}
	
	protected void printAll(PrintStream out, String indent)
	{
		out.println(indent + label + "\\");
		if (this.hasContents())
		{
			out.println(indent + "  [Contents:]");
			for(Item i: this.getContents())
				out.println(indent + "    " + i.toString());
		}
		if (this.hasSubfolders())
		{
			out.println(indent + "  [Directories:]");
			for(Folder f: this.getSubfolders())
			{
				//out.println(indent + "    " + f.toString() + "\\");
				f.printAll(out, indent + "    ");
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
	
	public Collection<Item> contentsForTitle(String title)
	{
		return titleMap.get(title);
//		if(titleMap.containsKey(title)) return titleMap.get(title);
//		else return null;
	}
	
	public Collection<Item> allContentsForTitle(String title)
	{
		List<Item> rtn = new ArrayList<>();
		
		// Add all for title on this level
		Collection<Item> toadd = contentsForTitle(title);
		if (!(toadd==null)) rtn.addAll(toadd);
		
		// If there are deeper levels, add all for those levels
		if (this.hasSubfolders())
		{
			for(Folder f: this.getSubfolders())
			{
				rtn.addAll(f.allContentsForTitle(title));
			}
		}
		
		return rtn;
	}
	
	public Boolean hasContents()
	{
		if (contents.isEmpty()) return false;
		else return true;
	}
	
	public Set<Item> getContents()
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
		if (folders.isEmpty()) return false;
		else return true;
	}
	
	public Collection<Folder> getSubfolders()
	{
		return folders;
	}
	
	public Folder newSubfolder(String lbl)
	{	
		// Create new folder with given properties
		Folder nf = new Folder(location, lbl);
		
		// Add new folder to appropriate maps
		folders.add(nf);
		
		return nf;
	}
	
	
	
	// New Item Methods
	public Item newItem(File f)
	{
		//TODO Detect item type based on file metadata
		
		//TODO Create appropriate item
		Item new1 = newText(f.getName(), f, "publisher", "publisher city", 2013, "n/a", "1337");
		
		return new1;
	}
	
	private Item newText(String tit, File loc, String pub, String pbc,
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
	
	private Item newArticle(String tit, File loc, String pub, String pbc,
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
