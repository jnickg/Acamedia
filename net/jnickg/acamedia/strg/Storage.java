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
	
	private Map<String, Set<Item>>	uuidMap; // Allows key collisions by storing Items in a Set
	private Map<String, Set<Item>>	titleMap;
	
	/* Constructors */
	public Storage(File parent, String fldr)
	{
		super(parent, fldr);
		if(!this.exists()) this.mkdir();

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
	}
	
	protected void saveAllSubs(PrintStream out)
	{
		this.saveAll(out);
		
		// invoke save method for each folder
		for(Folder f: folders)
		{
			f.saveAll(out);
		}
	}
	
	public void autoDetect(PrintStream out)
	{
		File[] guts = this.listFiles();
		for(File f: guts)
		{
			if(f.isDirectory()) addSubfolder(f.getName());
			else if(f.isFile()) addItem(f);
			
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
		Folder nf = new Folder(this.getAbsoluteFile(), lbl);
		
		// Add new folder to appropriate maps
		folders.add(nf);
		
		return nf;
	}
	
	
	
	
	/* New Item Methods */
	public Item addItem(File f)
	{
		try
		{
			Item new1;
			//TODO test if this works
			String ext = Files.probeContentType(f.toPath());
			if(ext.equalsIgnoreCase("PDF"))
					new1 = addPdfItem(f);
			else
					new1 = null;
			return new1;
				
		}
		catch(IOException e)
		{
			e.getMessage();
			e.getStackTrace();
			return null;
		}
	}
	
	private Item addPdfItem(File f)
	{
		// Create the basic Text
		Item new1 = new PdfItem(f, f.getName());
		
		
		// Add to master list(s)
		contents.add(new1);
		
		// Add to other maps
		addToSetMap(uuidMap, new1, new1.getUUID());
		addToSetMap(titleMap, new1, f.getName());
		
		// Return reference to new Text
		return new1;
	}
}
