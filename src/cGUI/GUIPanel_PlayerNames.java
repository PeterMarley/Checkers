package cGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.stream.events.StartDocument;

import cGUI.Enums.WinColors;
import checkers.Controller;
import checkers.GameBoard;

public class GUIPanel_PlayerNames extends JPanel implements ActionListener {

	private JButton startButton;
	private JTextField namePlayer1, namePlayer2;
	private JLabel player1FeedbackLabel, player2FeedbackLabel;

	public GUIPanel_PlayerNames() {
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

	private JPanel getPanelTop() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 3, 3));
		JLabel labelPlayer1 = getLabel("Player 1 Name:");
		JLabel labelPlayer2 = getLabel("Player 2 Name:");
		namePlayer1 = getTextField();
		namePlayer2 = getTextField();
		panel.add(labelPlayer1);
		panel.add(namePlayer1);
		panel.add(labelPlayer2);
		panel.add(namePlayer2);
		return panel;
	}

	private JPanel getPanelMiddle() {
		JPanel panel = new JPanel();
		startButton = new JButton();
		startButton.setPreferredSize(new Dimension(100, 100));
		startButton.setText("Play!");
		startButton.addActionListener(this);
		panel.add(startButton);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			String player1 = namePlayer1.getText();
			String player2 = namePlayer2.getText();
			player1FeedbackLabel.setVisible(false);
			player2FeedbackLabel.setVisible(false);
			boolean player1Accepted = true;
			boolean player2Accepted = true;

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
			if (player1Accepted && player2Accepted) {
				Controller.gameBoard = new GameBoard(player1, player2);
				MainGUI.showPaneGame();
			}

		}
	}
}
