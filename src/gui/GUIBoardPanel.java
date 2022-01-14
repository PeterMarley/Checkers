package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import checkers.GamePiece;

@SuppressWarnings("serial")
public class GUIBoardPanel extends JPanel {

	public GUISquareLabel[][] squares;

	/**
	 * 
	 * @param squaresWide
	 * @param panelWidth
	 * @param PanelHeight
	 */
	public GUIBoardPanel(int squaresWide, int panelWidth, int PanelHeight) {
		this.setLayout(new GridLayout(8, 8));
		this.setPreferredSize(new Dimension(800, 800));
		this.squares = new GUISquareLabel[squaresWide][squaresWide];
		int team = 0;
		Color squareColor = Color.WHITE;
		for (int row = 0; row < this.squares.length; row++) {
			for (int col = 0; col < this.squares.length; col++) {
				if (row == this.squares.length / 2 && col == 0) {
					team = 1;
				}
				GamePiece piece = null;
				GUISquareLabel square = null;
				//if (row < 3 || row > 4) {
				if ((row + 1) % 2 == 1) {
					if ((col + 1) % 2 == 0) {
						piece = new GamePiece(team);
					}
				} else {
					if ((col + 1) % 2 == 1) {
						piece = new GamePiece(team);
					}
				}

				//}
				square = new GUISquareLabel(squareColor, piece);

				this.add(square);
				if (col != 7) {
					squareColor = (squareColor == Color.WHITE) ? Color.DARK_GRAY : Color.WHITE;
				}
			}
		}
	}
}
