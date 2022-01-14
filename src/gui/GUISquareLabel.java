package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import checkers.GamePiece;

/**
 * A Custom JLabel subclass for representing a Checkers board square
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
@SuppressWarnings("serial")
public class GUISquareLabel extends JLabel {
	public GUISquareLabel(Color color, GamePiece p) {

		// define square display
		@SuppressWarnings("unused")
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
		int length = GUI.CENTER_PANEL_SIZE / GUI.CENTER_PANEL_SQUARES - (2 * GUIPanelBoard.SQUARE_BORDER_THICKNESS);
		this.setBackground(color);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(length, length));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);

	}

}
