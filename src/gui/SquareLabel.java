package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import checkers.GamePiece;

public class SquareLabel extends JLabel {
	public SquareLabel(Color color, GamePiece p) {
		// define border
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		// define icon
		ImageIcon icon = null;
		if (p != null) {
			if (p.isKing()) {
				if (p.getTeam() == 0) {
					icon = Enums.Icons.BLACK_KING.get();
				} else {
					icon = Enums.Icons.WHITE_KING.get();
				}
			} else {
				if (p.getTeam() == 0) {
					icon = Enums.Icons.BLACK_PIECE.get();
				} else {
					icon = Enums.Icons.WHITE_PIECE.get();
				}
			}
			this.setIcon(icon);
			//this.setText(p.toVisualString());

		}
		// define properties
		this.setBackground(color);
		this.setBorder(border);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(98,98));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);

	}

}
