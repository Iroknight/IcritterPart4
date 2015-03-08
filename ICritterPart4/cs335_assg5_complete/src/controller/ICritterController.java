package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Owner;
import view.ICritterView;

/**
 * This is the class that starts the whole ICritter application.
 * This class extends JFrame then adds an instance of ICritterView to itself to display the UI
 * 
 * @author John McGowan
 */
public class ICritterController extends JFrame{
	
	/**
	 * <b>ICritterController</b>
	 * <p>
	 * Constructor will add an instance of ICritterView to this JFrame.
	 * Constructor will also create an owner with my name and with an ICritter name that was passed into the constructor.
	 * 
	 * @author John McGowan
	 */
	public ICritterController(String iCritterName){
		Owner theOwner = new Owner("John McGowan",iCritterName);
		setLayout(new BorderLayout());//set the layout manager
		add(new ICritterView(theOwner),BorderLayout.CENTER);//add a new instance ot ICritterView to the JFrame
		
		//setup the JMenu
		JMenuBar theMenuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");//creat the file menu on our menu bar
		JMenuItem aboutItem = new JMenuItem("About");//creat our about menu item
		aboutItem.addActionListener(new AboutActionListener());//action listener that will display our "about" dialog
		fileMenu.add(aboutItem);
		
		JMenuItem quitItem = new JMenuItem("Quit");//item that will quit the program
		quitItem.addActionListener(new QuitActionListener());//action listener to quit the application
		fileMenu.add(quitItem);
		
		theMenuBar.add(fileMenu);
		this.setJMenuBar(theMenuBar);
		
		this.setTitle("CSc 335 Assignment 5");
		
		this.pack();//pack everything
		setVisible(true);//display this JFrame
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//kill the application when the window is closed
	}
	
	/**
	 * Action listener to show the about window
	 * 
	 * @author John McGowan
	 */
	private class AboutActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, new JLabel("My name is John McGowan, I am creating this program as a base code for the students in CSc 335 because I love computer science and I love section leading :)"));
		}
	}
	
	/**
	 * Action listener to quit the application
	 * 
	 * @author John McGowan
	 */
	private class QuitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);//exit the program
		}
		
	}
	
	/**
	 * Main method will create a new ICritterController object
	 * 
	 * @author John McGowan
	 */
	public static void main(String args[]){
		new ICritterController("John's awesome tuxedo penguin");//create our new ICritterController
	}
}
