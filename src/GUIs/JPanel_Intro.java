package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Enums.WinColors;

@SuppressWarnings("serial")
public class JPanel_Intro extends JPanel implements ActionListener {

	private JLabel topText = getTopTextLabel();
	private JLabel midText = getMidTextLabel();
	private JButton startButton = getStartButton();
	private JPanel selectionPanel = getSelectionPanel();
	
	///////////////////////////////////////////
	// Constructor & Operations				//
	/////////////////////////////////////////

	/**
	 * Constructor for the games Intro Screen object
	 */
	public JPanel_Intro() {
		this.setLayout(new GridBagLayout());
		this.setBackground(WinColors.LIGHT.get());

		///////////////////////////////
		// GridBagLayout			//
		/////////////////////////////
		// values
		int row = 0;
		int col = 0;
		int weightX = 10;
		int weightY = 10;

		// top row //////////////////////////////////////////

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = col;
		c.gridy = row;
		c.weightx = weightX;
		c.weighty = weightY;
		this.add(topText, c);

		// top-middle row ///////////////////////////////////
		c.gridy = ++row;
		c.weightx = weightX / 10;
		c.weighty = weightY / 10;
		c.anchor = GridBagConstraints.NORTH;
		this.add(midText, c);
		row++;

		// bottom-middle row ////////////////////////////////
		c.gridy = ++row;
		c.weightx = weightX;
		c.weighty = weightY;
		c.anchor = GridBagConstraints.CENTER;
		this.add(startButton, c);

		// bottom row - ////// //////////////////////////////
		//	bottom row -> top row
		c.gridy = ++row;
		c.weightx = weightX;
		c.weightx = weightY;
		this.add(selectionPanel, c);

	}

	///////////////////////////////////////////
	// Get Components						//
	/////////////////////////////////////////

	/**
	 * @return A JLabel - the top text of intro screen
	 */
	private JLabel getTopTextLabel() {
		JLabel topText = new JLabel();
		topText.setIcon(new ImageIcon("./images/checkerboard.png"));
		topText.setText("Welcome to Peter Marley's Student Project!");
		topText.setVerticalTextPosition(JLabel.TOP);
		topText.setHorizontalTextPosition(JLabel.CENTER);
		topText.setForeground(WinColors.DARKEST.get());
		topText.setIconTextGap(15);
		return topText;
	}

	/**
	 * @return A JLabel - the middle text of intro screen
	 */
	private JLabel getMidTextLabel() {
		JLabel midText = new JLabel("Have Fun!");
		midText.setOpaque(true);
		midText.setVerticalAlignment(JLabel.CENTER);
		midText.setHorizontalAlignment(JLabel.CENTER);
		midText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		midText.setForeground(WinColors.ACCENT.get());
		midText.setBackground(WinColors.DARK.get());
		midText.setPreferredSize(new Dimension(150, 50));
		return midText;
	}

	/**
	 * @return A JButton - the start button of intro screen
	 */
	private JButton getStartButton() {
		JButton startButton = new JButton();
		startButton.setText("START GAME");
		startButton.setIcon(new ImageIcon("./images/checkeredFlags.png"));
		startButton.addActionListener(this);
		return startButton;
	}

	/**
	 * @return A JPanel - the bottom panel of intro screen<br>
	 *         configured for intro menu selections (options, credits, load, quit)
	 */
	private JPanel getSelectionPanel() {
		// instantiate and configure JPanel container 
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(WinColors.MIDDLE.get());
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setPreferredSize(new Dimension(500, 200));

		// instantiate and configure JButtons
		JButton optionsButton = getSelectionButton("Options");
		JButton creditsButton = getSelectionButton("Credits");
		JButton loadButton = getSelectionButton("Load Game");
		JButton quitButton = getSelectionButton("Quit Game");

		optionsButton.setEnabled(false);
		creditsButton.setEnabled(false);
		loadButton.setEnabled(false);
		quitButton.setEnabled(false);
		//quitButton.addActionListener(e -> selectionQuit());

		// prepare constraints
		GridBagConstraints c = new GridBagConstraints();
		int spacer = 75;

		//top row ///////////////////////////////////////////
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, spacer);
		panel.add(optionsButton, c);

		c.gridx = 1;
		c.insets = new Insets(0, spacer, 0, 0);
		panel.add(loadButton, c);

		//bottom row /////////////////////////////////////////
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(spacer / 2, 0, 0, spacer);
		panel.add(creditsButton, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(spacer / 2, spacer, 0, 0);
		panel.add(quitButton, c);

		// GridBagLayout Complete!

		return panel;
	}

	/**
	 * @param text A String to set a JButton text
	 * @return A JButton standardised for intro menu
	 */
	private JButton getSelectionButton(String text) {
		JButton button = new JButton();
		button.setText(text);
		button.setPreferredSize(new Dimension(125, 50));
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==startButton) {
			MainGUI.showPanePlayerNames();
		}
		
	}
}