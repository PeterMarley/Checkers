package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GamePieceTest {

	GamePiece gamePieceWhite, gamePieceBlack, gamePieceBadTeam, gamePieceWhiteKing, gamePieceBlackKing;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		GamePiece.resetPieceCounts();
		gamePieceBlack = new GamePiece(0);
		gamePieceBlackKing = new GamePiece(0);
		gamePieceBlackKing.setToKing();
		
		gamePieceWhite = new GamePiece(1);
		gamePieceWhiteKing = new GamePiece(1);
		gamePieceWhiteKing.setToKing();
		
		gamePieceBadTeam = new GamePiece(2);
	}

	// Constructor

	@Test
	void testGamePiece_Team() {
		int teamBlackActual = gamePieceBlack.getTeam();
		int teamBlackExpected = 0;
		assertEquals(teamBlackExpected, teamBlackActual);

		int teamWhiteActual = gamePieceWhite.getTeam();
		int teamWhiteExpected = 1;
		assertEquals(teamWhiteExpected, teamWhiteActual);

		int teamBadTeamActual = gamePieceBadTeam.getTeam();
		int teamBadTeamExpected = -999;
		assertEquals(teamBadTeamExpected, teamBadTeamActual);
	}

	@Test
	void testGamePiece_PieceNum() {
		int teamBlackActual = gamePieceBlack.getPieceNum();
		int teamBlackExpected = 1;
		assertEquals(teamBlackExpected, teamBlackActual);

		int teamWhiteActual = gamePieceWhite.getPieceNum();
		int teamWhiteExpected = 1;
		assertEquals(teamWhiteExpected, teamWhiteActual);

		int teamBadTeamActual = gamePieceBadTeam.getPieceNum();
		int teamBadTeamExpected = 0;
		assertEquals(teamBadTeamExpected, teamBadTeamActual);
	}

	// getTotalPieces(int team)

	@Test
	void testGetTotalPieces_BlackTeam() {
		int expected = 1;
		int actual = GamePiece.getTotalPieces(0);
		assertEquals(expected, actual);
	}

	void testGetTotalPieces_WhiteTeam() {
		int expected = 1;
		int actual = GamePiece.getTotalPieces(1);
		assertEquals(expected, actual);
	}

	@Disabled @Test
	void testEnumeratePiecesOnBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testRemovePieceFromCount() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testToVisualString() {
		fail("Not yet implemented");
	}

	@Test
	void testIsKing() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPieceNum() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTeam() {
		fail("Not yet implemented");
	}

	@Test
	void testSetToKing() {
		fail("Not yet implemented");
	}

}
