package cabinet;

import java.util.*;

public class Article
	extends Text
{	
	private 	String		journal;
	private		Set<String>	contributors;
	
	public Article()
	{
		super();
		
		journal = "";
		
		contributors = new TreeSet<>();
		contributors.add("none");
	}
	
	public Article(String tit, String mkr, String jrnl, Integer yr, String ... kw)
	{
		super(tit, mkr, yr, kw);
		
		journal = jrnl;
		
		contributors = new TreeSet<>();
		contributors.add("none");
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
		if(contributors.contains("none")) contributors.clear();
		for(String s: contrib) contributors.add(s);
	}

}
