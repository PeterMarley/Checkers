package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import checkers.GamePiece;

@SuppressWarnings("serial")
public class GUIPanelBoard extends JPanel {

	//public GUISquareLabel[][] squares;
	public GUISquareButton[][] squares;
	public static final int SQUARE_BORDER_THICKNESS = 1;

	/**
	 * 
	 * @param squaresWide
	 * @param panelWidth
	 * @param PanelHeight
	 */
	public GUIPanelBoard(int squaresWide, int panelWidth, int PanelHeight) {
		this.setLayout(new GridLayout(8, 8));
		this.setPreferredSize(new Dimension(800, 800));
		//this.squares = new GUISquareLabel[squaresWide][squaresWide];
		this.squares = new GUISquareButton[squaresWide][squaresWide];
		int team = 0;
		Color squareColor = Color.WHITE;
		Color brown = new Color(107, 71, 55);
		//for (int row = 0; row < this.squares.length; row++) {
		//	for (int col = 0; col < this.squares.length; col++) {
		for (int row = this.squares.length - 1; row >= 0; row--) {
			for (int col = 0; col < this.squares.length; col++) {
				if (row == this.squares.length / 2 && col == 0) {
					team = 1;
				}
				GamePiece piece = null;
				GUISquareButton square = null;
				if (row < 3 || row > 4) {
					if ((row + 1) % 2 == 1) {
						if ((col + 1) % 2 == 0) {
							piece = new GamePiece(team);
						}
					} else {
						if ((col + 1) % 2 == 1) {
							piece = new GamePiece(team);
						}
					}
				}
				//if (piece != null) piece.setToKing();
				square = new GUISquareButton(row, col, squareColor, piece);
				int topBorderThickness = 0;
				int leftBorderThickness = 0;
				if (row == 0) {
					topBorderThickness = SQUARE_BORDER_THICKNESS;
				}
				if (col == 0) {
					leftBorderThickness = SQUARE_BORDER_THICKNESS;
				}
				Border border = BorderFactory.createMatteBorder(topBorderThickness, leftBorderThickness, SQUARE_BORDER_THICKNESS,
						SQUARE_BORDER_THICKNESS, Color.BLACK);
				square.setBorder(border);
				this.add(square);
				if (col != 7) {
					squareColor = (squareColor == Color.WHITE) ? brown : Color.WHITE;
				}
			}
		}
	}
}
