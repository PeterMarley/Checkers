package gui;

import java.awt.Color;

import checkers.GamePiece;

public class GUI {
	public static void main(String[] args) {
		// Test GamePieces
		//		GamePiece bp = new GamePiece(0);
		//		GamePiece bk = new GamePiece(0);
		//		bk.setToKing();
		//		GamePiece wp = new GamePiece(1);
		//		GamePiece wk = new GamePiece(1);
		//		wk.setToKing();

		// instantiate frame
		MyJFrame frame = new MyJFrame();
		// declare and init board
		SquareLabel[][] gameBoard = new SquareLabel[8][8];
		// set frame to visible
		frame.setVisible(true);

		// set all gameBoard square labels
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				GamePiece tmp = null;
				if (row < 3 || row > 4) {
					if ((row + 1) % 2 == 1) {
						if ((col + 1) % 2 == 0) {
							tmp = new GamePiece(0);
						}
					} else {
						if ((col + 1) % 2 == 1) {
							tmp = new GamePiece(0);
						}
					}
				}
				gameBoard[row][col] = new SquareLabel(Color.BLUE, tmp);
				gameBoard[row][col].setBounds((col * 100)+100, (row * 100)+100, 100, 100);
				frame.add(gameBoard[row][col]);
			}
		}
		//SquareLabel testLabl = new SquareLabel(0, 0, Color.BLACK, bk);

		//testLabl.setBounds(0, 0, 100, 100);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		//frame.add(testLabl);
	}

}
