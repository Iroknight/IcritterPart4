/* Name: Todd Wickizer and Andrew Rickus
 * Section Leader: Jeremy Mowery and Hasanain Jamal
 * Assignment: Assignment 4: iCritter, part 2
 */

package model;

/*
 * Defines a Slug ICritter.
 * 
 * @author Andy
 *
 */

public class ICritterSlug extends LandICritter {

	public ICritterSlug(String theName, Owner theOwner) {
		super(theName, theOwner);
		// Auto-generated constructor stub
	}

	public void interact(ICritter other) {

		try {
			interestCorrelation(other);
		} catch (NoInterestsException e) {
			System.out.println(e);
			addFriend(other);
		}
	}

}
