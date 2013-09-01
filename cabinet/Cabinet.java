package cabinet;

import java.io.File;

public class Cabinet
		extends Storage
{
	
	public Cabinet(File loc, String lbl) {
		super(loc, lbl);
	}

	@Override
	public int compareTo(Storage other)
	{
		return this.getLabel().compareTo(other.getLabel());
	}

}
