package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import checkers.GamePiece;

@SuppressWarnings("serial")
public class GUISquareButton extends JButton implements ActionListener {
	public int row;
	public int col;

	public GUISquareButton(int row, int col, Color color, GamePiece p) {
		// define square display
		ImageIcon icon = null;
		this.row = row;
		this.col = col;
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
		this.addActionListener(this);



	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this) {
			System.out.println("row=" + row + ", col=" + col + " button pressed!");
			String square = checkers.Controller.convertCoords(new int[] {row, col});
			System.out.println("Square is: " + square);
			System.out.println();
		}
	}
}
