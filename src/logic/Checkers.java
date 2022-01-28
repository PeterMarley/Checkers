package logic;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import GUIs.JFrame_GUI;
import GUIs.JPanel_Game;
import GUIs.JPanel_Intro;
import GUIs.JPanel_PlayerNames;
import logic.Enums.BoardSetup;
import logic.Enums.Palette;

/**
 * Main method & GUI for my 2 player checkers program
 * 
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class Checkers {

	///////////////////////////////////////
	// STATIC VARIABLES					//
	/////////////////////////////////////

	// testing variables
	public static final boolean TEST_MODE = true;						// When set to true, the game immediately starts rather than gets user input
	public static final BoardSetup BOARD_SETUP = BoardSetup.STANDARD; 	// Sets up a specific board layout for testing. Normal = "standard"
	public static final String CONSOLE_SEPARATOR = "----------------------";

	// GameBoard and memory
	private static GameBoard gameBoard; 							// GameBoard object
	private static int[][] memory = { { -1, -1 }, { -1, -1 } }; 	// program memory : default (unset) values all -1

	// GUI components
	private static JFrame_GUI frame;								// Main GUI JFrame
	private static JPanel_Intro intro;								// Introduction JPanel
	private static JPanel_PlayerNames playerNames;					// Player Name Selection JPanel
	private static JPanel_Game game;								// The Game itself JPanel
	private static JPanel winner;									// Declare winner screen

	///////////////////////////////////////
	// PROGRAM START main()				//
	/////////////////////////////////////

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

	/**
	 * Creates and renders the intro screen if SKIP_INTRO is false, otherwise, skips intro and player name screen and moves straight to rendering the
	 * checker board
	 */
	public static void initGUI() {
		clearMemory();
		gameBoard = new GameBoard();
		frame = new JFrame_GUI();
		frame.setVisible(true);
		intro = new JPanel_Intro();
		frame.add(intro);
		if (TEST_MODE) {
			initPanePlayerNames();
		}
	}

	/**
	 * Creates and renders the player's name selection screen if SKIP_INTRO is false, otherwise skips player name selection screen and renders checker
	 * board with fake player names
	 */
	public static void initPanePlayerNames() {
		intro.setVisible(false);
		frame.remove(intro);
		if (TEST_MODE) {
			playerNames = new JPanel_PlayerNames("Test Name 1", "Test Name 2");
			initPaneGame();
		} else {
			playerNames = new JPanel_PlayerNames();
			frame.add(playerNames);
		}

	}

	/**
	 * Create and render the checker board and info panels (main game screen) for the first time
	 */
	public static void initPaneGame() {
		if (!TEST_MODE) {
			playerNames.setVisible(false);
			frame.remove(playerNames);
		}
		game = new JPanel_Game();
		frame.add(game);
	}

	/**
	 * Redraws the checker board screen upon a move selection
	 */
	public static void redrawPaneGame() {
		// remove last game panel
		game.setVisible(false);
		frame.remove(game);
		// check if game is over
		boolean playerHasMoves = Checkers.getGameBoard().checkPlayerHasValidMoves();
		int playersPieceRemaining = GamePiece.getTotalPieces(Checkers.getGameBoard().getCurrentPlayer());
		if (!playerHasMoves || playersPieceRemaining == 0) {
			Checkers.getGameBoard().setWinner((Checkers.getGameBoard().getCurrentPlayer() == 0) ? 1 : 0);
		}
		if (Checkers.getGameBoard().getWinner() == null) {
			game = new JPanel_Game();
			frame.add(game);
		} else {
			System.out.println("Winner: " + Checkers.getGameBoard().getPlayerName(Checkers.getGameBoard().getWinner()));
			initPaneDeclareWinner();
		}
	}

	public static void initPaneDeclareWinner() {
		JLabel winnerLabel = new JLabel(
				"We have a winner! Congratulations " + Checkers.getGameBoard().getPlayerName(Checkers.getGameBoard().getWinner()) + "!");
		winnerLabel.setIcon(new ImageIcon("./images/winner.gif"));
		winnerLabel.setVerticalTextPosition(JLabel.BOTTOM);
		winnerLabel.setHorizontalTextPosition(JLabel.CENTER);
		winner = new JPanel();
		winner.setVisible(true);
		winner.add(winnerLabel);
		frame.add(winner);
		frame.setVisible(true);
	}

	public static void moveController() {
		if (isMemoryFilled()) {
			boolean moveAccepted = getGameBoard().move_operation(getMemory());

			// if move operation was successful check if player has more attacks
			if (moveAccepted) {
				boolean hasAttacks = getGameBoard().move_hasAttack(getMemory(1));
				if (!hasAttacks || (hasAttacks && !Checkers.getGameBoard().isCaptureMove())) {
					getGameBoard().nextPlayer();
				}
			}
			clearMemory();
		} else {
			// if selected piece is not the current players
			GameBoard board = getGameBoard();
			GamePiece piece = board.getSquare(getMemory(0));
			int pieceTeam = (piece != null) ? piece.getTeam() : -1;
			if (board.getCurrentPlayer() != pieceTeam) {
				board.setReturnMessage("That's not your piece! @ " + Checkers.convertCoords(getMemory(0)), Palette.ERROR.get());
				System.out.printf("Move operation complete; but player selected an opponents piece or an empty square%n" + CONSOLE_SEPARATOR + "%n");
				clearMemory();
			} else {
				board.setReturnMessage(board.getPlayerName(board.getCurrentPlayer())
						+ " has selected a piece @ " + Checkers.convertCoords(getMemory(0)));
			}
		}

	}

	///////////////////////////////////////
	// UTILITY 							//
	/////////////////////////////////////

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

	///////////////////////////////////////
	// GETTERS N SETTERS				//
	/////////////////////////////////////

	public static GameBoard getGameBoard() {
		return gameBoard;
	}

	/**
	 * Set memory cell
	 * 
	 * @param i - the board coordinates to set in cell
	 */
	public static void setMemory(int[] i) {
		int cell = 0;
		if (Checkers.memory[0][0] > 0) {
			cell = 1;
		}
		memory[cell] = i;
		System.out.printf("Cell " + cell + " set to [" + i[0] + "," + i[1] + "]%n%n");
		System.out.println("Memory: " + Arrays.deepToString(getMemory()));
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
		System.out.println("Memory cleared.");
	}

	/**
	 * Check if the Checkers class memory cells has been filled (meaning player has chose a source square and a destination square)
	 * 
	 * @return A boolean - are both memory cells filled?
	 */
	private static boolean isMemoryFilled() {
		int[][] memory = getMemory();
		boolean memoryFilled = true;
		for (int[] cell : memory) {
			for (int cellPart : cell) {
				if (cellPart == -1) {
					memoryFilled = false;
					break;
				}
			}
		}
		return memoryFilled;
	}

}
