package view;

/**
 * A class that describes an ICritterUpdate.
 * This will describe where a notifyObserver command came from.
 * 
 * @author John McGowan
 */
public class ICritterUpdate {
	public static final int UPDATE_NONE=0x0;
	public static final int UPDATE_ICRITTER=0x1;
	public static final int UPDATE_OWNER=0x2;
	private int updateType;
	
	/**
	 * <b>ICritterUpdate</b>
	 * <p>
	 * creates and initializes a new ICritterUpdate object
	 * 
	 * @param updateType	The type of this update
	 * 
	 * @author John McGowan
	 */
	public ICritterUpdate(int updateType){
		if(updateType != UPDATE_OWNER && updateType != UPDATE_ICRITTER)
			this.updateType = UPDATE_NONE;
		else
			this.updateType=updateType;
	}
	
	/**
	 * <b>getUpdateType</b>
	 * <p>
	 * Returns the type of this update
	 * 
	 * @return int	The type of this update
	 * 
	 * @author John McGowan
	 */
	public int getUpdateType(){return updateType;}
}
