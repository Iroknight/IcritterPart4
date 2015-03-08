package model;

public class NotEnoughCreditsException extends RuntimeException{
	
	public NotEnoughCreditsException(int xx, int yy){
		super("Attempted to remove " + xx + " credits, but only had " + yy + " credits.");
	}

}
