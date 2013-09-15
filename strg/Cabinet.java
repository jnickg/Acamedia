package strg;

import java.io.File;
import java.io.PrintStream;

public class Cabinet
		extends Storage
{
	
	public Cabinet(File loc, String lbl, PrintStream out)
	{
		super(loc, lbl);
		
		File l = this.getLocation();
		if (l.isDirectory())
		{
			for(File f: l.listFiles())
			{
				if (f.isDirectory())
				{
					try	
					{
						newSubfolder(f.getName(), out);
					}
					catch(Exception e)
					{
						out.println(e.getMessage());
					}
				}
				else if (f.isFile())
				{
					newText(f.getName(), f, "publisher", "publisher city", 2013, "n/a", "1337");
				}
				
			}
		}
	}

	@Override
	public int compareTo(Storage other)
	{
		return this.getLabel().compareTo(other.getLabel());
	}

}
