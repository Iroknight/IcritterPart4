package view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ICritter;
import model.Owner;

/**
 * This is the panel that will contain all components to display information about the critters.
 * 
 * @author John McGowan
 */
public class ICritterView extends JPanel implements Observer{
	
	OwnerInfoPanel ownerPanel;//panel for the Owner info
	ICritterInfoPanel iCritterPanel;//panel for the ICritter info
	JLabel namesLabel, imageLabel;//labes for the Owner's names and ICritter's name as well as the ICritter's image
	
	/**
	 * <b>ICritterView</b>
	 * <p>
	 * Will create a new ICritterView object and setup this JPanel with all the components needed
	 * in order to give the user the information they need.
	 */
	public ICritterView(Owner theOwner){
		theOwner.addObserver(this);//adds this class as the observer of Owner
		theOwner.getCritter().addObserver(this);//adds this class as the observer of the ICritter
		this.setLayout(new BorderLayout());//setup our layout manager
		
		//setup owner panel
		ownerPanel = new OwnerInfoPanel(theOwner);
		add(ownerPanel,BorderLayout.SOUTH);//add our ownerPanel to the south
		
		//setup ICritter panel
		iCritterPanel = new ICritterInfoPanel(theOwner.getCritter());
		add(iCritterPanel,BorderLayout.EAST);
		
		//setup imageLabel
		ImageIcon icon = new ImageIcon("images/funny-pictures-penguins-wonder-about-their-offspring.jpg");
		imageLabel = new JLabel("",icon,JLabel.CENTER);
		add(imageLabel,BorderLayout.CENTER);
		
		//setup names label
		namesLabel = new JLabel("ICritter: "+theOwner.getCritter().getName()+" Owner: "+theOwner.getName());
		add(namesLabel,BorderLayout.NORTH);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ICritterUpdate theUpdate = (ICritterUpdate)arg1;//cast the parameter to an ICritterUpdate
		if(theUpdate.getUpdateType()==theUpdate.UPDATE_ICRITTER)
			updateICritterComponents((ICritter)arg0);//arg0 is the ICritter that was being observed
		else if(theUpdate.getUpdateType()==theUpdate.UPDATE_OWNER)
			updateOwnerComponents((Owner)arg0);//the owner was passed in as the observed component
	}
	
	public void updateICritterComponents(ICritter theCritter){
		iCritterPanel.updateICritter(theCritter);
	}
	
	/**
	 * <b>updateOwnerComponents</b>
	 * <p>
	 * updates the owner JPanel with new information now that the owner has been updated
	 * 
	 * @param theOwner	The Owner object that has changed
	 * 
	 * @author John McGowan
	 */
	public void updateOwnerComponents(Owner theOwner){
		ownerPanel.updateTreatList(theOwner.listTreats());
		ownerPanel.updateCredits(theOwner.getCredits());
	}
}
