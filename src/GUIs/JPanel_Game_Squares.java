package GUIs;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import logic.Checkers;
import logic.GamePiece;
import logic.Enums.Sizes;

@SuppressWarnings("serial")
public class JPanel_Game_Squares extends JPanel {

	///////////////////////////////////////
	// INSTANCE FIELDS					//
	/////////////////////////////////////
	
	public JButton_aSquare[][] squares;
	private int squaresWide;
	
	///////////////////////////////////////
	// CONSTRUCTOR						//
	/////////////////////////////////////
	
	/** 
	 * @param squaresWide
	 * @param panelWidth
	 * @param PanelHeight
	 */
	public JPanel_Game_Squares() {
		super();
		this.squaresWide = Sizes.CENTER_PANEL_SQUARES.get();
		this.setLayout(new GridLayout(squaresWide, squaresWide, 0, 0));
		this.setAllSquares();
	}

	///////////////////////////////////////
	// GETTERS N SETTERS				//
	/////////////////////////////////////
	
	public void setAllSquares() {
		this.squares = new JButton_aSquare[squaresWide][squaresWide];
		Color squareColor = Color.WHITE;
		Color brown = new Color(107, 71, 55);
		for (int row = this.squares.length - 1; row >= 0; row--) {
			for (int col = 0; col < this.squares.length; col++) {
				GamePiece piece = Checkers.gameBoard.getSquare(row, col);
				JButton_aSquare square = new JButton_aSquare(row, col, squareColor, piece);
				this.add(square);
				if (col != 7) {
					squareColor = (squareColor == Color.WHITE) ? brown : Color.WHITE;
				}
			}
		}
	}
}
