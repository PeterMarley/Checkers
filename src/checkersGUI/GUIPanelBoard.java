package checkersGUI;

import java.awt.Color;

import checkers.GamePiece;
import checkersGUI.Enums.Sizes;
import checkersGUI.abstracts.GUIPanelMain;

@SuppressWarnings("serial")
public class GUIPanelBoard extends GUIPanelMain {

	//public GUISquareLabel[][] squares;
	public GUISquareButton[][] squares;

	/**
	 * 
	 * @param squaresWide
	 * @param panelWidth
	 * @param PanelHeight
	 */
	public GUIPanelBoard() {
		super();

		// get enum values for readability
		int squaresWide = Sizes.CENTER_PANEL_SQUARES.value();
		this.squares = new GUISquareButton[squaresWide][squaresWide];
		int team = 0;
		Color squareColor = Color.WHITE;
		Color brown = new Color(107, 71, 55);
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
				this.add(square);
				if (col != 7) {
					squareColor = (squareColor == Color.WHITE) ? brown : Color.WHITE;
				}
			}
		}
	}
}
