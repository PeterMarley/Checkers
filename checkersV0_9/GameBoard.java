package checkersV0_9;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Peter Marley
 * @projectTitle Checkers V1
 * @City Belfast
 * @Country Ireland
 * @maxLove cs50x - You changed my life <3
 *
 */
public class GameBoard {

	/**
	 * The game board itself, a GamePiece[][]
	 */
	private GamePiece[][] board;
	private ArrayList<GamePiece> captured = new ArrayList<GamePiece>(40); // captured pieces stored here
	private String[] playerNames = new String[2];
	/**
	 * 0 = Black<br>
	 * 1 = White
	 */
	private int currentPlayer;

	///////////////////////////////////////
	// CONSTRUCTORS //
	/////////////////////////////////////

	/**
	 * Creates a game board, the layout of which is governed by the value of
	 * Controller.BOARD_SETUP field
	 * 
	 * @param player1Name
	 * @param player2Name
	 */
	public GameBoard(String player1Name, String player2Name) {

		int gameWidth = 8;
		board = new GamePiece[gameWidth][gameWidth];
		boolean firstHalf = true;
		String logMessage = "";

		// populate board
		int point = 0;

		switch (Controller.BOARD_SETUP) { // This switch statement sets up the board depending on the value of
											// Controller.BOARD_SETUP
		case "standard": // REAL GAMEBOARD
			for (int row = 0; row < gameWidth; row++) {
				for (int col = 0; col < gameWidth; col++) {
					if (row < (gameWidth / 2) - 1 || row > (gameWidth / 2)) {
						if (point % 2 == 0) {
							this.setSquare(row, col, this.currentPlayer);
						}
					}
					point++;
				}
				point++;
				if (firstHalf && row > (gameWidth / 2) - 1) {
					this.nextPlayer();
					firstHalf = false;
				}
			}
			this.currentPlayer = 0;
			logMessage = "Dynamic GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case "test":
			for (int row = 0; row < gameWidth; row++) {
				for (int col = 0; col < gameWidth; col++) {
					if (row > (gameWidth / 2) + 1 || row < (gameWidth / 2) - 2) {
						if (point % 2 == 0) {
							this.setSquare(row, col, this.currentPlayer);
						}
					}
					point++;
				}
				point++;
				if (firstHalf && row > (gameWidth / 2) - 1) {
					this.currentPlayer++;
					firstHalf = false;
				}
			}
			break;
		case "jumping1":
			// Jumping Enforcement test board
			this.setSquare(1, 1, new GamePiece(0));
			this.setSquare(2, 2, new GamePiece(1));
			this.setSquare(4, 4, new GamePiece(1));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";

			break;
		case "jumping2":
			// jumping enforcement test board 2
			this.setSquare(2, 0, new GamePiece(0));
			this.setSquare(3, 1, new GamePiece(0));
			this.setSquare(1, 1, new GamePiece(0));
			this.setSquare(0, 2, new GamePiece(0));
			// this.setSquare(1, 3, new GamePiece(0));
			this.setSquare(0, 0, new GamePiece(0));
			this.setSquare(2, 2, new GamePiece(1));
			this.setSquare(0, 4, new GamePiece(0));
			// this.setSquare(6, 4, new GamePiece(1));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";

			break;
		case "kingjump":
			// king jump enforcement test board
			this.setSquare(6, 3, new GamePiece(1));
			this.setSquare(7, 2, new GamePiece(0));
			getSquare(7, 2).setToKing();
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case "multiplejumps1":
			// testing jump chain
			this.setSquare(0, 0, new GamePiece(0));
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(3, 1, new GamePiece(1));
			this.setSquare(5, 1, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			this.setSquare(7, 3, new GamePiece(1));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case "multiplejumps2":
			// testing jump chain 2
			this.setSquare(0, 2, new GamePiece(0));
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(3, 1, new GamePiece(1));
			this.setSquare(5, 1, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case "multiplejumpsagainstedge":
			// testing hasAttack recognises edges cannot be attacked
			this.setSquare(0, 0, new GamePiece(0));
			this.setSquare(2, 0, new GamePiece(1));
			this.setSquare(4, 0, new GamePiece(1));
			this.setSquare(6, 0, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			this.setSquare(0, 2, new GamePiece(0));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case "blackattackedge":
			// testing black attacking edge
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(2, 0, new GamePiece(0));
			this.setSquare(6, 6, new GamePiece(0));
			break;
		case "kingdirectionattack":
			// testing kings bidirectionality
			this.setSquare(3, 3, new GamePiece(0));
			this.setSquare(4, 2, new GamePiece(1));
			this.setSquare(4, 4, new GamePiece(1));
			getSquare(3, 3).setToKing();
			break;
		case "cs50demo":
			// demo board to demonstrate win, capture, and jump enforcement
			this.setSquare(2, 4, 0);
			this.setSquare(5, 5, 1);
			this.setSquare(3, 5, 1);
			// this.setSquare(7, 5, new GamePiece(1));
			// this.setSquare(6, 2, 1);
			// this.setSquare(4, 0, 1);

			// this.setSquare(7, 0, 1);
		case "validMoveCheck":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					this.setSquare(i, j, 0);
				}
			}
			this.setSquare(1, 3, 1);
			this.clearSquare(new int[] { 2, 4 });
			// this.setSquare(1, 4, point)
			break;
		case "validMoveCheck2":
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					this.setSquare(i, j, 0);
				}
			}
			this.setSquare(0, 0, 1);
			//this.clearSquare(new int[] { 2, 4 });
			break;
		}

		// reset currentPlayer

		this.setPlayerName(0, player1Name);
		this.setPlayerName(1, player2Name);

		logMessage += String.format("[TEST_MODE=%s ; TIMERS_ACTIVE=%s ; TEST_BOARD=%s]", Controller.TEST_MODE,
				Controller.TIMERS_DEACTIVATED, Controller.BOARD_SETUP);
		Controller.log.add(logMessage);
	}

	///////////////////////////////////////
	// MOVE OPERATION //
	/////////////////////////////////////

	public boolean checkPlayerHasValidMoves() {
		// TODO check player has valid moves
		boolean hasValidMove = false;
		for (int row = 0; row < board.length && !hasValidMove; row++) {
			for (int col = 0; col < board[row].length && !hasValidMove; col++) {
				GamePiece tmp = getSquare(row, col);
				int start, end;
				if (tmp != null && tmp.isKing()) {
					start = 0;
					end = 4;
				} else {
					start = (this.currentPlayer == 0) ? 0 : 2;
					end = (this.currentPlayer == 0) ? 2 : 4;
				}
				if (tmp != null && tmp.getTeam() == this.currentPlayer) {
					int[] moves = move_generateMoveList(new int[] { row, col });
					for (int i = start; i < end; i++) {
						if (moves[i] == 2) {
							hasValidMove = true;
							break;
						} else if (moves[i] == ((this.currentPlayer == 0) ? 1 : 0)) {
							int[] modifiers = move_generateVectorModifiers(i);
							int[] farSideOfPiece = new int[] { row + (modifiers[0] * 2), col + (modifiers[1] * 2) };
							GamePiece otherSide = getSquare(farSideOfPiece);
							if (otherSide == null) {
								hasValidMove = true;
								break;
							}
							/*
							 * int[] farSideOfEnemyPiece = new int[] { s[0] + (modifiers[0] * 2), s[1] +
							 * (modifiers[1] * 2) }; if (farSideOfEnemyPiece[0] >= 0 &&
							 * farSideOfEnemyPiece[0] <= 7 && farSideOfEnemyPiece[1] >= 0 &&
							 * farSideOfEnemyPiece[1] <= 7) { // if (s[0] + modifiers[0] < 0 || s[0] +
							 * modifiers[0] > 7 || s[1] + modifiers[1] // < 0 && s[1] + modifiers[1] > 7) {
							 * // if (s[0] != 0 && s[0] != 7 && s[1] != 0 && s[1] != 7) { if
							 * (this.getSquare(farSideOfEnemyPiece) == null) { // if far side square is
							 * empty then attack is // possible hasAttack = true; break; } }
							 */

						}
					}
				}
			}
		}
		return hasValidMove;
	}

	/**
	 * This adjudicates a players move from start to finish, including any jump
	 * enforcement.
	 * 
	 * @param coords an int[][] containing the players piece coords at index 0, and
	 *               the players destination coords at index 1.
	 * @return A boolean - continue the game?
	 */
	public boolean executeMove(int[][] coords) {
		if (this.move_operation(coords)) { // if the move was successful
			if (GamePiece.getTotalPieces(0) == 0 || GamePiece.getTotalPieces(1) == 0) { // if a player has lost all
																						// their pieces
				return false;
			}
			// TODO check if players have no moves available , which is a loss
			if (!move_hasAttack(coords[1]) || getSquare(coords[1]).isKing())
				nextPlayer(); // end this players move by changing active player
		} else {
			if (Controller.getReturnMessage().isEmpty()) {
				Controller.setReturnMessage("That move was not acceptable. Please try again!");
			}
		}
		return true;
	}

	/**
	 * Move a piece if its legally allowed
	 * 
	 * @param coords an int[][], holding the players own-piece selection at index 0
	 *               and the destination square at index 1.
	 * @return A boolean - This move was made successfully?
	 */
	private boolean move_operation(int[][] coords) {
		int[] s = coords[0];
		int[] d = coords[1];
		GamePiece sourcePiece = this.getSquare(s);
		GamePiece destinationPiece = this.getSquare(d);

		int vectorVert = d[0] - s[0];
		int vectorHori = d[1] - s[1];
		boolean capture = false;
		int rowToCheck = 0;
		int colToCheck = 0;

		// is move in the correct direction for this player (ignored if king)
		if (sourcePiece != null && !sourcePiece.isKing()) {
			int directionModifier = (this.getCurrentPlayer() == 0) ? 1 : -1;
			if ((this.getCurrentPlayer() == 0 && vectorVert < directionModifier)
					|| (this.getCurrentPlayer() == 1 && vectorVert > directionModifier)) {
				Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player "
						+ this.getCurrentPlayer() + " DENIED - move in wrong direction");
				return false;
			}
		}

		// General Check Block
		if (sourcePiece == null // if source square is empty
				|| currentPlayer != sourcePiece.getTeam() // if source square is other team's piece
				|| destinationPiece != null // if the destination is NOT empty
				|| Math.abs(vectorVert) > 2 || Math.abs(vectorHori) > 2 // if move greater than 2 squares away
				|| Math.abs(vectorVert) != Math.abs(vectorHori)) { // if not a diagonal move
			Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player "
					+ this.getCurrentPlayer() + " DENIED - General Check Block fail");
			return false; // THEN RETURN FALSE
		}

		// Check intervening square if it is a 2-distance move
		if (Math.abs(vectorVert) == 2) {
			rowToCheck = s[0] + (vectorVert / 2);
			colToCheck = s[1] + (vectorHori / 2);
			GamePiece check = this.getSquare(rowToCheck, colToCheck);
			if (check != null) {
				if (check.getTeam() == currentPlayer) {
					return false;
				} else {
					capture = true;
				}
				// System.out.println(check.toVisualString());
			} else {
				Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player "
						+ this.getCurrentPlayer() + " DENIED - Null Piece Check fail");
				return false;
			}
		}
		ArrayList<String> attacks = checkPlayersPiecesForAttacks();

		// at this point basic checks are done - now to check for jump enforcement
		boolean hasAttack = move_hasAttack(s);
		if (attacks.size() > 0) {
			if (!attacks.contains(Controller.convertCoords(s))) {
				String message = String
						.format("You have attacks at the following squares (if you can attack, you must attack)%n");
				for (String i : attacks) {
					message += i + " ";
				}
				Controller.setReturnMessage(String.format(message + "%n"));
				return false;
			}
		}

		// if piece hasAttack, but isn't a capture move then move is invalid
		if (hasAttack && !capture) {
			Controller.setReturnMessage("You have an attack move, you must make an attack!");
			return false;
		} else if (hasAttack && capture) {
			this.move_capture(new int[] { rowToCheck, colToCheck });
		}

		// move piece from s to d, clearing s afterwards
		this.setSquare(d, sourcePiece);
		GamePiece check = this.getSquare(d);
		this.clearSquare(s);

		// set as king if necessary
		if ((check.getTeam() == 0 && d[0] == 7) || (check.getTeam() == 1 && d[0] == 0)) {
			check.setToKing();
			Controller.setReturnMessage("Piece upgraded to king!");
		}
		Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player "
				+ this.getCurrentPlayer() + " accepted");

		return true;
	}

	/**
	 * @return return an {@code ArrayList<String>} containing all the players pieces
	 *         that have a valid attack (in format A1, H8 etc)
	 */
	public ArrayList<String> checkPlayersPiecesForAttacks() {
		ArrayList<String> attacks = new ArrayList<>(20);
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				GamePiece tmp = getSquare(row, col);
				if (tmp != null && tmp.getTeam() == currentPlayer) {
					if (move_hasAttack(new int[] { row, col })) {
						attacks.add(Controller.convertCoords(new int[] { row, col }));
					}
				}
			}
		}
		return attacks;
	}

	/**
	 * @param i 0 to 3 - representing clockwise top left, top right, bottom right
	 *          and bottom left squares around a central point.
	 * @return The vector modifiers for each direction
	 */
	private int[] move_generateVectorModifiers(int i) {
		int[] modifiers = new int[2];
		switch (i) { // set directional modifier
		case 0:
			// modifierVertical = 1;
			// modifierHorizontal = -1;
			modifiers[0] = 1;
			modifiers[1] = -1;
			break;
		case 1:
			// modifierVertical = 1;
			// modifierHorizontal = 1;
			modifiers[0] = 1;
			modifiers[1] = 1;
			break;
		case 2:
			// modifierVertical = -1;
			// modifierHorizontal = 1;
			modifiers[0] = -1;
			modifiers[1] = 1;
			break;
		case 3:
			// modifierVertical = -1;
			// modifierHorizontal = -1;
			modifiers[0] = -1;
			modifiers[1] = -1;
			break;
		}
		return modifiers;
	}

	/**
	 * Generates a int[4] containing all the possible moves for piece @ s. clockwise
	 * top left to bottom left<br>
	 * 
	 * @param s (for source) - An int[2] containing board coordinates
	 * @return int[4] <br>
	 *         <hr>
	 *         <b>INDICES (clockwise)</b><br>
	 *         0 = top left<br>
	 *         1 = top right<br>
	 *         2 = bottom right<br>
	 *         3 = bottom left<br>
	 *         <hr>
	 *         <b>VALUES</b><br>
	 *         -1 = off grid<br>
	 *         0 = black piece here<br>
	 *         1 = white piece here<br>
	 *         2 = empty square<br>
	 *         <hr>
	 */
	private int[] move_generateMoveList(int[] s) {
		// check all possible moves and store in a HashMap
		GamePiece selected = null;
		// int[] key = null;
		int value = 0;

		int[] fourDirections = new int[4];
		for (int i = 0; i < 4; i++) {

			// set vector modifiers
			int[] modifiers = move_generateVectorModifiers(i);
			int modifierVertical = modifiers[0];
			int modifierHorizontal = modifiers[1];

			// select the square to check
			int row = s[0] + modifierVertical;
			int col = s[1] + modifierHorizontal;
			// key = new int[] { row, col };

			selected = this.getSquare(row, col);
			if (selected == null) { // if piece is null
				if (row < 0 || row > 7 || col < 0 || col > 7) {
					value = -1; // cursor is off the board
				} else {
					value = 2; // square is empty
				}
			} else { // if piece is not null
				value = selected.getTeam(); // team number of the piece
			}

			// add to array
			fourDirections[i] = value;
		}
		return fourDirections;
	}

	/**
	 * Capture A GamePiece
	 * 
	 * @param coords - board array row & column indices
	 */
	private void move_capture(int[] coords) {
		GamePiece e = getSquare(coords);
		Controller.log.add("GamePiece " + e + " captured!");
		captured.add(e);
		Controller.log.add(GamePiece.pieceCount());
		e.removePieceFromBoard();
		clearSquare(coords);
	}

	/**
	 * Checks if the piece at coordinate s has attacks (jumps) available or not
	 * 
	 * @param s
	 * @return a boolean - this piece has an attack?
	 */
	private boolean move_hasAttack(int[] s) {
		boolean hasAttack = false;
		int start;
		int end;
		if (getSquare(s).isKing()) {
			start = 0;
			end = 4;
		} else {
			start = (this.currentPlayer == 0) ? 0 : 2;
			end = (this.currentPlayer == 0) ? 2 : 4;
		}
		int moves[] = move_generateMoveList(s);
		for (int i = start; i < end; i++) { // find the enemies
			if (moves[i] == ((this.getCurrentPlayer() == 0) ? 1 : 0)) { // found an adjacent enemy piece
				int[] modifiers = move_generateVectorModifiers(i);
				int[] farSideOfEnemyPiece = new int[] { s[0] + (modifiers[0] * 2), s[1] + (modifiers[1] * 2) };
				if (farSideOfEnemyPiece[0] >= 0 && farSideOfEnemyPiece[0] <= 7 && farSideOfEnemyPiece[1] >= 0
						&& farSideOfEnemyPiece[1] <= 7) {
					// if (s[0] + modifiers[0] < 0 || s[0] + modifiers[0] > 7 || s[1] + modifiers[1]
					// < 0 && s[1] + modifiers[1] > 7) {
					// if (s[0] != 0 && s[0] != 7 && s[1] != 0 && s[1] != 7) {
					if (this.getSquare(farSideOfEnemyPiece) == null) { // if far side square is empty then attack is
																		// possible
						hasAttack = true;
						break;
					}
				}
			}
		}
		return hasAttack;

		// //TODO has an attack check
		// GamePiece piece = this.getSquare(coords);
		// int start = 0;
		// int end = 4;
		//
		// if (!piece.isKing()) {
		// start = (piece.getTeam() == 0) ? 0 : 2;
		// end = (piece.getTeam() == 0) ? 2 : 4;
		// } else {
		//
		// }
		//
		// int modifierVertical;
		// int modifierHorizontal;
		//
		// int[] moves = move_generateMoveList(coords); // generate 4 direction checks
		// for this destination square
		// for (int i = start; i < end; i++) {
		// modifierVertical = -99; // default value
		// modifierHorizontal = -99; // default value
		// if (moves[i] == ((this.currentPlayer == 0) ? 1 : 0)) { // if another teams
		// piece is there
		// int[] modifiers = move_generateVectorModifiers(i);
		// modifierVertical = modifiers[0];
		// modifierHorizontal = modifiers[1];
		//
		// }
		// if (modifierHorizontal != -99 && modifierVertical != -99) { // if not default
		// value
		// int row = coords[0] + modifierVertical;
		// int col = coords[1] + modifierHorizontal;
		// int[] emptyAfterEnemy = move_generateMoveList(new int[] { row, col });
		// // if same direction has empty square then there is a move
		// if (emptyAfterEnemy[i] == 2) {
		// return true;
		// }
		// }
		// }
		//
		// return false;
	}

	///////////////////////////////////////
	// UTILITY //
	/////////////////////////////////////

	/**
	 * Display the board
	 */
	public void displayBoard() {
		String border = "       " + ((this.board.length == 8) ? "-".repeat(127) : "-".repeat(159));
		System.out.println(border);
		for (int i = board.length - 1; i >= 0; i--) {
			for (int k = 0; k <= 3; k++) {
				if (k == 1) {
					System.out.printf(" (%d) ", (Controller.TEST_MODE) ? i : i + 1);
				} else {
					System.out.printf("     ", (Controller.TEST_MODE) ? i : i + 1);

				}
				for (int j = 0; j < board[i].length; j++) {

					if (k == 0 || k == 2) {
						if (board[i][j] instanceof GamePiece) {
							if (k == 2 && board[i][j].isKing()) {
								System.out.printf(" | %13s", getSquare(i, j).toVisualString());
							} else {
								System.out.printf(" | %13s", "");
							}
						} else {
							System.out.printf(" | %13s", "");
						}
					} else if (k == 1) {
						if (board[i][j] instanceof GamePiece) {
							System.out.printf(" | %13s", getSquare(i, j).toVisualString());
						} else {
							System.out.printf(" | %13s", "");
						}
					} else if (k == 3) {
						if (Controller.TEST_MODE) {
							System.out.printf(" | %7s/%3s  ", i + "," + j, (char) (j + 65) + "" + (char) (i + 49));
						} else {
							System.out.printf(" |   %7s    ", "(" + (char) (j + 65) + (char) (i + 49) + ")");
						}
					}
					if (j == this.board.length - 1) {
						System.out.printf(" |");
					}

				}
				System.out.println();

			}
			System.out.println(border);
		}
		System.out.printf("      ");
		for (int i = 0; i < board.length; i++) {
			if (Controller.TEST_MODE) {
				System.out.printf("|     (%d/%c)     ", i, (char) i + 65);
			} else {
				System.out.printf("|      (%c)      ", (char) i + 65);
			}
		}
		System.out.println("|");
		System.out.println();
		if (Controller.TEST_MODE) {
			System.out.printf("--- TEST MODE ACTIVE%n");
		}
		if (!Controller.BOARD_SETUP.equals("standard")) {
			System.out.printf("--- TEST BOARD ACTIVE - %s%n", Controller.BOARD_SETUP);
		}
		if (Controller.TIMERS_DEACTIVATED) {
			System.out.printf("--- ALL TIMERS DE-ACTIVATED%n", "");
		}
		System.out.println();
		Controller.log.add("Board displayed");
	}

	public void printCaptured() {
		int black = 0;
		int white = 0;
		for (GamePiece i : captured) {
			if (i.getTeam() == 0) {
				black++;
			} else {
				white++;
			}
		}
		String returnString = String
				.format("Captured Pieces:%n\tBlack captured %d pieces%n\tWhite captured %d pieces%n", white, black);
		Controller.setReturnMessage(returnString);
	}

	/**
	 * Change to next player
	 */
	public void nextPlayer() {
		if (this.currentPlayer == 0) {
			this.currentPlayer = 1;
		} else {
			this.currentPlayer = 0;
		}
	}

	/**
	 * Set this element in the board GamePiece 2-d array to null
	 * 
	 * @param coords an int[2] containing row and column indices
	 */
	private void clearSquare(int[] coords) {
		this.board[coords[0]][coords[1]] = null;
	}

	///////////////////////////////////////
	// GETTERS N SETTERS //
	/////////////////////////////////////

	/**
	 * Set the player name in the playerNames String array at index player
	 * 
	 * @param player The index to use for the playerNames String array
	 * @param name   The player's name
	 */
	public void setPlayerName(int player, String name) {
		if (player >= 0 && player < this.playerNames.length) {
			this.playerNames[player] = name;
		} else {
			Controller.log.add("Invalid setPlayerName", true);
		}
	}

	/**
	 * Set the square
	 * 
	 * @param row
	 * @param col
	 * @param g   - A GamePiece
	 */
	private void setSquare(int row, int col, GamePiece g) {
		try {
			this.board[row][col] = g;
		} catch (Exception e) {
			Controller.log.add("Bad setSquare call! [row = " + row + "] [col = " + col + "] [GamePiece = " + g + "]",
					true);
		}
	}

	/**
	 * Set the square
	 * 
	 * @param row
	 * @param col
	 * @param team - 0 for black, 1 for white
	 */
	private void setSquare(int row, int col, int team) {
		this.setSquare(row, col, new GamePiece(team));
	}

	/**
	 * Set the square
	 * 
	 * @param s - an int[2] board coordinates
	 * @param g - A GamePiece to overwrite square with
	 */
	private void setSquare(int[] s, GamePiece g) {
		this.setSquare(s[0], s[1], g);
	}

	/**
	 * 
	 * @return the current player<br>
	 *         0 for Black<br>
	 *         1 for White
	 */
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Get the player name from the playerNames String array
	 * 
	 * @param player the index to use for the playerNames String array
	 * @return the player's name or "Unknown Player" if an unacceptable argument is
	 *         passed
	 */
	public String getPlayerName(int player) {
		if (player >= 0 && player < this.playerNames.length) {
			return this.playerNames[player];
		} else {
			Controller.log.add("Invalid argument passed to GameBoard.getPlayerName(int player): " + player);
			return "Unknown Player";
		}
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return Get the piece - or null
	 */
	private GamePiece getSquare(int row, int col) {
		if (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
			return board[row][col];
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param coords - an int[2] board coordinates
	 * @return Get the piece - or null
	 */
	private GamePiece getSquare(int[] coords) {
		return this.getSquare(coords[0], coords[1]);
	}

}
