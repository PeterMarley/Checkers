package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import checkers.GamePiece;

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
		int x, y;
		x = 1000;
		y = 1000;
		JFrame frame = new JFrame();
		frame.setTitle("Checkers by Peter Marley");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(x, y);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.GRAY); 	// remember a frame is made of several hidden layers (think back to java
															// doc on oracle website with the wee diagram with glass pane at front
		GameBoardPanel board = new GameBoardPanel();
		JPanel topPanel, leftPanel, bottomPanel, centrePanel;
		topPanel = new JPanel();
		leftPanel = new JPanel();
		bottomPanel = new JPanel();
		centrePanel = new JPanel();

<<<<<<< HEAD
		topPanel.setPreferredSize(new Dimension(1, 50));		// if you use .setSize it doesn't work? says:
		leftPanel.setPreferredSize(new Dimension(50, 1));		// "This method changes layout-related information, and therefore, invalidates the component hierarchy."
		bottomPanel.setPreferredSize(new Dimension(1, 150));	//		in the tool tip

		topPanel.setBackground(Color.RED);
		leftPanel.setBackground(Color.GREEN);
		bottomPanel.setBackground(Color.BLUE);
		centrePanel.setBackground(Color.DARK_GRAY);
=======
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		/* THIS */
		//panel.setLayout(new BorderLayout());
		//panel.setSize(1000, 1000);
		/* OR */
		panel.setLayout(null);
		panel.setBounds(10, 10, x - 35, x - 60);
>>>>>>> 8d2e47ed7b616cbbb1452f09ec1305eb550ad055

		centrePanel.add(new SquareLabel(Color.BLACK, new GamePiece(1)));
		
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(board, BorderLayout.CENTER);

<<<<<<< HEAD
=======
		// add components
		panel.add(label);
		frame.add(panel);

		// set frame to visible
>>>>>>> 8d2e47ed7b616cbbb1452f09ec1305eb550ad055
		frame.setVisible(true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////

<<<<<<< HEAD
		//		JFrame frame = new JFrame();
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		frame.setSize(1000, 1000);
		//		frame.setResizable(false);
		//		frame.setIconImage(new ImageIcon("./icons/checkersIcon.png").getImage());
		//		frame.setLayout(new BorderLayout());
		//		frame.getContentPane().setBackground(Color.GREEN);
		//
		//		JPanel panel = new JPanel();
		//		panel.setBackground(Color.BLUE);
		//		/* THIS */
		//		//panel.setLayout(new BorderLayout());
		//		//panel.setSize(900, 900);
		//		/* OR */
		//		panel.setLayout(null);
		//		panel.setBounds(10, 10, 900, 900); // sets panels position inside ITS container, not the position of the panel!!!
		//
		//
		//		JLabel label = new JLabel();
		//		label.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		//		label.setText("Test");
		//		label.setBackground(Color.BLACK);
		//		label.setOpaque(true);
		//		/* THIS (not right yet)*/
		//		//		label.setLayout(new BorderLayout());
		//		//		label.setVerticalAlignment(JLabel.CENTER);
		//		//		label.setHorizontalAlignment(JLabel.CENTER);
		//		//		label.setSize(900, 900);
		//		/* OR */
		//		label.setLayout(null);
		//		label.setBounds(0, 0, 50, 50);
		//
		//		// add components
		//		panel.add(label);
		//		frame.add(panel, BorderLayout.CENTER);
		//
		//
		//		// set frame to visible
		//		frame.setVisible(true);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////

=======
>>>>>>> 8d2e47ed7b616cbbb1452f09ec1305eb550ad055
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
