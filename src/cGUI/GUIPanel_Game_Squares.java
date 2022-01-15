package cGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import cGUI.Enums.Sizes;
import checkers.GamePiece;

@SuppressWarnings("serial")
public class GUIPanel_Game_Squares extends JPanel {

	//public GUISquareLabel[][] squares;
	public GUIButton_aSquare[][] squares;

	/**
	 * 
	 * @param squaresWide
	 * @param panelWidth
	 * @param PanelHeight
	 */
	public GUIPanel_Game_Squares() {
		super();
		int squaresWide = Sizes.CENTER_PANEL_SQUARES.value();
		this.setLayout(new GridLayout(squaresWide, squaresWide, 0, 0));
		
		// get enum values for readability
		this.squares = new GUIButton_aSquare[squaresWide][squaresWide];
		int team = 0;
		Color squareColor = Color.WHITE;
		Color brown = new Color(107, 71, 55);
		for (int row = this.squares.length - 1; row >= 0; row--) {
			for (int col = 0; col < this.squares.length; col++) {
				if (row == this.squares.length / 2 && col == 0) {
					team = 1;
				}
				GamePiece piece = null;
				// square = null;
				if (row < 3 || row > 4) {

					if ((row + 1) % 2 == 1) {
						if ((col + 1) % 2 == 1) {
							piece = new GamePiece(team);
						}

					} else {
						if ((col + 1) % 2 == 0) {
							piece = new GamePiece(team);
						}
					}
				}
				//if (piece != null) piece.setToKing();
				GUIButton_aSquare square = new GUIButton_aSquare(row, col, squareColor, piece);
				this.add(square);
				if (col != 7) {
					squareColor = (squareColor == Color.WHITE) ? brown : Color.WHITE;
				}
			}
		}
	}
}
