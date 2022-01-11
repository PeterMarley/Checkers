package gui;

import java.awt.Color;

import javax.swing.JPanel;

import checkers.GamePiece;

public class GUITesting {

	public static void main(String[] args) {
		// Test GamePieces
		GamePiece bp = new GamePiece(0);
		GamePiece bk = new GamePiece(0);
		bk.setToKing();
		GamePiece wp = new GamePiece(1);
		GamePiece wk = new GamePiece(1);
		wk.setToKing();

		MyJFrame frame = new MyJFrame();
		
		ChessSquareLabel label = new ChessSquareLabel(0, 0, Color.BLACK, bk);
		frame.setVisible(true);
		label.setBounds(0, 0, 100, 100);
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.add(label);
	}

}
