package logic;

import javax.swing.SwingUtilities;

import GUIs.JFrame_GUI;
import GUIs.JPanel_Game;
import GUIs.JPanel_Intro;
import GUIs.JPanel_PlayerNames;
import logic.Enums.BoardSetup;

/**
 * GUI for my 2 player checkers program
 * 
 * @Attributions - Checkers Pieces Images*:<br>
 *               https://woodexpressions.com/wp-content/uploads/2016/07/258015Main2DB.jpg<br>
 *               <br>
 *               - Checker Board Intro Image:<br>
 *               https://content.mycutegraphics.com/graphics/play/checker-game-clip-art.png<br>
 *               <br>
 *               * Images edited by me to give them the correct size and background transparency on GIMP
 * 
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class Main {

	// testing variables
	public static final boolean SKIP_INTRO = false;						// When set to true, the game immediately starts rather than gets user input
	public static final boolean TIMERS_DEACTIVATED = false; 			// Deactivates menu sleep() timers
	public static final BoardSetup BOARD_SETUP = BoardSetup.MULTIPLEJUMPS1; 	// Sets up a specific board layout for testing. Normal = "standard"
	
	public static GameBoard gameBoard; 								// GameBoard object
	private static int[][] memory = { { -1, -1 }, { -1, -1 } }; 	// program memory : default (unset) values all -1

	public static JFrame_GUI frame = new JFrame_GUI();				// Main GUI JFrame
	public static JPanel_Intro intro;								// Introduction JPanel
	public static JPanel_PlayerNames playerNames;					// Player Name Selection JPanel
	public static JPanel_Game game;									// The Game itself JPanel
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}

	///////////////////////////////////////
	// INITIALISATIONS					//
	/////////////////////////////////////

	public static void initGUI() {
		frame.setVisible(true);
		intro = new JPanel_Intro();
		frame.add(intro);
		if (SKIP_INTRO) {
			initPanePlayerNames();
		}
	}

	public static void initPanePlayerNames() {
		intro.setVisible(false);
		frame.remove(intro);
		if (SKIP_INTRO) {
			playerNames = new JPanel_PlayerNames("Test Name 1", "Test Name 2");
			initPaneGame();
		} else {
			playerNames = new JPanel_PlayerNames();
			frame.add(playerNames);
		}

	}

	public static void initPaneGame() {
		if (!SKIP_INTRO) {
			playerNames.setVisible(false);
			frame.remove(playerNames);
		}
		game = new JPanel_Game();
		frame.add(game);
	}

	///////////////////////////////////////
	// REDRAW GUI						//
	/////////////////////////////////////

//	public static void redrawPaneGame() {
//		game.setVisible(false);
//		frame.remove(game);
//		clearMemory();
//		game = new JPanel_Game();
//		frame.add(game);
//		game.setVisible(true);
//	}

	///////////////////////////////////////
	// UTILITY 							//
	/////////////////////////////////////

	public static void setGameBoard(String player1, String player2) {
		gameBoard = new GameBoard(player1, player2);
	}
	
	public static String[] getPlayerNames() {
		String[] names = new String[2];
		names[0] = gameBoard.getPlayerName(0);
		names[1] = gameBoard.getPlayerName(1);
		return names;
	}
	
	/**
	 * Set memory cell
	 * 
	 * @param i - the board coordinates to set in cell
	 */
	public static void setMemory(int[] i) {
		int cell = 0;
		if (Main.memory[0][0] != -1) {
			cell = 1;
		}
		memory[cell][0] = i[0];
		memory[cell][1] = i[1];
		System.out.printf("Cell " + cell + " set to [" + i[0] + "," + i[1] + "]%n%n");
	}

	/**
	 * @return both memory cells - an int[2][2]
	 */
	public static int[][] getMemory() {
		return memory;
	}

	/**
	 * @param cell - the cell to return (must be 0 or 1)
	 * @return a memory cell - an int[2] (or null cell argument outside range)
	 */
	public static int[] getMemory(int cell) {
		if (cell == 0 || cell == 1) {
			return memory[cell];
		}
		return null;
	}

	/**
	 * Clear both memory cells
	 */
	public static void clearMemory() {
		memory = new int[][] { { -1, -1 }, { -1, -1 } };
	}

	/**
	 * Convert a String representation of a board square to an int[2].<br>
	 * A1 -> [0,0]
	 * 
	 * @param s - A string representing a board square A-H / 1-8
	 * @return an int[2] representing the board position, eg, [0,0]
	 */
	public static int[] convertCoords(String s) {
		s = s.toUpperCase();
		if (s.charAt(0) < 'A' || s.charAt(0) > 'H' || s.charAt(1) < '1' || s.charAt(1) > '7') {
			return new int[] { -15, -15 };
		}
		return new int[] { s.charAt(1) - 49, s.charAt(0) - 65 };
	}

	/**
	 * Convert an int[2] representation of a board square to a String.<br>
	 * [0,0] -> A1
	 * 
	 * @param c - An int[2] representing a board square [0-7,0-7]
	 * @return A String representing the board position, eg, A1
	 */
	public static String convertCoords(int[] c) {
		if (c[0] < 0 || c[0] > 7 || c[1] < 0 || c[1] > 7) {
			return "ERROR";
		}
		return String.format("%c%c", (char) (c[1] + 65), (char) c[0] + 49);
	}
}