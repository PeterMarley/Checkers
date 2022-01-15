package cGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIPanel_PlayerNames extends JPanel {
	public GUIPanel_PlayerNames() {
		super();
		JPanel[] panels = new JPanel[4];
		panels[0] = new JPanel(new GridLayout(1, 2, 3, 0));
		panels[1] = new JPanel(new GridLayout(1, 2, 3, 0));
		panels[2] = new JPanel();
		panels[3] = new JPanel(new GridBagLayout());
		
		JLabel labelPlayer1 = getLabel("Player 1 Name:");
		JLabel labelPlayer2 = getLabel("Player 2 Name:");
		JLabel errorLabel = new JLabel();
		JTextField textPlayer1 = getTextField();
		JTextField textPlayer2 = getTextField();
		JButton play = new JButton();
		this.setLayout(new GridBagLayout());
		
		//panels[0].add
		// grid bag constraints

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 0.5;
		
		// row 0 
		// col 0 
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(labelPlayer1, c);

		// col 1
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(textPlayer1, c);
		
		// row 1
		// col 0 
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_END;
		//c.anchor = GridBagConstraints.LINE_END;
		this.add(labelPlayer2, c);
		
		// col 1
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		//c.anchor = GridBagConstraints.LINE_START;
		this.add(textPlayer2, c);
		
		// row 2
		c.weighty = 10;
		// col 0
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		this.add(play, c);
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
}
