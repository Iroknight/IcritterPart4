/* Name: Todd Wickizer and Andrew Rickus
 * Section Leader: Jeremy Mowery and Hasanain Jamal
 * Assignment: Assignment 4: iCritter, part 2
 */

package model;

public class NotEnoughCreditsException extends RuntimeException{
	
	public NotEnoughCreditsException(int xx, int yy){
		super("Attempted to remove " + xx + " credits, but only had " + yy + " credits.");
	}

}
