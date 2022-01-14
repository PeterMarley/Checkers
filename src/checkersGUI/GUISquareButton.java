package checkersGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import checkers.GamePiece;
import checkersGUI.Enums.Length;;

@SuppressWarnings("serial")
public class GUISquareButton extends JButton implements ActionListener {
	public int row;
	public int col;

	public GUISquareButton(int row, int col, Color color, GamePiece p) {
		int borderThickness = Length.SQUARE_BORDER_THICKNESS.value();
		int widthOfSquare = Length.CENTER_PANEL_SIZE.value() / Length.CENTER_PANEL_SQUARES.value() - (borderThickness * 2);
		
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
		this.setBackground(color);
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(widthOfSquare, widthOfSquare));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		this.addActionListener(this);

		int topBorderThickness = 0;
		int leftBorderThickness = 0;
		if (row == 0) {
			topBorderThickness = borderThickness;
		}
		if (col == 0) {
			leftBorderThickness = borderThickness;
		}
		Border border = BorderFactory.createMatteBorder(topBorderThickness, leftBorderThickness, borderThickness,
				borderThickness, Color.BLACK);
		this.setBorder(border);


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
