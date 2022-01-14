package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gui.Enums.Length;
import gui.abstracts.GUIFrame;

/**
 * GUI for my 2 player checkers program
 * @Attributions Images: https://woodexpressions.com/wp-content/uploads/2016/07/258015Main2DB.jpg<br>
 * Edited by me to give them the correct size and background transparency on GIMP
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class GUI {

	// Define Dimensions
	public static final int TOP_PANEL_HEIGHT = 50;
	public static final int LEFT_PANEL_WIDTH = 50;
	public static final int BOTTOM_PANEL_HEIGHT = 50;
	public static final int CENTER_PANEL_SIZE = 800;
	public static final int CENTER_PANEL_SQUARES = 8;
	public static final int FRAME_WIDTH = CENTER_PANEL_SIZE + LEFT_PANEL_WIDTH;
	public static final int FRAME_HEIGHT = CENTER_PANEL_SIZE + TOP_PANEL_HEIGHT + BOTTOM_PANEL_HEIGHT;

	public static void main(String[] args) {
		// if you dont do this then you get inconsistent GUI. i presume something to do with
		// multiple unsynchronised threads being used? more research needed
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});

	}

	public static void initGUI() {


		GUIFrame frameMain = new GUIFrameMain();
		GUIFrame frameIntro = new GUIFrameIntro();
		


		// set frame to visible
		frameMain.setVisible(true);
		frameIntro.setVisible(false);

	}

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////

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
