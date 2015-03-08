package model;

import java.util.Set;
import java.util.TreeSet;

/**
 * A collection of keywords that can be correlated to other keywordcollections to
 * determine how close of a match they are.
 * 
 * @author John McGowan
 */
public class KeywordCollection {
	private Set<String> keywords;
	
	/**
	 * <b>KeywordCollection</b>
	 * <p>
	 * Creates a new keywordcollection class and initializes the set of keywords.
	 * 
	 * @author John McGowan
	 */
	public KeywordCollection(){
		keywords = new TreeSet<String>();
	}
	
	/**
	 * <b>listKeywords</b>
	 * <p>
	 * Returns a set of the keywords
	 * 
	 * @return Set<String>
	 * 
	 * @author John McGowan
	 */
	public Set<String> listKeywords(){
		return keywords;
	}
	
	/**
	 * <b>addKeyword</b>
	 * <p>
	 * If the given keyword already exists, then return false, otherwise
	 * 		add the keyword to our set and return true.
	 * 
	 * @parm theWord	The word to add to our set.
	 * 
	 * @return boolean
	 * 
	 * @author John McGowan
	 */
	public boolean addKeyword(String theWord){
		if(keywords.contains(theWord))//do we have this keyword already?
			return false;
		else{
			keywords.add(theWord);//add the word to our set
			return true;
		}
	}
	
	/**
	 * <b>removeKeyword</b>
	 * <p>
	 * Removes the given keyword from the set.
	 * If the keyword exists, returns true; otherwise, false.
	 * 
	 * @parm theWord	The word to remove from the set
	 * 
	 * @return boolean
	 * 
	 * @author John McGowan
	 */
	public boolean removeKeyword(String theWord){
		return keywords.remove(theWord);//the remove method satisfies the requirements for this method
	}
	
	/**
	 * <b>correlation</b>
	 * <p>
	 * Calculates the correlation between two sets of keys.
	 * Determines calculation by taking the cardinality of the intersetion of both sets and dividing it by
	 * the cardinality of the union of both sets.
	 * 
	 * @parm oColl	The other KeywordCollection
	 * 
	 * @return double
	 * 
	 * @author John McGowan
	 */
	public double correlation(KeywordCollection oColl){
		//do the calculation and return it as a double
		double intersectSize = intersection(oColl).size();
		double unionSize = union(oColl).size();
		if(unionSize >0)
			return intersectSize/unionSize;
		else
			throw new NoInterestsException(); // Neither ICritters have interests.
			// (Commented this out because we have an exception to handle it nowreturn 1;//if neither Critter has any interests, then they share all interests
	}
	
	
	
	/**
	 * <b>union</b>
	 * <p>
	 * Takes another KeywordCollection and returns a Set that is a union of the two.
	 * 
	 * @parm oColl	The other KeywordCollection
	 * 
	 * @return Set<String>
	 * 
	 * @author John McGowan
	 */
	private Set<String> union(KeywordCollection oColl){
		Set<String> output = new TreeSet<String>();
		
		for(String i: oColl.keywords)//add all elements of oColl's keywords to output
			output.add(i);
		//add all the elements of keywords to the output, anything already there will not be duplicated because a Set does not duplicate keys
		for(String i:keywords)
			output.add(i);
		
		return output;
	}
	
	/**
	 * <b>intersection</b>
	 * <p>
	 * Returns the intersection of this set of keywords and the provided KeywordCollection's set of keywords
	 * 
	 * @parm oColl	The other KeywordCollection
	 * 
	 * @return Set<String>
	 * 
	 * @author John McGowan
	 */
	private Set<String> intersection(KeywordCollection oColl){
		Set<String> output = new TreeSet<String>();
		
		for(String i:keywords)//add all keywords that exist in both collections
			if(oColl.keywords.contains(i))
				output.add(i);
		return output;
	}
}
