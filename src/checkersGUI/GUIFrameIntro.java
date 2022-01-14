package checkersGUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import checkersGUI.Enums.Sizes;
import checkersGUI.abstracts.GUIFrame;

public class GUIFrameIntro extends GUIFrame {

	public GUIFrameIntro() {
		super();
		this.setLayout(new GridLayout(2, 1, 0, 10));
		JLabel checkerboard = new JLabel();
		checkerboard.setIcon(new ImageIcon("./images/checkerboard.png"));
		checkerboard.setVerticalAlignment(JLabel.CENTER);
		checkerboard.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2, 1));
		textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, Sizes.SQUARE_BORDER_THICKNESS.value()));
		textPanel.setBackground(Color.LIGHT_GRAY);
		textPanel.setForeground(Color.WHITE);
		
		JLabel textLabel = new JLabel();
		String welcome = String.format("Welcome to Checkers!");
		textLabel.setVerticalAlignment(JLabel.CENTER);
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setText(welcome);
		
		JButton startButton = new JButton();
		startButton.setText("Start the game!");
		startButton.addActionListener(e -> startGame());
		
		//combine components
		textPanel.add(textLabel);
		textPanel.add(startButton);
		this.add(checkerboard);
		this.add(textPanel);
	}
	
	public static void startGame() {
		GUI.frameMain.setVisible(true);
		GUI.frameIntro.setVisible(false);
	}

}
