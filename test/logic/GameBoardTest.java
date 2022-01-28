package logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {

	GameBoard gameBoard;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		gameBoard = new GameBoard();
		gameBoard.setPlayerNames("Unit Test P1", "Unit Test P2");
	}

	@Test
	void testGameBoard_HeightAndWidth() {
		GamePiece[][] boardArray = gameBoard.getBoardArray();
		int expectedWidth = 8;
		int actualWidth = boardArray[0].length;
		assertEquals(expectedWidth, actualWidth);

		int expectedHeight = 8;
		int actualHeight = boardArray.length;
		assertEquals(expectedHeight, expectedHeight);
		
	}

	@Test
	void testMove_operation() {
		fail("Not yet implemented");
	}

	@Test
	void testMove_hasAttack() {
		fail("Not yet implemented");
	}

	@Test
	void testCheckPlayerHasValidMoves() {
		fail("Not yet implemented");
	}

	@Test
	void testNextPlayer() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPlayerNames() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCurrentPlayer() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPlayerName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPlayerNames() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSquareIntInt() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSquareIntArray() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCaptured() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testSetReturnMessageString() {
		fail("Not yet implemented");
	}

	@Test
	void testSetReturnMessageStringColor() {
		fail("Not yet implemented");
	}

	@Test
	void testGetReturnMessage() {
		fail("Not yet implemented");
	}

	@Test
	void testClearReturnMessage() {
		fail("Not yet implemented");
	}

	@Test
	void testSetWinner() {
		fail("Not yet implemented");
	}

	@Test
	void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	void testIsCaptureMove() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCaptureMove() {
		fail("Not yet implemented");
	}

	@Test
	void testGetReturnMessageBgColor() {
		fail("Not yet implemented");
	}

}
