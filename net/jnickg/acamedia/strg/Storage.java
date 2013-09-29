package net.jnickg.acamedia.strg;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.*;

import net.jnickg.acamedia.fil.*;

public abstract class Storage
		extends File
{
	// For implementation of Serializable interface
	private static final long serialVersionUID = 1L;
	
	/* Variable Members */
	private	Set<Item>				contents; // Items held directly inside the storage
	private	Set<Folder>				folders; // All labels must be unique
	
	private Map<String, Set<Item>>	titleMap;
	private Map<String, Set<Item>>	metadataMap;
	
	/* Constructors */
	public Storage(File parent, String fldr)
	{
		// Make the file and 
		super(parent, fldr);
		if(!this.exists()) this.mkdir();

		// Initialize guts
		contents = new TreeSet<>();
		folders = new TreeSet<>();
		
		// Initialize maps
		titleMap = new HashMap<>();
		metadataMap = new HashMap<>();
	}
	
	
	
	
	/* General methods */
	protected void saveAll(PrintStream out)
	{
		// Save own-level contents
		for(Item i: contents)
		{
			i.saveFile(out);
		}
	}
	
	protected void saveAllSubs(PrintStream out)
	{
		this.saveAll(out);
		
		// invoke save method for each folder
		for(Folder f: folders)
		{
			f.saveAllSubs(out);
		}
	}
	
	public void autoDetect(PrintStream out)
	{
		File[] guts = this.listFiles();
		for(File f: guts)
		{
			if(f.isDirectory()) addSubfolder(f.getName());
			else if(f.isFile()) addItem(f, out);
			
		}
	}
	
	public void autoDetectSubs(PrintStream out)
	{
		this.autoDetect(out);
		for(Folder f: folders)
		{
			f.autoDetectSubs(out);
		}
	}
	
	public int compareTo(Storage other)
	{
		return this.getName().compareTo(other.getName());
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
	
	private void addToSetMap(Map<String, Set<Item>> disMap, Item disValue, Map<String, String> disKey)
	{
		for(String k: disKey.values())
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
		out.println(indent + this.getName() + "\\");
		if (this.hasContents())
		{
			out.println(indent + "  [Contents:]");
			for(Item i: this.getContents())
				out.println(indent + "    " + i.print());
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
	
	
	
	
	/* Contents methods */
	//TODO Revamp the contents methods
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
	
	
	
	
	/* Folders methods */
	public Boolean hasSubfolders()
	{
		if (folders.isEmpty()) return false;
		else return true;
	}
	
	public Collection<Folder> getSubfolders()
	{
		return folders;
	}
	
	public Folder addSubfolder(String lbl)
	{	
		// Create new folder with given properties
		Folder nf = new Folder(this, lbl);
		
		// Add new folder to appropriate maps
		folders.add(nf);
		
		return nf;
	}
	
	
	
	
	/* New Item Methods */
	public Item addItem(File f, PrintStream out)
	{
		out.println("\nAttempting to add new item: " + f.getName());
		Item new1;
		try
		{
			String ext = Files.probeContentType(f.toPath());
			ItemType it = ItemType.matchType(ext); 
			out.println("filetype of " + f.getName() + ":\n\t\"" + ext + "\" aka \"" + it + "\"");
			new1 = it.makeInstance(f);
			contents.add(new1);
			
			//TODO use new1 to employ addToSetMap method, to add new1 to maps
			addToSetMap(titleMap, new1, new1.getName());
			addToSetMap(metadataMap, new1, new1.getMetadata());
		}
		// Files.probeContentType failed due to
		// an I/O error
		catch(IOException e)
		{
			out.println(e.getMessage());
			out.println(e.getStackTrace());
			new1 = null;
		}
		// The ItemType.matchType failed because
		// the filetype is not yet supported.
		catch(IllegalArgumentException e)
		{
			out.println(e.getMessage());
			out.println(e.getStackTrace());
			new1 = null;			
		}
		return new1;
	}
	
	public void initContents(PrintStream out)
	{
		for(Item i: contents)
		{
			i.pullMetadata(out);
		}
	}
}
