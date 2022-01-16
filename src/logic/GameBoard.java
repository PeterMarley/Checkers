package logic;

import java.util.ArrayList;

import logic.Enums.*;

/**
 * <hr>
 * <b>currentPlayer:</b><br>
 * 0 = Black<br>
 * 1 = White
 * <hr>
 * @author Peter Marley
 * @projectTitle Checkers V1
 * @City Belfast
 * @Country Ireland
 * @maxLove cs50x - You changed my life <3
 *
 */
public class GameBoard {

	private GamePiece[][] board;											// the "physical" board itself
	private ArrayList<GamePiece> captured = new ArrayList<GamePiece>(40); 	// captured pieces stored here
	private String[] playerNames = new String[2];							// stores both players names
	private int currentPlayer;												// int representing current player (0 = black, 1 = white)
	private Modifier[] modifierArray = Modifier.values();				// This holds the GameBoard array index modifiers

	///////////////////////////////////////
	// CONSTRUCTORS 					//
	/////////////////////////////////////

	/**
	 * Creates a game board, the layout of which is governed by the value of Controller.BOARD_SETUP field
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

		switch (Main.BOARD_SETUP) { // This switch statement sets up the board depending on the value of
											// Controller.BOARD_SETUP
		case STANDARD: // REAL GAMEBOARD
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
		case TEST:
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
		case JUMPING1:
			// Jumping Enforcement test board
			this.setSquare(1, 1, new GamePiece(0));
			this.setSquare(2, 2, new GamePiece(1));
			this.setSquare(4, 4, new GamePiece(1));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";

			break;
		case JUMPING2:
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
		case KINGJUMP:
			// king jump enforcement test board
			this.setSquare(6, 3, new GamePiece(1));
			this.setSquare(7, 2, new GamePiece(0));
			getSquare(7, 2).setToKing();
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case MULTIPLEJUMPS1:
			// testing jump chain
			this.setSquare(0, 0, new GamePiece(0));
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(3, 1, new GamePiece(1));
			this.setSquare(5, 1, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			this.setSquare(7, 3, new GamePiece(1));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case MULTIPLEJUMPS2:
			// testing jump chain 2
			this.setSquare(0, 2, new GamePiece(0));
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(3, 1, new GamePiece(1));
			this.setSquare(5, 1, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case MULTIPLEJUMPSAGAINSTEDGE:
			// testing hasAttack recognises edges cannot be attacked
			this.setSquare(0, 0, new GamePiece(0));
			this.setSquare(2, 0, new GamePiece(1));
			this.setSquare(4, 0, new GamePiece(1));
			this.setSquare(6, 0, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			this.setSquare(0, 2, new GamePiece(0));
			logMessage = "Test GameBoard of width " + gameWidth + " created successfully. ";
			break;
		case BLACKATTACKEDGE:
			// testing black attacking edge
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(2, 0, new GamePiece(0));
			this.setSquare(6, 6, new GamePiece(0));
			break;
		case KINGDIRECTIONALATTACK:
			// testing kings bidirectionality
			this.setSquare(3, 3, new GamePiece(0));
			this.setSquare(4, 2, new GamePiece(1));
			this.setSquare(4, 4, new GamePiece(1));
			getSquare(3, 3).setToKing();
			break;
		case CS50DEMO:
			// demo board to demonstrate win, capture, and jump enforcement
			this.setSquare(2, 4, 0);
			this.setSquare(5, 5, 1);
			this.setSquare(3, 5, 1);
			// this.setSquare(7, 5, new GamePiece(1));
			// this.setSquare(6, 2, 1);
			// this.setSquare(4, 0, 1);

			// this.setSquare(7, 0, 1);
			break;
		case VALIDMOVECHECK:
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					this.setSquare(i, j, 0);
				}
			}
			this.setSquare(1, 3, 1);
			this.clearSquare(new int[] { 2, 4 });
			// this.setSquare(1, 4, point)
			break;
		case VALIDMOVECHECK2:
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					this.setSquare(i, j, 0);
				}
			}
			this.setSquare(0, 0, 1);
			// this.clearSquare(new int[] { 2, 4 });
			break;
		case VALIDMOVECHECK3:
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					this.setSquare(i, j, 0);
				}
			}

			this.setSquare(1, 3, 1);
			this.setSquare(1, 4, 1);
			this.setSquare(1, 5, 1);
			this.setSquare(2, 3, 1);
			this.setSquare(2, 5, 1);
			this.setSquare(3, 3, 1);
			this.setSquare(3, 4, 1);
			this.setSquare(3, 5, 1);

			this.setSquare(2, 4, 0);

			this.setSquare(0, 0, 0);
			this.setSquare(7, 0, 1);

			this.clearSquare(new int[] { 4, 6 });
			this.clearSquare(new int[] { 3, 3 });
			break;
		}

		// reset currentPlayer

		this.setPlayerName(0, player1Name);
		this.setPlayerName(1, player2Name);

		//logMessage += String.format("[TEST_MODE=%s ; TIMERS_ACTIVE=%s ; TEST_BOARD=%s]", TestVariables.SKIP_INTRO, TestVariables.TIMERS_DEACTIVATED,
		//		TestVariables.BOARD_SETUP);
		//Controller.log.add(logMessage);
	}

	///////////////////////////////////////
	// MOVE OPERATION 					//
	/////////////////////////////////////

	public boolean checkPlayerHasValidMoves() {
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
							int[] modifiers = modifierArray[i].get();
							int[] farSideOfPiece = new int[] { row + (modifiers[0] * 2), col + (modifiers[1] * 2) };
							if (farSideOfPiece[0] > 7 || farSideOfPiece[0] < 0 || farSideOfPiece[1] > 7 || farSideOfPiece[1] < 0) {
								break;
							}
							GamePiece otherSide = getSquare(farSideOfPiece);
							if (otherSide == null) {
								hasValidMove = true;
								break;
							}
							/*
							 * int[] farSideOfEnemyPiece = new int[] { s[0] + (modifiers[0] * 2), s[1] + (modifiers[1] * 2) }; if
							 * (farSideOfEnemyPiece[0] >= 0 && farSideOfEnemyPiece[0] <= 7 && farSideOfEnemyPiece[1] >= 0 && farSideOfEnemyPiece[1] <=
							 * 7) { // if (s[0] + modifiers[0] < 0 || s[0] + modifiers[0] > 7 || s[1] + modifiers[1] // < 0 && s[1] + modifiers[1] >
							 * 7) { // if (s[0] != 0 && s[0] != 7 && s[1] != 0 && s[1] != 7) { if (this.getSquare(farSideOfEnemyPiece) == null) { //
							 * if far side square is empty then attack is // possible hasAttack = true; break; } }
							 */

						}
					}
				}
			}
		}
		return hasValidMove;
	}

	/**
	 * Move a piece if its legally allowed
	 * 
	 * @param coords an int[][], holding the players own-piece selection at index 0 and the destination square at index 1.
	 * @return A boolean - This move was made successfully?
	 */
	public boolean move_operation(int[][] coords) {
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
				//Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player " + this.getCurrentPlayer()
				//		+ " DENIED - move in wrong direction");
				return false;
			}
		}

		// General Check Block
		if (sourcePiece == null // if source square is empty
				|| currentPlayer != sourcePiece.getTeam() // if source square is other team's piece
				|| destinationPiece != null // if the destination is NOT empty
				|| Math.abs(vectorVert) > 2 || Math.abs(vectorHori) > 2 // if move greater than 2 squares away
				|| Math.abs(vectorVert) != Math.abs(vectorHori)) { // if not a diagonal move
			//Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player " + this.getCurrentPlayer()
			//		+ " DENIED - General Check Block fail");
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
				//Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player " + this.getCurrentPlayer()
				//		+ " DENIED - Null Piece Check fail");
				return false;
			}
		}
		ArrayList<String> attacks = checkPlayersPiecesForAttacks();

		// at this point basic checks are done - now to check for jump enforcement
		if (attacks.size() > 0) {
			if (!attacks.contains(Main.convertCoords(s))) {
				String message = String.format("You have attacks at the following squares (if you can attack, you must attack)%n");
				for (String i : attacks) {
					message += i + " ";
				}
				return false;
			}
		}

		boolean hasAttack = move_hasAttack(s);

		// if piece hasAttack, but isn't a capture move then move is invalid
		if (hasAttack && !capture) {
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
		}
		//Controller.log.add("Move from " + Arrays.toString(s) + " to " + Arrays.toString(d) + " by player " + this.getCurrentPlayer() + " accepted");

		return true;
	}

	/**
	 * @return return an {@code ArrayList<String>} containing all the players pieces that have a valid attack (in format A1, H8 etc)
	 */
	public ArrayList<String> checkPlayersPiecesForAttacks() {
		ArrayList<String> attacks = new ArrayList<>(20);
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				GamePiece tmp = getSquare(row, col);
				if (tmp != null && tmp.getTeam() == currentPlayer) {
					if (move_hasAttack(new int[] { row, col })) {
						attacks.add(Main.convertCoords(new int[] { row, col }));
					}
				}
			}
		}
		return attacks;
	}

	/**
	 * Generates a int[4] containing all the possible moves for piece @ s. clockwise top left to bottom left<br>
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
			int[] modifiers = modifierArray[i].get();
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
		//Controller.log.add("GamePiece " + e + " captured!");
		captured.add(e);
		//Controller.log.add(GamePiece.enumeratePiecesOnBoard());
		e.removePieceFromCount();
		clearSquare(coords);
	}

	/**
	 * Checks if the piece at coordinate s has attacks (jumps) available or not
	 * 
	 * @param s
	 * @return a boolean - this piece has an attack?
	 */
	public boolean move_hasAttack(int[] s) {
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
				int[] modifiers = modifierArray[i].get();
				int[] farSideOfEnemyPiece = new int[] { s[0] + (modifiers[0] * 2), s[1] + (modifiers[1] * 2) };
				if (farSideOfEnemyPiece[0] >= 0 && farSideOfEnemyPiece[0] <= 7 && farSideOfEnemyPiece[1] >= 0 && farSideOfEnemyPiece[1] <= 7) {
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
	}

	///////////////////////////////////////
	// UTILITY 							//
	/////////////////////////////////////

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
	// GETTERS N SETTERS 				//
	/////////////////////////////////////

	/**
	 * Set the player name in the playerNames String array at index player
	 * 
	 * @param player The index to use for the playerNames String array
	 * @param name The player's name
	 */
	public void setPlayerName(int player, String name) {
		if (player >= 0 && player < this.playerNames.length) {
			this.playerNames[player] = name;
		} else {
			//Controller.log.add("Invalid setPlayerName", true);
		}
	}

	/**
	 * Set the square
	 * 
	 * @param row
	 * @param col
	 * @param g - A GamePiece
	 */
	private void setSquare(int row, int col, GamePiece g) {
		try {
			this.board[row][col] = g;
		} catch (Exception e) {
			//Controller.log.add("Bad setSquare call! [row = " + row + "] [col = " + col + "] [GamePiece = " + g + "]", true);
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
	 * @return the player's name or "Unknown Player" if an unacceptable argument is passed
	 */
	public String getPlayerName(int player) {
		if (player >= 0 && player < this.playerNames.length) {
			return this.playerNames[player];
		} else {
			//Controller.log.add("Invalid argument passed to GameBoard.getPlayerName(int player): " + player);
			return "Unknown Player";
		}
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return Get the piece - or null
	 */
	public GamePiece getSquare(int row, int col) {
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
	public GamePiece getSquare(int[] coords) {
		return this.getSquare(coords[0], coords[1]);
	}

}