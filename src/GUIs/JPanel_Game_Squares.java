package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import logic.GamePiece;
import logic.Tools;
import logic.Enums.Sizes;

@SuppressWarnings("serial")
public class JPanel_Game_Squares extends JPanel {

	//public GUISquareLabel[][] squares;
	public JButton_aSquare[][] squares;
	private int squaresWide;
	/**
	 * 
	 * @param squaresWide
	 * @param panelWidth
	 * @param PanelHeight
	 */
	public JPanel_Game_Squares() {
		super();
		this.squaresWide = Sizes.CENTER_PANEL_SQUARES.value();
		this.setLayout(new GridLayout(squaresWide, squaresWide, 0, 0));
		fillSquares();
	}

	public void fillSquares() {
		this.squares = new JButton_aSquare[squaresWide][squaresWide];
		Color squareColor = Color.WHITE;
		Color brown = new Color(107, 71, 55);
		for (int row = this.squares.length - 1; row >= 0; row--) {
			for (int col = 0; col < this.squares.length; col++) {
				GamePiece piece = Tools.gameBoard.getSquare(row, col);
				JButton_aSquare square = new JButton_aSquare(row, col, squareColor, piece);
				this.add(square);
				if (col != 7) {
					squareColor = (squareColor == Color.WHITE) ? brown : Color.WHITE;
				}
			}
		}
	}
}
