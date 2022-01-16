package GUIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import logic.GamePiece;
import logic.Tools;
import logic.Enums.*;

@SuppressWarnings("serial")
public class JButton_aSquare extends JButton implements ActionListener {
	public int row;
	public int col;

	public JButton_aSquare(int row, int col, Color color, GamePiece p) {
		int borderThickness = Sizes.SQUARE_BORDER_THICKNESS.value();
		int widthOfSquare = Sizes.CENTER_PANEL_SIZE.value() / Sizes.CENTER_PANEL_SQUARES.value() - (2 * Sizes.SQUARE_BORDER_THICKNESS.value());

		// define square display
		ImageIcon icon = null;
		this.row = row;
		this.col = col;
		if (p != null) {
			if (p.isKing()) {
				if (p.getTeam() == 0) {
					icon = Icons.BLACK_KING.get();
				} else {
					icon = Icons.WHITE_KING.get();
				}
			} else {
				if (p.getTeam() == 0) {
					icon = Icons.BLACK_PIECE.get();
				} else {
					icon = Icons.WHITE_PIECE.get();
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
			int[] coords = new int[] {row, col};
			// console message
			System.out.println("row=" + row + ", col=" + col + " button pressed!");
			String square = Tools.convertCoords(coords);
			System.out.println("Square is: " + square);
			System.out.println();
			// set memory
			Tools.setMemory(coords);
			// check if memory cells set
			int[][] memory = Tools.getMemory();
			boolean memoryFilled = true;
			for (int[] cell : memory) {
				for (int cellPart : cell) {
					if (cellPart == -1) {
						memoryFilled = false;
						break;
					}
				}
			}
			// if memory full then try move operation
			boolean moveAccepted = false;
			if (memoryFilled) {
				moveAccepted = Tools.gameBoard.move_operation(memory);
			}
			if (moveAccepted) {
				Controller.redrawPaneGame();
			}
		}
	}
}
