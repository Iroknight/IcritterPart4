package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import model.CheapTreat;
import model.FancyTreat;
import model.Owner;
import model.Treat;

/**
 * This JPanel will describe the Owner in a JPanel
 * It will use a grid layout and display the Owner's credits, list the treats the owner has,
 * have buttons to buy, both types of treats and give the treats to the ICritter.
 * 
 * @author John McGowan
 */
public class OwnerInfoPanel extends JPanel{
	
	Owner theOwner;
	DefaultListModel treatListModel;
	JLabel creditsLabel, numCreditsLabel;
	JList treatList;
	JButton giveTreatButton, buyCheapTreatButton, buyFancyTreatButton;
	
	/**
	 * <b>OwnerInfoPanel</b>
	 * <p>
	 * Will create a new OwnerInfoPanel with labels and buttons to describe the Owner and tell the owner
	 * what to do.
	 * 
	 * @param theOwner	A reference to the owner this JPanel will display
	 * 
	 * @author John McGowan
	 */
	public OwnerInfoPanel(Owner theOwner){
		this.theOwner = theOwner;
		this.setLayout(new GridLayout(3,2));//set layout to a grid layout
		creditsLabel = new JLabel("Credits:");
		this.add(creditsLabel);//add our credits label
		
		numCreditsLabel = new JLabel(""+theOwner.getCredits());//set initial value for the number of credits
		add(numCreditsLabel);//add it to this JLabel
		
		//setup our treat list
		treatListModel = new DefaultListModel();//the list model will contain all our treats for the JList to display
		treatList = new JList(treatListModel);//add the list model
		treatList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//only allow single selection
		treatList.setPreferredSize(new Dimension(200, 75));//set the preferred size for the list
		treatList.setAutoscrolls(true);//allows the list to scroll
		setupTreatList(theOwner.listTreats());//setup the list
		add(treatList);
		
		//setup the giveTreatButton
		giveTreatButton = new JButton("Give Treat");
		giveTreatButton.addActionListener(new giveTreatListener());
		add(giveTreatButton);

		//setup buyFancyTreatButton
		buyFancyTreatButton = new JButton("Buy Fancy Treat");
		buyFancyTreatButton.addActionListener(new buyTreatListener());
		add(buyFancyTreatButton);
		
		//setup buyFancyTreatButton
		buyCheapTreatButton = new JButton("Buy Cheap Treat");
		buyCheapTreatButton.addActionListener(new buyTreatListener());
		add(buyCheapTreatButton);
	}
	
	/**
	 * <b>setupTreatList</b>
	 * <p>
	 * Method will setup the JList displaying the treats the owner has.
	 * It will add the treats to the list as JLabels in the order given by the listTreats method in Owner.
	 * 
	 * @author John McGowan
	 */
	private void setupTreatList(List<Treat> theTreats){
		treatListModel.clear();//remove all the treats currently in the list to add them all again
		//Iterate over all the treats in theOwner.
		for(Treat i : theTreats){
			//add the treat to the list using it's treat type
			if(i instanceof FancyTreat)
				treatListModel.addElement("Fancy Treat");
			else if(i instanceof CheapTreat)
				treatListModel.addElement("Cheap Treat");
		}
		this.validate();
	}
	
	/**
	 * Private action listener class to buy treats, will be used to buy both a fancy treat
	 * and a cheap treat based on the originating JButton.
	 * 
	 * @author John McGowan
	 */
	private class buyTreatListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==buyCheapTreatButton){
				if(theOwner.buyCheapTreat("Cheap Treat")==null)//if we couldn't buy the treat, then let the user know
					JOptionPane.showMessageDialog(null, new JLabel("Unable to purchase Cheap Treat"));
			}
			else{//we were called by the buyFancyTreatButton
				if(theOwner.buyFancyTreat("Fancy Treat")==null)//if we couldn't buy the treat, then let the user know
					JOptionPane.showMessageDialog(null, new JLabel("Unable to purchase Fancy Treat"));
			}
		}		
	}
	
	/**
	 * <b>updateCredits</b>
	 * <p>
	 * Updates the Owner's number of credits and repaints the panel
	 * 
	 * @param numCredits	The number of credits the owner has now
	 * 
	 * @author John McGowan
	 */
	public void updateCredits(int numCredits){
		numCreditsLabel.setText(""+numCredits);
	}
	
	/**
	 * <b>updateTreatList</b>
	 * <p>
	 * Updates the treat list to represent the treats that the owner has now.
	 * 
	 * @param treatList	The list of treats from the owner
	 * 
	 * @author John McGowan
	 */
	public void updateTreatList(List<Treat> treatList){
		setupTreatList(treatList);
	}
	
	/**
	 * Private action listener class to give a treat to the Owner's ICritter.
	 * 
	 * @author John McGowan
	 */
	private class giveTreatListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//gets the treat index in the Owner's treat list to give to the Critter.
			int index = treatList.getSelectedIndex();
			if(index==-1){//nothing selected
				JOptionPane.showMessageDialog(null,new JLabel("You must select a treat to give your ICritter."));
			}
			else{
				//get the selected treat
				Treat treatToGive = theOwner.listTreats().get(index);
				//give it now.
				theOwner.giveTreat(treatToGive);
			}
		}
	}
}
