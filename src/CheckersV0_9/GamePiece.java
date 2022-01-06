package draughts;

/**
 * 
 * @author Peter Marley
 * @projectTitle Checkers V1
 * @City Belfast
 * @Country Ireland
 * @maxLove cs50x - You changed my life <3
 *
 */
public class GamePiece {

	private int team;
	private boolean king;
	private int pieceNum;

	private static int blackNormalPieces;
	private static int whiteNormalPieces;

	private static int blackKingPieces;
	private static int whiteKingPieces;

	private static int totalWhitePieces;
	private static int totalBlackPieces;

	/**
	 * Creates a GamePiece, setting the team and the piece number info
	 * 
	 * @param team - The specified team
	 */
	public GamePiece(int team) {
		this.setTeam(team);
		this.setPieceNum();
		//Controller.log.add("Game piece \"" + this.toVisualString() + " " + this.pieceNum + "\" created!");
	}

	/**
	 * 
	 * @return is the Piece a King?
	 */
	public boolean isKing() {
		return this.king;
	}

	/**
	 * Make the Piece a king.
	 */
	public void setToKing() {
		this.king = true;
		if (this.team == 0) {
			--blackNormalPieces;
			this.pieceNum = ++blackKingPieces;
		} else {
			--whiteNormalPieces;
			this.pieceNum = ++whiteKingPieces;
		}

	}

	/**
	 * 
	 * @return the pieceNum of this piece
	 */
	public int getPieceNum() {
		return this.pieceNum;
	}

	/**
	 * Set the appropriate piece number for this team
	 */
	private void setPieceNum() {
		if (this.team == 0) {
			this.pieceNum = ++blackNormalPieces;
			++totalBlackPieces;
		} else {
			this.pieceNum = ++whiteNormalPieces;
			++totalWhitePieces;
		}
	}

	/**
	 * @return A String that represents the amount of black/white normals and kings, and total pieces of each team still on board
	 */
	public static String pieceCount() {
		return String.format("Black Team[normals %d, kings %d, total %d] White Team[normals %d, kings %d, total %d]%n",
				blackNormalPieces, blackKingPieces, totalBlackPieces, whiteNormalPieces, whiteKingPieces, totalWhitePieces);
	}

	/**
	 * @param team
	 * @return returns the count of total active pieces for specified team
	 */
	public static int getTotalPieces(int team) {
		if (team == 0) {
			return totalBlackPieces;
		} else {
			return totalWhitePieces;
		}
	}

	/**
	 * adjusts the piece count data for a captured piece
	 */
	public void removePieceFromBoard() {
		if (this.team == 0) {
			totalBlackPieces--;
			if (this.isKing()) {
				blackKingPieces--;
			} else {
				blackNormalPieces--;
			}
		} else {
			totalWhitePieces--;
			if (this.isKing()) {
				whiteKingPieces--;
			} else {
				whiteNormalPieces--;
			}
		}

	}

	/**
	 * Get the team for this piece
	 * 
	 * @return team <br>
	 *         0 for Black<br>
	 *         1 for White<br>
	 *         set to -999 if error
	 */
	public int getTeam() {
		return this.team;
	}

	/**
	 * Set the team for this piece
	 *
	 * @param team <br>
	 *             0 for Black<br>
	 *             1 for White<br>
	 *             set to -999 if error
	 */
	public void setTeam(int team) {
		if (team == 0 || team == 1) {
			this.team = team;
		} else {
			this.team = -999;
		}
	}

	/**
	 * "Black Num" / "Black King Num"
	 * Team 0 == Black
	 * Team 1 == White
	 */
	public String toString() {
		String team = null;
		if (this.team == 0) {
			team = "Black";
		} else if (this.team == 1) {
			team = "White";
		}
		return String.format("%s %s %s", team, ((this.king) ? "King" : ""), this.pieceNum);
	}

	/**
	 * "[#][#][#][#]" / "[ ][ ][ ][ ]"
	 * Team 0 == Black
	 * Team 1 == White
	 */
	public String toVisualString() {
		String team = null;
		if (this.team == 0) {
			team = "[##########]";
		} else if (this.team == 1) {
			team = "[__________]";
		}
		return team;
	}

}
