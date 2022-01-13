package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import checkers.GamePiece;

public class GameBoardPanel extends JPanel {
	public GameBoardPanel() {
		//Add GamePieces at appropriate positions
		JPanel panel = new JPanel();
		int scale = 100;
		SquareLabel[][] board = new SquareLabel[8][8];
		int team = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (row == 3 && col == 0) {
					team = 1;
				}
				GamePiece piece = null;
				SquareLabel square = null;
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
				//TODO get index 0,0 to actually have correct alignment and position (see comment after loop) / works on laptop?!
				//if (piece != null) {
				square = new SquareLabel(Color.RED, piece); // TODO figure out why this colour is being setting the Frame background
				board[row][col] = square;
				int x, y;
				x = (col + 1) * scale;
				y = (row + 1) * scale;
				board[row][col].setBounds(x, y, scale, scale);
				/*
				 * Probably need a box or grid layout
				 */
				//panel.add(board[row][col], BorderLayout);
				//}
			}
		}
	}
}
