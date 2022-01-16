package GUIs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.Main;
import logic.Enums.WinColors;

@SuppressWarnings("serial")
public class JPanel_PlayerNames extends JPanel implements ActionListener {

	///////////////////////////////////////
	// INSTANCE FIELDS					//
	/////////////////////////////////////
	
	private JButton playButton;
	private JTextField player1TextField, player2TextField;
	private JLabel player1FeedbackLabel, player2FeedbackLabel;

	///////////////////////////////////////
	// CONSTRUCTORS						//
	/////////////////////////////////////
	
	public JPanel_PlayerNames() {
		super();
		JPanel[] panels = new JPanel[3];
		panels[0] = getPanelTop();
		panels[1] = getPanelMiddle();
		panels[2] = getPanelBottom();
		this.setLayout(new GridBagLayout());

		// grid bag constraints

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;

		// row 0 
		c.gridy = 0;
		this.add(panels[0], c);

		// row 1 
		c.gridy = 1;
		this.add(panels[1], c);

		// row 2
		c.weighty = 10;
		c.gridy = 2;
		this.add(panels[2], c);
	}

	public JPanel_PlayerNames(String player1, String player2) {
		this.setNames(player1, player2);
	}

	///////////////////////////////////////
	// GETTERS N SETTERS				//
	/////////////////////////////////////
	
	private JPanel getPanelTop() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 3, 3));
		JLabel labelPlayer1 = getLabel("Player 1 Name:");
		JLabel labelPlayer2 = getLabel("Player 2 Name:");
		player1TextField = getTextField();
		player2TextField = getTextField();
		panel.add(labelPlayer1);
		panel.add(player1TextField);
		panel.add(labelPlayer2);
		panel.add(player2TextField);
		return panel;
	}

	private JPanel getPanelMiddle() {
		JPanel panel = new JPanel();
		playButton = new JButton();
		playButton.setPreferredSize(new Dimension(100, 100));
		playButton.setText("Play!");
		playButton.addActionListener(this);
		panel.add(playButton);
		return panel;

	}

	private JPanel getPanelBottom() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		player1FeedbackLabel = getErrorLabel();
		player2FeedbackLabel = getErrorLabel();
		panel.add(player1FeedbackLabel);
		panel.add(player2FeedbackLabel);
		return panel;

	}

	private JLabel getErrorLabel() {
		JLabel label = new JLabel();
		label = new JLabel();
		label.setBackground(WinColors.DARK.get());
		label.setPreferredSize(new Dimension(200, 50));
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setText("s");
		label.setVisible(false);
		return label;
	}

	private JLabel getLabel(String text) {
		JLabel label = new JLabel();
		label.setOpaque(false);
		label.setText(text);
		//label.insets(new Insets(0, 10, 0, 10));
		return label;
	}

	private JTextField getTextField() {
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(150, 25));
		return field;
	}

	public boolean setNames(String player1, String player2) {
		boolean player1Accepted = true;
		boolean player2Accepted = true;
		if (!Main.SKIP_INTRO) {
			player1FeedbackLabel.setVisible(false);
			player2FeedbackLabel.setVisible(false);

			// check player 1 name validity
			if (player1.isEmpty() || player1.isBlank()) {
				player1Accepted = false;
				player1FeedbackLabel.setVisible(true);
				player1FeedbackLabel.setText("Player 1 name unacceptable!");
			}

			// check player 2 name validity
			if (player2.isEmpty() || player2.isBlank()) {
				player2Accepted = false;
				player2FeedbackLabel.setVisible(true);
				player2FeedbackLabel.setText("Player 2 name unacceptable!");
			}

			// logic
			if (player1Accepted) {
				player1FeedbackLabel.setVisible(true);
				player1FeedbackLabel.setText(String.format("Player 1: %s", player1));
			}
			if (player2Accepted) {
				player2FeedbackLabel.setVisible(true);
				player2FeedbackLabel.setText(String.format("Player 2: %s", player2));
			}

		}
		if (player1Accepted && player2Accepted) {
			Main.gameBoard.setPlayerNames(player1, player2);
			return true;
		}
		return false;
	}

	///////////////////////////////////////
	// ACTIONS							//
	/////////////////////////////////////
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			String player1 = player1TextField.getText();
			String player2 = player2TextField.getText();
			boolean namesSet = setNames(player1, player2);
			if (namesSet) {
				Main.initPaneGame();
			}
		}
	}
}
