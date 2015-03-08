package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;

import model.CheapTreat;
import model.FancyTreat;
import model.ICritter;
import model.ICritterMemoryEvent;

/**
 * Class will setup the JPanel for displaying information about the ICritter
 * 
 * @author John McGowan
 */
public class ICritterInfoPanel extends JPanel{
	
	JSlider happinessSlider;
	JList memoryList;
	DefaultListModel memoryListModel;
	
	/**
	 * <b>ICritterInfoPanel</b>
	 * <p>
	 * Constructor will add components to display the ICritter's happiness and most recent memories it has consumed
	 * 
	 * @param theCrit	the ICritter this JPanel represents
	 * 
	 * @author John McGowan
	 */
	public ICritterInfoPanel(ICritter theCrit){
		this.setLayout(new BorderLayout());
		
		add(new JLabel("Happiness and Memories"),BorderLayout.NORTH);//JLabel not stored because it never changes
		
		//setup our slider
		happinessSlider = new JSlider();
		happinessSlider.setMinimum(-4);
		happinessSlider.setMaximum(4);
		happinessSlider.setValue((int) theCrit.getHappiness());
		add(happinessSlider,BorderLayout.CENTER);
		
		//setup memory list
		memoryListModel = new DefaultListModel();
		setupMemories(theCrit.getMemories());
		memoryList = new JList(memoryListModel);
		memoryList.setPreferredSize(new Dimension(150,200));
		add(memoryList,BorderLayout.SOUTH);
	}
	
	/**
	 * <b>updateICritter</b>
	 * <p>
	 * Updates the information about the ICritter for this panel
	 * 
	 * @param theCrit	The ICritter to update from
	 * 
	 * @author John McGowan
	 */
	public void updateICritter(ICritter theCrit){
		happinessSlider.setValue((int)theCrit.getHappiness());
		
		setupMemories(theCrit.getMemories());
	}

	/**
	 * <b>setupMemories</b>
	 * <p>
	 * Method will add the last 8 memories of the ICritter to the memoryListModel to be displayed to the user
	 * 
	 * @param memories	a list of the memories of the ICritter
	 * 
	 * @author John McGowan
	 */
	private void setupMemories(List<ICritterMemoryEvent> memories) {
		
		int begIndex;//the beginning index for getting a sublist of the memories list
		if(memories.size() <8)
			begIndex = 0;//since there is less than 8 memories
		else
			begIndex= memories.size()-8;
		List<ICritterMemoryEvent> lastEightMemories = memories.subList(begIndex,memories.size());//get the last 8ish elements of the list
		
		//first clear the list to make sure we don't duplicate Treats
		memoryListModel.clear();
		//now add all the memories we want
		for(ICritterMemoryEvent i : lastEightMemories){
			String lineOutput="";//string that will be displayed for this treat
			if(i.getRememberedTreat() instanceof CheapTreat)
				lineOutput += "Cheap Treat";
			else if(i.getRememberedTreat() instanceof FancyTreat)
				lineOutput += "Fancy Treat";
			lineOutput += " (reaction was "+i.getRememberedReaction().getMoodModifier()+")";
			//now add the element
			memoryListModel.addElement(lineOutput);
		}
	}
}
