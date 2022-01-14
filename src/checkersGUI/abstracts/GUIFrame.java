package checkersGUI.abstracts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import checkersGUI.Enums;
import checkersGUI.Enums.Sizes;

/**
 * This custom JFrame object is used to define the Game window JFrame
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
@SuppressWarnings("serial")
public abstract class GUIFrame extends JFrame {
	
	public GUIFrame() {
		ImageIcon icon = new ImageIcon("./icons/checkersIcon.png");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Checkers!");
		this.setIconImage(icon.getImage());
		this.getContentPane().setBackground(Color.GRAY);// remember a frame is made of several hidden layers (think back to java
														// doc on oracle website with the wee diagram with glass pane at front;
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(Sizes.getFrameWidth(), Sizes.getFrameHeight()));

	}
}
