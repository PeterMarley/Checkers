package checkersGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import checkersGUI.Enums.Sizes;
import checkersGUI.abstracts.GUIFrame;

@SuppressWarnings("serial")
public class GUIFrameIntro extends GUIFrame {

	public GUIFrameIntro() {
		super();
		this.setLayout(new GridLayout(3, 1));

		Color thirdsPanelColor = Color.LIGHT_GRAY;
		JPanel north, center, south;
		north = getPanelNorth(thirdsPanelColor);
		center = getPanelCenter(thirdsPanelColor);
		south = getPanelSouth(thirdsPanelColor);

		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	}

	public static void startGame() {
		GUI.frameMain.setVisible(true);
		GUI.frameIntro.setVisible(false);
	}

	/**
	 * @param rows
	 * @param cols
	 * @param color
	 * @return The Top Panel of intro screen, containing the logo only
	 */
	public static JPanel getPanelNorth(Color color) {

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon("./images/checkerboard.png"));
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);

		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setBackground(color);
		panel.add(label);

		return panel;
	}

	/**
	 * @param color
	 * @return The Top Panel of intro screen, containing welcome message
	 */
	public static JPanel getPanelCenter(Color color) {

		JLabel label = new JLabel();
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label.setBackground(new Color(200, 100, 125));
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setPreferredSize(new Dimension(100, 100));
		label.setBounds(100, 100, Sizes.getFrameWidth() - 200, Sizes.getFrameHeight() / 3 - 200);
		label.setText("Weclome to Checkers!");

		JPanel panel = new JPanel(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.add(label, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @param color
	 * @return The Bottom Panel of intro screen, containing various selections
	 */
	public static JPanel getPanelSouth(Color color) {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.setBackground(color);
		
		JPanel left, middle, right;
		left = new JPanel();
		left.setBackground(Color.RED);
		left.setLayout(new BorderLayout());
		middle = new JPanel();
		middle.setBackground(Color.GREEN);
		right = new JPanel();
		right.setBackground(Color.BLUE);
		
		panel.add(left);
		panel.add(middle);
		panel.add(right);
		
		JButton options = new JButton();
		options.setText("Option");
		left.add(options);;

		
		

		return panel;
	}

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
