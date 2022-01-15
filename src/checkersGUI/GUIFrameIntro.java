package checkersGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import checkers.Controller;
import checkersGUI.Enums.Sizes;
import checkersGUI.Enums.WinColors;
import checkersGUI.abstracts.GUIFrame;

@SuppressWarnings("serial")
public class GUIFrameIntro extends GUIFrame {

	public GUIFrameIntro() {
		super();
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		p.setBackground(WinColors.LIGHT.get());

		JLabel topText = new JLabel();
		topText.setIcon(new ImageIcon("./images/checkerboard.png"));
		topText.setText("Welcome to Peter Marley's Student Project!");
		topText.setVerticalTextPosition(JLabel.TOP);
		topText.setHorizontalTextPosition(JLabel.CENTER);
		topText.setForeground(WinColors.DARKEST.get());
		topText.setIconTextGap(15);


		JLabel midText = new JLabel("Have Fun!");
		midText.setOpaque(true);
		midText.setVerticalAlignment(JLabel.CENTER);
		midText.setHorizontalAlignment(JLabel.CENTER);
		midText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		midText.setForeground(WinColors.ACCENT.get());
		midText.setBackground(WinColors.DARK.get());
		midText.setPreferredSize(new Dimension(150, 50));

		JButton startButton = new JButton();
		startButton.setText("START GAME");
		startButton.setIcon(new ImageIcon("./images/checkeredFlags.png"));
		startButton.addActionListener(e -> startGame());

		JPanel selectionPanel = getSelectionPanel();

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
		p.add(topText, c);

		// top-middle row ///////////////////////////////////
		c.gridy = ++row;
		c.weightx = weightX / 10;
		c.weighty = weightY / 10;
		c.anchor = GridBagConstraints.NORTH;
		p.add(midText, c);
		row++;

		// bottom-middle row ////////////////////////////////
		c.gridy = ++row;
		c.weightx = weightX;
		c.weighty = weightY;
		c.anchor = GridBagConstraints.CENTER;
		p.add(startButton, c);

		// bottom row - ////// //////////////////////////////
		//	bottom row -> top row
		c.gridy = ++row;
		c.weightx = weightX;
		c.weightx = weightY;
		p.add(selectionPanel, c);

		this.add(p);
		this.setVisible(true);
	}

	/**
	 * @return A JPanel configured for intro menu selections
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
		//quitButton.setEnabled(false);
		quitButton.addActionListener(e -> selectionQuit());

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

	private void startGame() {
		String message = "User selected start game from intro menu";
		Controller.log.add(message);
		Controller.gui.initGUI_Game();
	}
	
	private void selectionQuit() {
		String message = "User selected quit game from intro menu";
		Controller.log.add(message);
		this.dispose();
	}
	
	//	public GUIFrameIntro() {
	//		super();
	//		this.setLayout(new GridLayout(3, 1));
	//
	//		Color thirdsPanelColor = Color.LIGHT_GRAY;
	//		JPanel north, center, south;
	//		north = getPanelNorth(thirdsPanelColor);
	//		center = getPanelCenter(thirdsPanelColor);
	//		south = getPanelSouth(thirdsPanelColor);
	//
	//		this.add(north, BorderLayout.NORTH);
	//		this.add(center, BorderLayout.CENTER);
	//		this.add(south, BorderLayout.SOUTH);
	//	}
	//
	//	public static void startGame() {
	//		GUI.frameMain.setVisible(true);
	//		GUI.frameIntro.setVisible(false);
	//	}
	//
	//	/**
	//	 * @param rows
	//	 * @param cols
	//	 * @param color
	//	 * @return The Top Panel of intro screen, containing the logo only
	//	 */
	//	public static JPanel getPanelNorth(Color color) {
	//
	//		JLabel label = new JLabel();
	//		label.setIcon(new ImageIcon("./images/checkerboard.png"));
	//		label.setVerticalAlignment(JLabel.CENTER);
	//		label.setHorizontalAlignment(JLabel.CENTER);
	//
	//		JPanel panel = new JPanel(new GridLayout(1, 1));
	//		panel.setBackground(color);
	//		panel.add(label);
	//
	//		return panel;
	//	}
	//
	//	/**
	//	 * @param color
	//	 * @return The Top Panel of intro screen, containing welcome message
	//	 */
	//	public static JPanel getPanelCenter(Color color) {
	//
	//		JLabel label = new JLabel();
	//		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	//		label.setBackground(new Color(200, 100, 125));
	//		label.setOpaque(true);
	//		label.setHorizontalAlignment(JLabel.CENTER);
	//		label.setVerticalAlignment(JLabel.CENTER);
	//		label.setPreferredSize(new Dimension(100, 100));
	//		label.setBounds(100, 100, Sizes.getFrameWidth() - 200, Sizes.getFrameHeight() / 3 - 200);
	//		label.setText("Weclome to Checkers!");
	//
	//		JPanel panel = new JPanel(null);
	//		panel.setBackground(Color.LIGHT_GRAY);
	//		panel.add(label, BorderLayout.CENTER);
	//
	//		return panel;
	//	}
	//
	//	/**
	//	 * @param color
	//	 * @return The Bottom Panel of intro screen, containing various selections
	//	 */
	//	public static JPanel getPanelSouth(Color color) {
	//
	//		JPanel panel = new JPanel();
	//		panel.setLayout(new GridLayout(1, 3));
	//		panel.setBackground(color);
	//		
	//		JPanel left, middle, right;
	//		left = new JPanel();
	//		left.setBackground(Color.RED);
	//		left.setLayout(new BorderLayout());
	//		middle = new JPanel();
	//		middle.setBackground(Color.GREEN);
	//		right = new JPanel();
	//		right.setBackground(Color.BLUE);
	//		
	//		panel.add(left);
	//		panel.add(middle);
	//		panel.add(right);
	//		
	//		JButton options = new JButton();
	//		options.setText("Option");
	//		left.add(options);;
	//
	//		
	//		
	//
	//		return panel;
	//	}

	//	public static JPanel getTextPanel() {
	//		JPanel textPanel = new JPanel();
	//		textPanel.setLayout(new GridLayout(2, 1));
	//		textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, Sizes.SQUARE_BORDER_THICKNESS.value()));
	//		textPanel.setBackground(Color.LIGHT_GRAY);
	//		textPanel.setForeground(Color.WHITE);
	//		return textPanel;
	//	}

	//	public static JLabel getTextLabel() {
	//		JLabel textLabel = new JLabel();
	//		String welcome = String.format("Welcome to Checkers!");
	//		textLabel.setVerticalAlignment(JLabel.CENTER);
	//		textLabel.setHorizontalAlignment(JLabel.CENTER);
	//		textLabel.setText(welcome);
	//		return textLabel;
	//	}

	//	public static JButton getStartButton() {
	//		JButton startButton = new JButton();
	//		startButton.setText("Start the game!");
	//		startButton.addActionListener(e -> startGame());
	//		return startButton;
	//	}
}
