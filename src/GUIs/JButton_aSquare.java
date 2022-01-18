package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import logic.Checkers;
import logic.GamePiece;
import logic.Enums.*;

@SuppressWarnings("serial")
public class JButton_aSquare extends JButton implements ActionListener {

	///////////////////////////////////////
	// INSTANCE FIELDS					//
	/////////////////////////////////////

	public int row;
	public int col;

	///////////////////////////////////////
	// CONSTRUCTOR						//
	/////////////////////////////////////

	/**
	 * Constructor for a single checker board square
	 * @param row
	 * @param col
	 * @param color
	 * @param piece
	 */
	public JButton_aSquare(int row, int col, Color color, GamePiece piece) {
		int borderThickness = Sizes.SQUARE_BORDER_THICKNESS.get();
		int widthOfSquare = Sizes.CENTER_PANEL_SIZE.get() / Sizes.CENTER_PANEL_SQUARES.get() - (2 * Sizes.SQUARE_BORDER_THICKNESS.get());

		// define square display
		ImageIcon icon = null;
		this.row = row;
		this.col = col;

		if (piece != null) {
			if (piece.isKing()) {
				if (piece.getTeam() == 0) {
					icon = Icons.BLACK_KING.get();
				} else {
					icon = Icons.WHITE_KING.get();
				}
			} else {
				if (piece.getTeam() == 0) {
					icon = Icons.BLACK_PIECE.get();
				} else {
					icon = Icons.WHITE_PIECE.get();
				}
			}
			this.setIcon(icon);
		}

		// define properties
		this.setBackground(color);
		this.setToolTipText(Checkers.convertCoords(new int[] { row, col }));
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(widthOfSquare, widthOfSquare));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		this.addActionListener(this);

		int topBorderThickness = 0;
		int leftBorderThickness = 0;
		if (row == Sizes.CENTER_PANEL_SQUARES.get() - 1) {
			topBorderThickness = borderThickness;
		}
		if (col == 0) {
			leftBorderThickness = borderThickness;
		}
		Border border = BorderFactory.createMatteBorder(topBorderThickness, leftBorderThickness, borderThickness,
				borderThickness, Color.BLACK);
		this.setBorder(border);


	}

	///////////////////////////////////////
	// ACTIONS							//
	/////////////////////////////////////

	/**
	 * Button click actions for this object
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this) {
			squareClicked();
		}
	}

	/**
	 * Player clicks a square
	 */
	private void squareClicked() {
		int[] coords = new int[] { row, col };

		// console message
		GamePiece piece = Checkers.getGameBoard().getSquare(coords);
		String pieceName = (piece == null) ? "empty!" : piece.toString();
		System.out.println("row=" + row + ", col=" + col + " button pressed!");
		String square = Checkers.convertCoords(coords);
		System.out.println("Square is: " + square + " : " + pieceName);
		System.out.println();

		// set memory
		Checkers.setMemory(coords);

		//TODO move this logic to Checkers.java - probably more appropriate there
		// if memory full then try move operation
		
		
		
		if (isMemoryFilled()) {
			boolean moveAccepted = Checkers.getGameBoard().move_operation(Checkers.getMemory());

			// if move operation was successful check if player has more attacks
			if (moveAccepted) {
				boolean hasAttacks = Checkers.getGameBoard().move_hasAttack(Checkers.getMemory(1));
				if (!hasAttacks || (hasAttacks && !Checkers.getGameBoard().isCaptureMove())) {
					//TODO fix this conditional statement for todo in checkers.java
					Checkers.getGameBoard().nextPlayer();
				}
			}
			Checkers.clearMemory();
		} else {
			Checkers.getGameBoard().setReturnMessage(Checkers.getGameBoard().getPlayerName(Checkers.getGameBoard().getCurrentPlayer())
					+ " has selected a piece @ " + Checkers.convertCoords(coords));
		}
		
		
		
		Checkers.redrawPaneGame();
	}

	/**
	 * Check if the Checkers class memory cells has been filled (meaning player has chose a source square and a destination square)
	 * @return A boolean - are both memory cells filled?
	 */
	private boolean isMemoryFilled() {
		int[][] memory = Checkers.getMemory();
		boolean memoryFilled = true;
		for (int[] cell : memory) {
			for (int cellPart : cell) {
				if (cellPart == -1) {
					memoryFilled = false;
					break;
				}
			}
		}
		return memoryFilled;
	}
}
