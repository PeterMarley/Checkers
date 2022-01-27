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
	void test_GamePiece_Team() {
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
	void test_GamePiece_PieceNum() {
		int teamBlackActual = gamePieceBlack.getPieceNum();
		int teamBlackExpected = 1;
		assertEquals(teamBlackExpected, teamBlackActual);

		int teamWhiteActual = gamePieceWhite.getPieceNum();
		int teamWhiteExpected = 1;
		assertEquals(teamWhiteExpected, teamWhiteActual);

		int teamBadTeamActual = gamePieceBadTeam.getPieceNum();
		int teamBadTeamExpected = -999;
		assertEquals(teamBadTeamExpected, teamBadTeamActual);
	}

	// getTotalPieces(int team)

	@Test
	void test_getTotalPieces_BlackTeam() {
		int expected = 2;
		int actual = GamePiece.getTotalPieces(0);
		assertEquals(expected, actual);
	}

	@Test
	void test_getTotalPieces_WhiteTeam() {
		int expected = 2;
		int actual = GamePiece.getTotalPieces(1);
		assertEquals(expected, actual);
	}
	
	@Test
	void test_getTotalPieces_InvalidTeam() {
		int expected = -999;
		int actual = GamePiece.getTotalPieces(2);
		assertEquals(expected, actual);
	}

	// removePieceFromCount()
	
	@Test
	void test_removePieceFromCount_WhiteTeam() {
		gamePieceWhite.removePieceFromCount();
		int expected = 1;
		int actual = GamePiece.getTotalPieces(1);
		assertEquals(expected, actual);
	}
	
	@Test
	void test_removePieceFromCount_BlackTeam() {
		gamePieceBlack.removePieceFromCount();
		int expected = 1;
		int actual = GamePiece.getTotalPieces(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void test_removePieceFromCount_InvalidTeam() {
		gamePieceBadTeam.removePieceFromCount();
		int expected = -999;
		int actual = GamePiece.getTotalPieces(2);
		assertEquals(expected, actual);
	}
	
	// toString()
	
	@Test
	void test_toString_WhiteNormal() {
		String expected = "White 1";
		String actual = gamePieceWhite.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	void test_toString_WhiteKing() {
		String expected = "White King 1";
		String actual = gamePieceWhiteKing.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	void test_toString_BlackNormal() {
		String expected = "Black 1";
		String actual = gamePieceBlack.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	void test_toString_BlackKing() {
		String expected = "Black King 1";
		String actual = gamePieceBlackKing.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	void test_toString_BadTeam() {
		String expected = "No Team -999";
		String actual = gamePieceBadTeam.toString();
		assertEquals(expected, actual);
	}
	
	/*
	 * Getters and Setters
	 */
	
	// isKing()
	
	@Test
	void test_isKing_NormalPieceBlack() {
		assertTrue(!gamePieceBlack.isKing());
	}
	
	@Test
	void test_isKing_NormalPieceWhite() {
		assertTrue(!gamePieceWhite.isKing());
	}
	
	@Test
	void test_isKing_KingPieceBlack() {
		assertTrue(gamePieceBlackKing.isKing());
	}
	
	@Test
	void test_isKing_KingPieceWhite() {
		assertTrue(gamePieceWhiteKing.isKing());
	}
	
	// setToKing() 
	
	@Test
	void test_setToKing_White() {
		assertTrue(!gamePieceWhite.isKing());
		gamePieceWhite.setToKing();
		assertTrue(gamePieceWhite.isKing());
	}
	
	@Test
	void test_setToKing_Black() {
		assertTrue(!gamePieceBlack.isKing());
		gamePieceBlack.setToKing();
		assertTrue(gamePieceBlack.isKing());
	}
	
	// getPieceNum()
	
	@Test
	void test_getPieceNum_White() {
		int expected = 1;
		int actual = gamePieceWhite.getPieceNum();
		assertEquals(expected, actual);
		GamePiece tmp = new GamePiece(1);
		int expected2 = 2;
		int actual2 = tmp.getPieceNum();
		assertEquals(expected2, actual2);

	}
	
	@Test
	void test_getPieceNum_Black() {
		int expected = 1;
		int actual = gamePieceWhite.getPieceNum();
		assertEquals(expected, actual);
		GamePiece tmp = new GamePiece(0);
		int expected2 = 2;
		int actual2 = tmp.getPieceNum();
		assertEquals(expected2, actual2);
	}
	
	@Test
	void test_getPieceNum_BadTeam() {
		int expected = -999;
		int actual = gamePieceBadTeam.getPieceNum();
		assertEquals(expected, actual);
	}
	
	// getTeam()
	
	@Test
	void test_getTeam_White() {
		int expected = 1;
		int actual = gamePieceWhite.getTeam();
		assertEquals(expected, actual);
	}
	
	@Test
	void test_getTeam_Black() {
		int expected = 0;
		int actual = gamePieceBlack.getTeam();
		assertEquals(expected, actual);
	}
	
	// resetPieceCount()
	
	@Test
	void test_resetPieceCounts() {
		GamePiece.resetPieceCounts();
		int expected = 0;
		int actual = GamePiece.getTotalPieces(0);
		assertEquals(expected, actual);
		int actual2 = GamePiece.getTotalPieces(1);
		assertEquals(expected, actual2);

	}
	
	
}
