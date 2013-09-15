package strg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Text
		extends Item
{
	/* Variable Members */
	private		String		publisher, publisherCity;
	private		String		isbn;
	private		Integer		year;
	// TODO Add other members for citation data
	
	/* Constructors */
	Text(String tit, File loc)
	{
		super(tit, loc);
		publisher = "";
		publisherCity = "";
		year = 0;
		isbn = "";
	}
	
	Text(String tit, File loc, String mkr, String pbc, Integer yr,
			String isbn, String ... tgs)
	{
		super(tit, loc, tgs);
		publisher = mkr;
		publisherCity = pbc;
		year = yr;
		this.isbn = isbn;
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
		return this.getTitle();
	}
	
	@Override
	public List<String> getCitation()
	{
		List<String> cit = new ArrayList<>();
		cit.add(this.getTitle());
		cit.add(this.getYear().toString());
		cit.add(this.getPublisher());
		cit.add(this.getPubCity());
		return cit;
	}

	/* Publisher Methods */
	public String getPublisher()
	{
		return publisher;
	}
	
	public String getPubCity()
	{
		return publisherCity;
	}

	public void setPublisher(String publisher, String publisherCity)
	{
		this.publisher = publisher;
		this.publisherCity = publisherCity;
	}
	
	/* Year Methods */
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year)
	{
		this.year = year;
	}
	
	/* ISBN Methods */
	public String getISBN()
	{
		return isbn;
	}
	public void setISBN(String isbn)
	{
		this.isbn = isbn;
	}
	

}
