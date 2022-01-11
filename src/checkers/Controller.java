package checkers;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Scanner;

import checkers.Enums.BoardSetup;

/**
 * 
 * @author Peter Marley
 * @projectTitle Checkers V1
 * @City Belfast
 * @Country Ireland
 * @maxLove cs50x - You changed my life <3
 *
 */
public class Controller {

	// declarations
	public static Log log; 											// Logging System
	private static GameBoard gameBoard; 							// GameBoard object
	private static Scanner scanner = new Scanner(System.in); 		// User input scanner
	private static int[][] memory = { { -1, -1 }, { -1, -1 } }; 	// program memory : default (unset) values all -1
	private static String returnMessage = ""; 						// remotely set display message for UI

	// testing variables
	public static final boolean SKIP_INTRO = true;						// When set to true, the game immediately starts rather than gets user input
	public static final boolean TIMERS_DEACTIVATED = true; 				// Deactivates menu sleep() timers
	public static final BoardSetup BOARD_SETUP = BoardSetup.STANDARD; 	// Sets up a specific board layout for testing. Normal = "standard"

	///////////////////////////////////////
	// BOARD ADMIN 						//
	/////////////////////////////////////

	/**
	 * Start point of program<br>
	 * ensures logging system is correctly shut down after program is finished
	 */
	public static void main(String[] args) {
		//		EventQueue.invokeLater(new Runnable() {
		//			public void run() {
		//				try {
		//					GUI frame = new GUI();
		//					frame.setVisible(true);
		//				} catch (Exception e) {
		//					e.printStackTrace();
		//				}
		//			}
		//		});
		startUp();
		shutDown();
	}

	/**
	 * Start log, get player names, initiate game
	 */
	private static void startUp() {
		log = new Log("./");
		gameBoard = (SKIP_INTRO) ? new GameBoard("Test Player 1", "Test Player 2") : getGameBoard();
		aGameOfCheckers();
	}

	/**
	 * Shuts the program down safely by shutting down the logging system and displaying credits & farewell message
	 */
	private static void shutDown() {
		log.shutdown();
		System.out.println("Thanks for playing!");

		for (int i = 0; i < 4; i++) {
			sleep(1);
			System.out.println(".".repeat(i + 1));
		}

		System.out.printf("%n".repeat(50));
		System.out.println("This game brought to you by novice programmer");

		System.out.println(
				" ______   ______     ______   ______     ______        __    __     ______     ______     __         ______     __  __    ");
		System.out.println(
				"/\\  == \\ /\\  ___\\   /\\__  _\\ /\\  ___\\   /\\  == \\      /\\ \"-./  \\   /\\  __ \\   /\\  == \\   /\\ \\       /\\  ___\\   /\\ \\_\\ \\   ");
		System.out.println(
				"\\ \\  _-/ \\ \\  __\\   \\/_/\\ \\/ \\ \\  __\\   \\ \\  __<      \\ \\ \\-./\\ \\  \\ \\  __ \\  \\ \\  __<   \\ \\ \\____  \\ \\  __\\   \\ \\____ \\");
		System.out.println(
				" \\ \\_\\    \\ \\_____\\    \\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\     \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\/\\_____\\");
		System.out.println(
				"  \\/_/     \\/_____/     \\/_/   \\/_____/   \\/_/ /_/      \\/_/  \\/_/   \\/_/\\/_/   \\/_/ /_/   \\/_____/   \\/_____/   \\/_____/");
		sleep(5);
		System.out.println();
		System.out.println("ASCII text art from: https://patorjk.com/software/taag/#p=display&h=0&v=0&f=Sub-Zero&t=Peter%20Marley");
	}

	/**
	 * The main logical loop of the game
	 */
	private static void aGameOfCheckers() {
		boolean toContinue = true;
		while (toContinue) {
			// get selection
			gameBoard.displayBoard();
			if (gameBoard.checkPlayerHasValidMoves()) {
				if (!setMemoryOrQuit(0, "Select your piece!")
						|| !setMemoryOrQuit(1, "You have selected " + convertCoords(memory[0]) + " - now select your destination square! ")) {
					break;
				}

				// if memory is invalid get selection again
				if (memory[0][0] == -1 || memory[0][1] == -1 || memory[1][0] == -1 || memory[1][1] == -1) {
					continue;
				}

				// actuate the move. player is changed here if move has no further attacks
				toContinue = gameBoard.executeMove(memory);
			} else {
				break;
			}
		}
		declareWinner();
	}

	/**
	 * This method gets the users input into text prompt.<br>
	 * Allows:<br>
	 * - Board coordinates in format "A1", "C4" etc<br>
	 * - "q"/ "quit" - quits the game<br>
	 * - "h"/ "help" - displays help menu<br>
	 * - "c"/ "cancel" - cancels and restarts the current players move
	 * 
	 * @param i the index of static int[2] memory to set
	 * @param prompt A String prompt
	 * @return A boolean - player has selected quit?
	 */
	private static boolean setMemoryOrQuit(int i, String prompt) {

		if (i >= memory.length || i < 0) {
			Controller.log.add("Improper index passed into setMemoryOrQuit() [i=" + i + ", should be 0 or 1]", true);
			return false;
		}

		boolean isValidated = false;
		while (!isValidated) {
			// render game scene and get user input
			renderGameScene(prompt);
			String userInput = scanner.nextLine().toLowerCase();

			// selection logic for i'th selection
			if (userInput.equals("h") || userInput.equals("help")) {
				printMenuHelp();
			} else if (userInput.equals("q") || userInput.equals("quit")) {
				Controller.log.add("User chose quit!");
				return false;
			} else if (userInput.equals("c") || userInput.equals("cancel")) {
				boolean pieceSelected = (memory[0][0] == -1 || memory[0][1] == -1) ? false : true;
				returnMessage = String.format("Move cancelled! %s%n", (pieceSelected) ? convertCoords(memory[0]) + " unselected" : "");
				clearMemory();
				return true;
			} else if (userInput.equals("cap") || userInput.equals("captured")) {
				gameBoard.printCaptured();

			} else if (userInput.length() == 2) {
				if (userInput.charAt(0) >= 'a' && userInput.charAt(0) <= 'h' && userInput.charAt(1) >= '1' && userInput.charAt(1) <= '8') {
					isValidated = true;
					memory[i][0] = userInput.charAt(1) - 49;
					memory[i][1] = userInput.charAt(0) - 97;
					Controller.log.add("Player " + gameBoard.getCurrentPlayer() + " selected " + Arrays.deepToString(memory));
				}
			}
			userInput = "";
		}
		return true;
	}

	/**
	 * gets players names, and displays welcome message
	 * 
	 * @return A GameBoard - correctly configured
	 */
	private static GameBoard getGameBoard() {

		String[] playerNames = new String[2];
		boolean[] playerNameSelected = new boolean[2];

		// Prompt for game mode & set GameBoard
		System.out.printf("%n".repeat(200));
		System.out.println("\tWelcome to Draughts.");
		sleep(3);

		// prompt for player names
		for (int i = 0; i < playerNames.length; i++) {
			System.out.printf("%n[Enter player " + (i + 1) + " name]: ");
			while (!playerNameSelected[i]) {
				playerNames[i] = scanner.nextLine();
				if (!playerNames[i].isBlank()) {
					playerNameSelected[i] = true;
				}
			}
			Controller.log.add("Player " + i + " named: \"" + playerNames[i] + "\" successfully");
		}

		// log successful GameBoard creation and then return the GameBoard
		Controller.log.add("GameBoard initialised. [TEST_MODE: " + SKIP_INTRO + "] [TEST_BOARD: " + BOARD_SETUP + "]");
		return new GameBoard(playerNames[0], playerNames[1]);

	}

	/**
	 * Set the returnMessage used on renderGameScene()
	 * 
	 * @param m
	 */
	public static void setReturnMessage(String m) {
		returnMessage = m;
	}

	/**
	 * Set the returnMessage used on renderGameScene()
	 * 
	 * @return A String - the returnMessage
	 */
	public static String getReturnMessage() {
		return returnMessage;
	}

	/**
	 * Declares the winner
	 */
	private static void declareWinner() {
		System.out.printf("%n".repeat(200));
		int blackPieces = GamePiece.getTotalPieces(0);
		int whitePieces = GamePiece.getTotalPieces(1);
		if (blackPieces == 0) {
			System.out.println(gameBoard.getPlayerName(1) + " has won the game!");
		} else if (whitePieces == 0) {
			System.out.println(gameBoard.getPlayerName(0) + " has won the game!");
		} else {
			System.out.println(gameBoard.getPlayerName((gameBoard.getCurrentPlayer() == 0) ? 1 : 0) + " has won the game!");
		}
		System.out.println("Pieces left on board: " + GamePiece.enumeratePiecesOnBoard());

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

	/**
	 * @param seconds - sleep for this long unless TIMERS_DEACTIVATED == true
	 */
	private static void sleep(int seconds) {
		if (!TIMERS_DEACTIVATED) {
			try {
				Thread.sleep(seconds * 1000);
			} catch (InterruptedException e) {
				Controller.log.add("Controller.menuSleep() Thread.Sleep failure", true);
			}
		}
	}

	/**
	 * Clear class memory
	 */
	private static void clearMemory() {
		memory = new int[][] { { -1, -1 }, { -1, -1 } };
	}

	///////////////////////////////////////
	// MENUS 							//
	/////////////////////////////////////

	/**
	 * Display the help menu
	 */
	private static void printMenuHelp() {
		System.out.printf("%n".repeat(200));
		System.out.println("You can do the following during the game:");
		System.out.println("\t1) type \"h\" or \"help\" for help at any time");
		System.out.println("\t2) type \"q\" or \"quit\" to quit");
		System.out.println("\t3) type \"c\" or \"cancel\" to restart your go");
		System.out.println("\t4) type \"cap\" or \"captured\" to see captured pieces");
		System.out.println("\t5) play the game");
		System.out.println();
		System.out.println("How to play:");
		System.out.println("\t- Select your piece with coorindates e.g. A1, E4");
		System.out.println("\t- You then select your target move");
		System.out.println("\t- If the move was illegal you will get another try at your turn");
		System.out.printf("%n".repeat(15));
		System.out.println("Continue (press enter)?");
		scanner.nextLine();

	}

	/**
	 * Render a game scene and prompt player for input
	 */
	public static void renderGameScene(String prompt) {
		// User Interface
		if (!SKIP_INTRO) {
			System.out.printf("%n".repeat(200));
		}
		gameBoard.displayBoard();

		// User info
		String playerColour = (gameBoard.getCurrentPlayer() == 0) ? "Black" : "White";
		String playerName = gameBoard.getPlayerName(gameBoard.getCurrentPlayer());
		// display instruction
		System.out.printf("%nINSTRUCTION: %s - %s%n", playerColour + "'s Move (" + playerName + ")", prompt);
		// display returnMessage, if there is one (then set back to "" / empty)
		if (!returnMessage.isEmpty()) {
			System.out.printf("%n*** MESSAGE *** %s%n", returnMessage);
			returnMessage = "";
		}
		// display prompt
		System.out.printf("%n[h for help]: ");
	}
}
