package cabinet;

import java.util.*;

public class Article
	extends Text
{	
	private 	String		journal;	// academic journal through which the article was published
	private		Integer		jrnlVol, jrnlEd, jrnlPg; // specific academic journal info for this article
	private		Set<String>	contributors;	// contributors to the writing or research
	private		Set<String>	keywords;	// academic keywords used in this article
	
	private static final String	nocontrb = "nobody"; // used when there are no contributors
	private static final String nokeywrd = "none"; // used when there are no keywords
	
	
	public Article()
	{
		super();
		
		journal = "";
		setJrnlVol(1);
		setJrnlEd(1);
		setJrnlPg(1);
		
		contributors = new TreeSet<>();
		contributors.add("none");
		
		keywords = new TreeSet<>();
		keywords.add(nokeywrd);
	}
	
	public Article(String tit, String mkr, String pbc, String jrnl, Integer jv, Integer je, Integer jp, Integer yr, String ... tgs)
	{
		super(tit, mkr, pbc, yr, tgs);
		
		journal = jrnl;
		setJrnlVol(jv);
		setJrnlEd(je);
		setJrnlPg(jp);
		
		contributors = new TreeSet<>();
		contributors.add(nocontrb);
		
		keywords = new TreeSet<>();
		keywords.add(nokeywrd);
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/* Journal Methods */
	public String getJournal()
	{
		return journal;
	}
	
	public void setJournal(String journal)
	{
		this.journal = journal;
	}
	
	public Integer getJrnlPg() {
		return jrnlPg;
	}

	public void setJrnlPg(Integer jrnlPg) {
		this.jrnlPg = jrnlPg;
	}

	public Integer getJrnlVol() {
		return jrnlVol;
	}

	public void setJrnlVol(Integer jrnlVol) {
		this.jrnlVol = jrnlVol;
	}

	public Integer getJrnlEd() {
		return jrnlEd;
	}

	public void setJrnlEd(Integer jrnlEd) {
		this.jrnlEd = jrnlEd;
	}

	/* Contributors Methods */
	// Returns the contributors as a set of strings
	public Set<String> getContribSet()
	{
		return contributors;
	}
	
	// Returns an item's contributors as a single string, separated by
	// commas.
	public String getContributors()
	{
		Boolean first = true;
		StringBuilder contrib = new StringBuilder();
		for(String s: contributors)
		{
			contrib.append((first ? "" : ", ") + s);
			if (first) first=false;
		}
		return contrib.toString();
	}
	
	// Clears existing contributors and then adds new ones.
	public void setContributors(String... contrib)
	{
		contributors.clear();
		for(String s: contrib) contributors.add(s + " ");
	}
	
	// Adds existing contributors
	public void addContributors(String... contrib)
	{
		if(contributors.contains(nocontrb)) contributors.clear();
		for(String s: contrib) contributors.add(s);
	}
	
	public Boolean rmvContributor(String contrib)
	{
		Boolean wasThere = contributors.remove(contrib);
		if (contributors.isEmpty()) contributors.add(nocontrb);
		return wasThere;
	}
	
	/* Keywords Methods */
	// Returns the contributors as a set of strings
	public Set<String> getKWSet()
	{
		return keywords;
	}
	
	// Returns an item's contributors as a single string, separated by
	// commas.
	public String getKeywords()
	{
		Boolean first = true;
		StringBuilder keywrd = new StringBuilder();
		for(String s: keywords)
		{
			keywrd.append((first ? "" : ", ") + s);
			if (first) first=false;
		}
		return keywrd.toString();
	}
	
	// Clears existing contributors and then adds new ones.
	public void setKeywords(String... keywrd)
	{
		keywords.clear();
		for(String s: keywrd) keywords.add(s + " ");
	}
	
	// Adds existing contributors
	public void addKeywords(String... keywrd)
	{
		if(keywords.contains(nokeywrd)) keywords.clear();
		for(String s: keywrd) keywords.add(s);
	}

	public Boolean rmvKeyword(String keywrd)
	{
		Boolean wasThere = keywords.remove(keywrd);
		if (keywords.isEmpty()) keywords.add(nokeywrd);
		return wasThere;
	}
}
