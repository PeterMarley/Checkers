package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Enums.Sizes;
import logic.Enums.WinColors;
import logic.Checkers;

/**
 * Creates a JPanel instance which is used to house the main games play window - where the game is played
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
@SuppressWarnings("serial")
public class JPanel_Game extends JPanel {

	///////////////////////////////////////
	// INSTANCE FIELDS					//
	/////////////////////////////////////

	private JPanel centrePanel, topPanel, leftPanel, bottomPanel;

	///////////////////////////////////////
	// CONSTRUCTOR						//
	/////////////////////////////////////

	/**
	 * A constructor for the main game display JPanel of GUI
	 */
	public JPanel_Game() {
		// configure this.
		super();
		this.setLayout(new BorderLayout());
		// set panels
		this.setPanelTop();			// display players and show which is current player
		this.setLeftPanel();
		this.setBottomPanel();
		this.setCenterPanel();	 	// the checker board itself
		// add components
		this.add(topPanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(centrePanel, BorderLayout.CENTER);
	}

	///////////////////////////////////////
	// GETTERS N SETTERS				//
	/////////////////////////////////////

	/**
	 * @return the top JPanel of main game window (player names and current player indicator)
	 */
	private void setPanelTop() {
		// get player names
		String[] names = Checkers.getGameBoard().getPlayerNames();

		// instantiate components
		topPanel = new JPanel(new GridLayout(1, 2));
		JLabel player1 = new JLabel("Black: " + names[0]);
		JLabel player2 = new JLabel("White: " + names[1]);

		// set alignments
		player1.setVerticalAlignment(JLabel.CENTER);
		player1.setHorizontalAlignment(JLabel.CENTER);
		player2.setVerticalAlignment(JLabel.CENTER);
		player2.setHorizontalAlignment(JLabel.CENTER);

		// set background colours and text depending on current player
		if (Checkers.getGameBoard().getCurrentPlayer() == 0) {
			player1.setBackground(WinColors.DARK.get());
			player1.setForeground(WinColors.ACCENT.get());
			player1.setText("Black: " + names[0] + " (Current)");
			player1.setOpaque(true);
		} else {
			player2.setBackground(WinColors.DARK.get());
			player2.setForeground(WinColors.ACCENT.get());
			player2.setText("White: " + names[1] + " (Current)");
			player2.setOpaque(true);
		}

		// configure topPanel
		topPanel.add(player1);
		topPanel.add(player2);
		topPanel.setPreferredSize(new Dimension(1, Sizes.TOP_PANEL_HEIGHT.get()));
		topPanel.setBackground(Color.RED);

	}

	/**
	 * @return the left JPanel of main game window
	 */
	private void setLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(Sizes.LEFT_PANEL_WIDTH.get(), 1));
		leftPanel.setBackground(Color.GREEN);
	}

	/**
	 * @return the bottom JPanel of main game window
	 */
	private void setBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(1, Sizes.BOTTOM_PANEL_HEIGHT.get()));	// in the tool tip
		bottomPanel.setBackground(Color.BLUE);
		bottomPanel.setLayout(new GridBagLayout());
		JLabel displayReturnMessage;
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		displayReturnMessage = new JLabel(Checkers.getGameBoard().getReturnMessage());
		displayReturnMessage.setVerticalAlignment(JLabel.CENTER);
		displayReturnMessage.setHorizontalAlignment(JLabel.CENTER);
		displayReturnMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		displayReturnMessage.setPreferredSize(new Dimension(Sizes.getFrameWidth() / 2, Sizes.BOTTOM_PANEL_HEIGHT.get() / 2));
		displayReturnMessage.setOpaque(true);
		displayReturnMessage.setBackground(WinColors.DARKEST.get());
		displayReturnMessage.setForeground(WinColors.ACCENT.get());
		bottomPanel.add(displayReturnMessage, c);
		Checkers.getGameBoard().clearReturnMessage();
	}

	/**
	 * @return the center JPanel of main game window
	 */
	private void setCenterPanel() {
		centrePanel = new JPanel_Game_Squares();
		centrePanel.setBackground(Color.MAGENTA);
		centrePanel.setPreferredSize(new Dimension(Sizes.CENTER_PANEL_SIZE.get(), Sizes.CENTER_PANEL_SIZE.get()));
	}

}
