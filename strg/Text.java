package strg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.pdf.PdfReader;

public class Text
		extends Item
{
	/* Variable Members */
	private		PdfReader	pdf;
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
	

	/* PDF Methods */
//	public PdfReader pullPdf(File f)
//	{
//		PdfReader pdef;
//		try
//		{
//			pdef = new PdfReader(this.getFile().toString());
//		}
//		catch(IOException e)
//		{
//			pdef = null;
//		}
//		return pdef;
//	}
//	
//	public void pullPdfMetadata(Map<String, String> nfo)
//	{
//		if(nfo.containsKey("Publisher")) this.publisher = nfo.get("Publisher");
//		else this.publisher = "unknown";
//		
//		if(nfo.containsKey("Publisher City")) this.publisherCity = nfo.get("Publisher City");
//		else this.publisherCity = "unknown";
//		
//		if(nfo.containsKey("ISBN")) this.isbn = nfo.get("ISBN");
//		else this.isbn = "unknown";
//		
//		if(nfo.containsKey("Year")) this.year = Integer.parseInt(nfo.get("Year"));
//		else this.year = 0;
//	}
}
