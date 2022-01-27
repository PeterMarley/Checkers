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
import logic.Enums.Palette;
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
		JLabel[] players = new JLabel[] { new JLabel("Black: " + names[0]), new JLabel("White: " + names[1]) };
		int currentPlayer = Checkers.getGameBoard().getCurrentPlayer();
		// set alignments
		players[0].setVerticalAlignment(JLabel.CENTER);
		players[0].setHorizontalAlignment(JLabel.CENTER);
		players[0].setPreferredSize(new Dimension(1, Sizes.TOP_PANEL_HEIGHT.get() / 2));
		players[1].setVerticalAlignment(JLabel.CENTER);
		players[1].setHorizontalAlignment(JLabel.CENTER);
		players[1].setPreferredSize(new Dimension(1, Sizes.TOP_PANEL_HEIGHT.get() / 2));


		// set background colours and text depending on current player
		players[currentPlayer].setBackground(Palette.DARK.get());
		players[currentPlayer].setForeground(Palette.ACCENT.get());
		players[currentPlayer].setText(((currentPlayer == 0) ? "Black: " : "White: ") + names[0] + " (Current)");
		players[currentPlayer].setBorder(BorderFactory.createLineBorder(Palette.ACCENT.get(), 1));
		players[currentPlayer].setOpaque(true);

		// configure topPanel
		topPanel.add(players[0]);
		topPanel.add(players[1]);
		topPanel.setPreferredSize(new Dimension(1, Sizes.TOP_PANEL_HEIGHT.get()));
		topPanel.setBackground(Palette.LIGHT.get());

	}

	/**
	 * @return the left JPanel of main game window
	 */
	private void setLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(Sizes.LEFT_PANEL_WIDTH.get(), 1));
		leftPanel.setBackground(Palette.LIGHT.get());
	}

	/**
	 * @return the bottom JPanel of main game window
	 */
	private void setBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(1, Sizes.BOTTOM_PANEL_HEIGHT.get()));	// in the tool tip
		bottomPanel.setBackground(Palette.LIGHT.get());
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
		displayReturnMessage.setBackground(Checkers.getGameBoard().getReturnMessageBgColor());
		displayReturnMessage.setForeground(Palette.ACCENT.get());
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
