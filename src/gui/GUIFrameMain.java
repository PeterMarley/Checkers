package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.Enums.Length;
import gui.abstracts.GUIFrame;

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
		GUIPanelBoard centrePanel;
		topPanel = new JPanel();
		leftPanel = new JPanel();
		bottomPanel = new JPanel();
		centrePanel = new GUIPanelBoard(Length.CENTER_PANEL_SQUARES.value(), Length.CENTER_PANEL_SIZE.value(), Length.CENTER_PANEL_SIZE.value());

		// set panel sizes
		topPanel.setPreferredSize(new Dimension(1, Length.TOP_PANEL_HEIGHT.value()));		// if you use .setSize it doesn't work? says:
		leftPanel.setPreferredSize(new Dimension(Length.LEFT_PANEL_WIDTH.value(), 1));		// "This method changes layout-related information, and therefore, invalidates the component hierarchy."
		bottomPanel.setPreferredSize(new Dimension(1, Length.BOTTOM_PANEL_HEIGHT.value()));	// in the tool tip

		// set panel backgrounds
		topPanel.setBackground(Color.RED);
		leftPanel.setBackground(Color.GREEN);
		bottomPanel.setBackground(Color.BLUE);
		centrePanel.setBackground(Color.DARK_GRAY);

		// add components
		this.add(topPanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(centrePanel, BorderLayout.CENTER);
	}
}
