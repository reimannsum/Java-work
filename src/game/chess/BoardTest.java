package game.chess;

import game.chess.pieces.*;
import junit.framework.TestCase;
import util.StringUtil;

public class BoardTest extends TestCase{
private Board board;
	
	public void setUp() {
		board = new Board();
		board.clear();
	}
	
	public void testCreate() {
		board.initialize();     
		assertEquals(32, board.pieceCount());
		assertEquals(
				StringUtil.appendNewLine("R N B Q K B N R 8") + 
				StringUtil.appendNewLine("P P P P P P P P 7") +
				StringUtil.appendNewLine(". . . . . . . . 6") + 
				StringUtil.appendNewLine(". . . . . . . . 5") + 
				StringUtil.appendNewLine(". . . . . . . . 4") + 
				StringUtil.appendNewLine(". . . . . . . . 3") +
				StringUtil.appendNewLine("p p p p p p p p 2") +
				StringUtil.appendNewLine("r n b q k b n r 1") +
				StringUtil.appendNewLine("a b c d e f g h +")
				, board.print());
		assertEquals(16, board.getWhiteCount());
		assertEquals(16, board.getBlackCount());
	}
	
	public void testGetPiece() {
		board.initialize();
		assertEquals(Piece.Type.Rook, board.getPiece("a8").getType());
		assertEquals(Piece.Color.White, board.getPiece("f1").getColor());
		assertEquals(Piece.Type.NO_PIECE, board.getPiece("d5").getType());
		
	}
	
	public void testPlacePiece() {
		assertEquals(
				StringUtil.appendNewLine(". . . . . . . . 8") + 
				StringUtil.appendNewLine(". . . . . . . . 7") +
				StringUtil.appendNewLine(". . . . . . . . 6") + 
				StringUtil.appendNewLine(". . . . . . . . 5") + 
				StringUtil.appendNewLine(". . . . . . . . 4") + 
				StringUtil.appendNewLine(". . . . . . . . 3") +
				StringUtil.appendNewLine(". . . . . . . . 2") +
				StringUtil.appendNewLine(". . . . . . . . 1") +
				StringUtil.appendNewLine("a b c d e f g h +")
				, board.print());
		assertTrue(board.put(Piece.createBlackKing(), "b6"));
		assertTrue(board.put(Piece.createBlackRook(), "b5"));
		assertTrue(board.put(Piece.createWhiteKing(), "c4"));
		assertEquals(
				StringUtil.appendNewLine(". . . . . . . . 8") + 
				StringUtil.appendNewLine(". . . . . . . . 7") +
				StringUtil.appendNewLine(". K . . . . . . 6") + 
				StringUtil.appendNewLine(". R . . . . . . 5") + 
				StringUtil.appendNewLine(". . k . . . . . 4") + 
				StringUtil.appendNewLine(". . . . . . . . 3") +
				StringUtil.appendNewLine(". . . . . . . . 2") +
				StringUtil.appendNewLine(". . . . . . . . 1") +
				StringUtil.appendNewLine("a b c d e f g h +")
				, board.print());
	}
	public void testPieceCount() {
		board.initialize();
		assertEquals(8, board.getNumberOf(Piece.Color.Black, Piece.Type.Pawn));
		assertEquals(2, board.getNumberOf(Piece.Color.White, Piece.Type.Bishop));
		assertEquals(1, board.getNumberOf(Piece.Color.White, Piece.Type.King));
	}
	
	public void testRemovePieces() {
		board.initialize();
		assertTrue(board.removePiece("a1"));
		assertFalse(board.removePiece("a1"));
		assertEquals(15, board.getWhiteCount());
	}
	
	public void testGetPosition() {
		int[] firstSquare = new int[2];
		firstSquare[0] = 0;
		firstSquare[1] = 0;
		String square = Board.getSquare(firstSquare);
		assertEquals("a1", square);
		assertEquals(Board.getPosition(square)[0], firstSquare[0]);
		assertEquals(Board.getPosition(square)[1], firstSquare[1]);
		
	}
	
	
	

	
	

}
