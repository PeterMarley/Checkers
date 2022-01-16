package logic;

import logic.Enums.BoardSetup;

/**
 * 
 * @author Peter Marley
 * @projectTitle Checkers V1
 * @City Belfast
 * @Country Ireland
 * @maxLove cs50x - You changed my life <3
 *
 */
public class Tools {

	// variables
	public static GameBoard gameBoard; 								// GameBoard object
	private static int[][] memory = { { -1, -1 }, { -1, -1 } }; 	// program memory : default (unset) values all -1
	private static String returnMessage = ""; 						// remotely set display message for UI

	// testing variables
	public static final boolean SKIP_INTRO = true;						// When set to true, the game immediately starts rather than gets user input
	public static final boolean TIMERS_DEACTIVATED = false; 			// Deactivates menu sleep() timers
	public static final BoardSetup BOARD_SETUP = BoardSetup.CS50DEMO; 	// Sets up a specific board layout for testing. Normal = "standard"

	///////////////////////////////////////
	// Memory Cells						//
	/////////////////////////////////////

	/**
	 * Set memory cell
	 * 
	 * @param i - the board coordinates to set in cell
	 */
	public static void setMemory(int[] i) {
		int cell = 0;
		if (memory[0][0] != -1) {
			cell = 1;
		}
		memory[cell][0] = i[0];
		memory[cell][1] = i[1];
		System.out.printf("Cell " + cell + " set to [" + i[0] + "," + i[1] + "]%n%n");
	}

	/**
	 * @return memory - an int[2][2]
	 */
	public static int[][] getMemory() {
		return memory;
	}

	/**
	 * Clear both memory cells
	 */
	public static void clearMemory() {
		memory = new int[][] { { -1, -1 }, { -1, -1 } };
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

}
