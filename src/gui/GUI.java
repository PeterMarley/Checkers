package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI {
	public static void main(String[] args) {
		// if you dont do this then you get inconsistent GUI. i presume something to do with
		// multiple unsynchronised threads being used? more research needed
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initBoard();
			}
		});

	}

	public static void initBoard() {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon("./icons/checkersIcon.png").getImage());
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.GREEN);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		/* THIS */
		//panel.setLayout(new BorderLayout());
		//panel.setSize(1000, 1000);
		/* OR */
		panel.setLayout(null);
		panel.setBounds(0, 0, 990, 965);


		JLabel label = new JLabel();
		label.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		label.setText("Test");
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		label.setLayout(null);
		label.setBounds(0, 0, 50, 50);

		// add components
		panel.add(label);
		frame.add(panel);


		// set frame to visible
		frame.setVisible(true);













		// Test GamePieces
		//		GamePiece bp = new GamePiece(0);
		//		GamePiece bk = new GamePiece(0);
		//		bk.setToKing();
		//		GamePiece wp = new GamePiece(1);
		//		GamePiece wk = new GamePiece(1);
		//		wk.setToKing();

		//		int scale = 50;
		//		MyJFrame frame = new MyJFrame();
		//		SquareLabel[][] board = new SquareLabel[8][8];

		// initialise squares to have null GamePiece
		//		for (int row = 0; row < board.length; row++) {
		//			for (int col = 0; col < board[row].length; col++) {
		//				SquareLabel square = new SquareLabel(Color.WHITE, null);
		//				board[row][col] = square;
		//				int x, y;
		//				x = (col + 1) * scale;
		//				y = (row + 1) * scale;
		//				board[row][col].setBounds(x, y, scale, scale);
		//				frame.add(board[row][col]);
		//
		//			}
		//		}

		// Add GamePieces at appropriate positions
		//		int team = 0;
		//		for (int row = 0; row < board.length; row++) {
		//			for (int col = 0; col < board[row].length; col++) {
		//				if (row == 3 && col == 0) {
		//					team = 1;
		//				}
		//				GamePiece piece = null;
		//				SquareLabel square = null;
		//				if (row < 3 || row > 4) {
		//					if ((row + 1) % 2 == 1) {
		//						if ((col + 1) % 2 == 0) {
		//							piece = new GamePiece(team);
		//						}
		//					} else {
		//						if ((col + 1) % 2 == 1) {
		//							piece = new GamePiece(team);
		//						}
		//					}
		//				}
		//				//TODO get index 0,0 to actually have correct alignment and position (see comment after loop) / works on laptop?!
		//				//if (piece != null) {
		//				square = new SquareLabel(Color.RED, piece); // TODO figure out why this colour is being setting the Frame background
		//				board[row][col] = square;
		//				int x, y;
		//				x = (col + 1) * scale;
		//				y = (row + 1) * scale;
		//				board[row][col].setBounds(x, y, scale, scale);
		//				frame.add(board[row][col]);
		//				//}
		//			}
		//		}
		/*
		 * SquareLabel bastardFirstSquare = new SquareLabel(Color.BLUE, null); bastardFirstSquare.setBounds(100, 100, 100, 100);
		 * frame.add(bastardFirstSquare);
		 */
		//SquareLabel testLabl = new SquareLabel(0, 0, Color.BLACK, bk);

		//testLabl.setBounds(0, 0, 100, 100);
		//frame.setSize(scale * 10, scale * 10);
		//frame.setLayout(null);

		//gridLayout.
		//GridBagConstraints gridConstraints = new GridBagConstraints(gridx, gridy, gridwidth, 
		//		gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady)
		//gridLayout.add
		//frame.setLayout(null);

		//frame.add(testLabl);
		//frame.pack();
		//frame.setVisible(true);

	}
}
