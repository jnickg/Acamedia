package cabinet;

public class Text
		extends Item
{
	/* Variable Members */
	private		String		publisher;
	private		Integer		year;
	
	private		String		filepath;
	
	/* Constructors */
	Text()
	{
		super();
		publisher = "";
		year = 0;
		
		filepath = "";
	}
	
	Text(String tit, String mkr, Integer yr, String ... kw)
	{
		super(tit, kw);
		publisher = mkr;
		year = yr;
		
		filepath = "";
	}
	
	//TODO Add constructor for existing filepath
	
	/* General Methods */
	
	// Implementation of the Comparable interface is required to sort
	// items correctly, based on title, year, and then publisher
//	@Override
//	public int compareTo(Text other)
//	{
//		int title  = this.getTitle().compareTo(other.getTitle());
//		if (title==0)
//		{
//			int yr = this.getYear().compareTo(other.getYear());
//			if (yr==0)
//			{
//				return this.getPublisher().compareTo(other.getPublisher());
//				
//			}
//			else return yr;
//		}
//		else return title;
//	}
	
	@Override
	public String toString()
	{
		//TODO
		return null;
	}

	/* Publisher Methods */
	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	
	/* Year Methods */
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year)
	{
		this.year = year;
	}
	
	/* Filepath Methods */
	public String getFilepath()
	{
		return filepath;
	}
	
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}

}
