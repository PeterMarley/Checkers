package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import checkers.GamePiece;

public class ChessSquareLabel extends JLabel {
	public ChessSquareLabel(int x, int y, Color color, GamePiece p) {
		// define border
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		// define icon
		ImageIcon icon = null;
		if (p.isKing()) {
			if (p.getTeam() == 0) {
				icon = Enums.Pieces.BLACK_KING.get();
			} else {
				icon = Enums.Pieces.WHITE_KING.get();
			}
		} else {
			if (p.getTeam() == 0) {
				icon = Enums.Pieces.BLACK_PIECE.get();
			} else {
				icon = Enums.Pieces.WHITE_PIECE.get();
			}
		}
		
		// define properties
		this.setIcon(icon);
		this.setBackground(color);
		this.setBorder(border);
		this.setOpaque(false);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		//this.setBounds(x, y, 100, 100);

	}

}
