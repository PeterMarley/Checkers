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
				SquareLabel tmpSquare = null;
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
				//TODO get index 0,0 to actually have correct alignment and position (see comment after loop)
				tmpSquare = new SquareLabel(Color.BLUE, tmp);
				gameBoard[row][col] = tmpSquare;
				int x, y;
				x = (col + 1) * 100;
				y = (row + 1) * 100;
				gameBoard[row][col].setBounds(x, y, 100, 100);
				frame.add(gameBoard[row][col]);
			}
		}
		//SquareLabel tmp = new SquareLabel(Color.BLUE, null);
		//tmp.setBounds(100, 100, 100, 100);
		//frame.add(tmp);
		//SquareLabel testLabl = new SquareLabel(0, 0, Color.BLACK, bk);

		//testLabl.setBounds(0, 0, 100, 100);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		//frame.add(testLabl);
	}

}
