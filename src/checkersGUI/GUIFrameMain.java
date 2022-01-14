package checkersGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import checkersGUI.Enums.Sizes;
import checkersGUI.abstracts.GUIFrame;
import checkersGUI.abstracts.GUIPanelMain;

/**
 * This custom JFrame object is used to define the Game window JFrame
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
@SuppressWarnings("serial")
public class GUIFrameMain extends GUIFrame {
	public GUIFrameMain() {
		super();
		// create panels
		JPanel topPanel, leftPanel, bottomPanel;
		GUIPanelMain centrePanel;
		Color bgColor = Color.LIGHT_GRAY;
		
		topPanel = new JPanel();
		leftPanel = new JPanel();
		bottomPanel = new JPanel();
		centrePanel = new GUIPanelBoard(); // the game board itself

		// set panel sizes
		topPanel.setPreferredSize(new Dimension(1, Sizes.TOP_PANEL_HEIGHT.value()));		// if you use .setSize it doesn't work? says:
		leftPanel.setPreferredSize(new Dimension(Sizes.LEFT_PANEL_WIDTH.value(), 1));		// "This method changes layout-related information, and therefore, invalidates the component hierarchy."
		bottomPanel.setPreferredSize(new Dimension(1, Sizes.BOTTOM_PANEL_HEIGHT.value()));	// in the tool tip

		// set panel backgrounds
		topPanel.setBackground(bgColor);
		leftPanel.setBackground(bgColor);
		bottomPanel.setBackground(bgColor);
		centrePanel.setBackground(bgColor);

		// add components
		this.add(topPanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(centrePanel, BorderLayout.CENTER);
	}
}
