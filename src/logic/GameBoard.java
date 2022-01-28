package logic;

import java.awt.Color;
import java.util.ArrayList;

import logic.Enums.*;

/**
 * <hr>
 * <b>currentPlayer:</b><br>
 * 0 = Black<br>
 * 1 = White
 * <hr>
 * 
 * @author Peter Marley
 * @projectTitle Checkers V1
 * @City Belfast
 * @Country Ireland
 * @maxLove cs50x - You changed my life <3
 *
 */
public class GameBoard {

	///////////////////////////////////////
	// INSTANCE FIELDS					//
	/////////////////////////////////////

	private GamePiece[][] board;				// the "physical" board itself
	private String[] playerNames;				// stores both players names
	private ArrayList<GamePiece> captured; 		// captured pieces stored here
	private int currentPlayer;					// int representing current player (0 = black, 1 = white)
	private Modifier[] modifierArray;			// This holds the GameBoard array index modifiers
	private String returnMessage;				// This holds a return message for displaying move operation fail messages
	private Color returnMessageBgColour;		// This holds the background colour for the return message
	private Integer winner;						// This holds the winners name
	private boolean isCaptureMove;				// Was the preceding move a capture?

	///////////////////////////////////////
	// CONSTRUCTOR	 					//
	/////////////////////////////////////

	/**
	 * Creates a game board, the layout of which is governed by the value of Controller.BOARD_SETUP field. The player names are initially set to
	 * "Unset Player Name 1" & "Unset Player Name 2" and are re-set later to correct names
	 */
	public GameBoard() {
		this.clearReturnMessage();
		this.returnMessageBgColour = Palette.DARKEST.get();
		this.setBoardArray();
		this.setPlayerNames("Unset Player Name 1", "Unset Player Name 2");
		this.setCaptured();
		this.currentPlayer = 0;
		this.modifierArray = Modifier.values();
	}

	///////////////////////////////////////
	// MOVE LOGIC	 					//
	/////////////////////////////////////

	/**
	 * Move a piece (if its legally allowed)
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
		this.setCaptureMove(false);
		int rowToCheck = 0;
		int colToCheck = 0;
		Color errorMessageColor = Palette.ERROR.get();

		// is move in the correct direction for this player (ignored if king)
		if (sourcePiece != null && !sourcePiece.isKing()) {
			int directionModifier = (this.getCurrentPlayer() == 0) ? 1 : -1;
			if ((this.getCurrentPlayer() == 0 && vectorVert < directionModifier)
					|| (this.getCurrentPlayer() == 1 && vectorVert > directionModifier)) {
				this.setReturnMessage("That's the wrong direction!", errorMessageColor);
				System.out.printf("Move operation complete; but move direction was not valid%n" + Checkers.CONSOLE_SEPARATOR + "%n");
				return false;
			}
		}
		// TODO re-do logic here so it bottom panel of game window can show multiple messages!
		// General Checks
		if (sourcePiece == null) {											// if source square is empty
			this.setReturnMessage("You didn't select a piece!", errorMessageColor);
			System.out.printf("Move operation complete; but the player selected an empty square%n" + Checkers.CONSOLE_SEPARATOR + "%n");
			return false;
		}

		//		if (currentPlayer != sourcePiece.getTeam()) {						// if source square is other team's piece
		//			this.setReturnMessage("You selected an opponents piece!", errorMessageColor);
		//			return false;
		//		}

		if (destinationPiece != null) {										// if the destination is NOT empty
			this.setReturnMessage("The destination square was not empty!", errorMessageColor);
			return false;
		}

		if (Math.abs(vectorVert) > 2 || Math.abs(vectorHori) > 2) {			// if move greater than 2 squares away
			this.setReturnMessage("You cannot move that far!", errorMessageColor);
			return false;
		}

		if (Math.abs(vectorVert) != Math.abs(vectorHori)) {					// if not a diagonal move
			this.setReturnMessage("You can only move diagonally!", errorMessageColor);
			return false;
		}

		// Check intervening square if it is a 2-distance move
		if (Math.abs(vectorVert) == 2) {
			rowToCheck = s[0] + (vectorVert / 2);
			colToCheck = s[1] + (vectorHori / 2);
			GamePiece pieceToCheck = this.getSquare(rowToCheck, colToCheck);
			if (pieceToCheck != null) {								// if intervening square is not empty
				if (pieceToCheck.getTeam() == currentPlayer) {		// if it is current players piece
					this.setReturnMessage("You can't capture your own piece!", errorMessageColor);
					return false;									//		return false
				} else {											// if it is other players piece
					this.setCaptureMove(true);						//		set capture to true

				}
			} else {												// if intervening square is empty
				this.setReturnMessage("You cannot move two squares unless capturing!", errorMessageColor);
				return false;										//		return false
			}
		}
		ArrayList<String> attacks = checkPlayersPiecesForAttacks();

		// at this point basic checks are done - now to check for jump enforcement
		if (attacks.size() > 0) {
			if (!attacks.contains(Checkers.convertCoords(s))) {			// if player has an attack somewhere, but their selection piece has no attack
				this.setReturnMessage("If you have an attack you must take it!", errorMessageColor);
				System.out.printf("Move operation complete; but player had an attack and did not take it%n" + Checkers.CONSOLE_SEPARATOR + "%n");

				return false;										//		return false
			}
		}

		boolean hasAttack = move_hasAttack(s);

		// if piece hasAttack, but isn't a capture move then move is invalid
		if (hasAttack && !isCaptureMove()) {
			this.setReturnMessage("If you have an attack you must take it!", errorMessageColor);
			System.out.printf("Move operation complete; but player had an attack and did not take it%n" + Checkers.CONSOLE_SEPARATOR + "%n");
			return false;
		} else if (hasAttack && isCaptureMove()) {
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
		this.setReturnMessage("Move successful! " + Checkers.convertCoords(s) + " to " + Checkers.convertCoords(d) + ".");
		System.out.printf("Move operation completed successfully!%n" + Checkers.CONSOLE_SEPARATOR + "%n");
		return true;
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

	/**
	 * @return return an {@code ArrayList<String>} containing all the players pieces that have a valid attack (in format A1, H8 etc)
	 */
	private ArrayList<String> checkPlayersPiecesForAttacks() {
		ArrayList<String> attacks = new ArrayList<>(20);
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				GamePiece tmp = getSquare(row, col);
				if (tmp != null && tmp.getTeam() == currentPlayer) {
					if (move_hasAttack(new int[] { row, col })) {
						attacks.add(Checkers.convertCoords(new int[] { row, col }));
					}
				}
			}
		}
		return attacks;
	}

	/**
	 * Checks for the loss condition "player has no valid moves"
	 * 
	 * @return A boolean - has the current player got valid moves available to them?
	 */
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

	///////////////////////////////////////
	// GETTERS N SETTERS 				//
	/////////////////////////////////////

	/**
	 * Set both player name Strings
	 * 
	 * @param player1
	 * @param player2
	 */
	public void setPlayerNames(String player1, String player2) {
		this.playerNames = new String[] { player1, player2 };
	}

	/**
	 * Set the square
	 * 
	 * @param row
	 * @param col
	 * @param g   - A GamePiece
	 */
	private void setSquare(int row, int col, GamePiece g) {
		if (row >= 0 && row < Sizes.CENTER_PANEL_SQUARES.get()
				&& col >= 0 && col < Sizes.CENTER_PANEL_SQUARES.get()) {
			this.board[row][col] = g;
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
	 * Set this element in the board GamePiece 2-d array to null
	 * 
	 * @param coords an int[2] containing row and column indices
	 */
	private void clearSquare(int[] coords) {
		setSquare(coords, null);
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
	 * @param index the index to use for the playerNames String array
	 * @return the player's name, or null if an unacceptable index argument is passed
	 */
	public String getPlayerName(int index) {
		if (index >= 0 && index < this.playerNames.length) {
			return this.playerNames[index];
		} else {
			return null;
		}
	}

	/**
	 * @return a String[2] - containing both player names
	 */
	public String[] getPlayerNames() {
		return this.playerNames;
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

	public void setCaptured() {
		this.captured = new ArrayList<GamePiece>(40);
	}

	/**
	 * @return the GamePiece[][]
	 */
	public GamePiece[][] getBoardArray() {
		return this.board;
	}
	
	public void setBoardArray() {
		boolean firstHalf = true;
		board = new GamePiece[Sizes.CENTER_PANEL_SQUARES.get()][Sizes.CENTER_PANEL_SQUARES.get()];

		// populate board
		int point = 0;
		int gameWidth = Sizes.CENTER_PANEL_SQUARES.get();
		switch (Checkers.BOARD_SETUP) { // This switch statement sets up the board depending on the value of
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

			break;
		case KINGJUMP:
			// king jump enforcement test board
			this.setSquare(6, 3, new GamePiece(1));
			this.setSquare(7, 2, new GamePiece(0));
			getSquare(7, 2).setToKing();
			break;
		case MULTIPLEJUMPS1:
			// testing jump chain
			this.setSquare(0, 0, new GamePiece(0));
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(3, 1, new GamePiece(1));
			this.setSquare(5, 1, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			this.setSquare(7, 3, new GamePiece(1));
			break;
		case MULTIPLEJUMPS2:
			// testing jump chain 2
			this.setSquare(0, 2, new GamePiece(0));
			this.setSquare(1, 1, new GamePiece(1));
			this.setSquare(3, 1, new GamePiece(1));
			this.setSquare(5, 1, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			break;
		case MULTIPLEJUMPSAGAINSTEDGE:
			// testing hasAttack recognises edges cannot be attacked
			this.setSquare(0, 0, new GamePiece(0));
			this.setSquare(2, 0, new GamePiece(1));
			this.setSquare(4, 0, new GamePiece(1));
			this.setSquare(6, 0, new GamePiece(1));
			this.setSquare(7, 7, new GamePiece(1));
			this.setSquare(0, 2, new GamePiece(0));
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
	}

	public void setReturnMessage(String message) {
		setReturnMessage(message, Palette.DARKEST.get());
	}

	public void setReturnMessage(String message, Color bgColor) {
		this.returnMessage = message;
		this.returnMessageBgColour = bgColor;
	}

	public String getReturnMessage() {
		return this.returnMessage;
	}

	public void clearReturnMessage() {
		this.setReturnMessage("");
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public Integer getWinner() {
		return this.winner;
	}

	public boolean isCaptureMove() {
		return isCaptureMove;
	}

	public void setCaptureMove(boolean wasCapture) {
		this.isCaptureMove = wasCapture;
	}

	public Color getReturnMessageBgColor() {
		return this.returnMessageBgColour;
	}
}
