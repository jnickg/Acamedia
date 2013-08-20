package cabinet;

import java.util.*;

public abstract class Item
		implements Comparable<Item>{
/* Variable Members */
	public		String		title;
	public		String		maker;
	public		Set<String>	contributors;
	public		Set<String> keywords;
	
/* Constructors */
	Item() {
		title = "";
		maker = "";
		
		contributors = new TreeSet<>();
		contributors.add("nobody");
		
		keywords = new TreeSet<>();
		keywords.add("none");
	}
	
	Item(String tit, String mkr, String ... kw) {
		title = tit;
		maker = mkr;
		
		contributors = new TreeSet<>();
		contributors.add("nobody");
		
		keywords = new TreeSet<>();
		for(String s: kw) keywords.add(s);
	}
	
/* General Methods */
	
	// Implementation of the Comparable interface is required to sort
	// items correctly, based on Title first and then Maker
	@Override
	public int compareTo(Item other) {
		int title  = this.title.compareTo(other.getTitle());
		return title==0 ? this.maker.compareTo(other.getMaker()) : title;
	}
	
	// All items must know how to print themselves
	@Override
	public abstract String toString();

/* Search Methods */
	// Deprecated
	public boolean matchTit(String tit) {
		if(title.equals(tit)) return true;
		return false;
	}
	
	// Deprecated
	public boolean matchMkr(String mkr) {
		if(maker.equals(mkr)) return true;
		return false;
	}
	
	// Deprecated
	public boolean matchKw(String ... kw) {
		for(String s: kw)	if(keywords.contains(s.toLowerCase()))	return true;
		return false;
	}

/* Title Methods */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

/* Maker Methods */
	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}
	
/* Contributors Methods */
	// Returns the contributors as a set of strings
	public Set<String> getContribSet() {
		return contributors;
	}
	
	// Returns an item's contributors as a single string, separated by
	// commas.
	public String getContributors() {
		// Add commas
		StringBuilder contrib = new StringBuilder();
		for(String s: contributors) contrib.append(s + ", ");
		String rtn = contrib.toString();
		// Trim extra commas
		if(rtn.endsWith(", "));
		rtn = rtn.substring(0, rtn.lastIndexOf(", "));
		return rtn;
	}
	
	// Clears existing contributors and then adds new ones.
	public void setContributors(String... actors) {
		contributors.clear();
		for(String s: actors) contributors.add(s + " ");
	}
	
	// Adds existing contributors
	public void addContributors(String... actors) {
		if(contributors.contains("nobody")) contributors.clear();
		for(String s: actors) contributors.add(s);
	}

/* Keywords Methods */
	// Returns a set of Kanye Wests. As if one weren't too many.
	public Set<String>getKWset() {
		return keywords;
	}

	// Returns an item's keywords as a single string, separated by
	// commas.
	public String getKeywords() {
		StringBuilder kw = new StringBuilder();
		// Add all keywords
		for(String s: keywords)	kw.append(s + ", ");
		String rtn = kw.toString();
		// Trim extra commas
		if(rtn.endsWith(", "));
		rtn = rtn.substring(0, rtn.lastIndexOf(", "));
		return rtn;
	}
	
	public void setKeywords(String ... kw) {
		keywords.clear();
		for(String s: kw) keywords.add(s);
	}
	
	public void addKeywords(String ... kw) {
		if(keywords.contains("none")) keywords.clear();
		for(String s: kw) keywords.add(s);
	}

}
