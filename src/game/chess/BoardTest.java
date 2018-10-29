package game.chess;

import game.chess.pieces.Piece;
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
	}
	
	public void testColorCount() {
		board.initialize();
		assertEquals(16, Piece.getWhiteCount());
		assertEquals(16, Piece.getBlackCount()); 
	}
	
	public void testPieceCount() {
		board.initialize();
		assertEquals(8, board.getNumberOf(Piece.BLACK, Piece.Type.Pawn));
		assertEquals(2, board.getNumberOf(Piece.WHITE, Piece.Type.Bishop));
		assertEquals(1, board.getNumberOf(Piece.WHITE, Piece.Type.King));
	}
	
	public void testGetPiece() {
		board.initialize();
		assertEquals(Piece.Type.Rook, board.getPiece("a8").getType());
		assertEquals(Piece.WHITE, board.getPiece("f1").getColor());
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
		assertTrue(board.placePiece(Piece.createBlackKing(), "b6"));
		assertTrue(board.placePiece(Piece.createBlackRook(), "b5"));
		assertTrue(board.placePiece(Piece.createWhiteKing(), "c4"));
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
	
	public void testStrength() {
		assertEquals(0.0, board.whiteStrength());
		assertEquals(0.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createBlackKing(), "b8"));
		assertEquals(0.0, board.whiteStrength());
		assertEquals(0.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createBlackRook(), "c8"));
		assertEquals(0.0, board.whiteStrength());
		assertEquals(5.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createBlackPawn(), "a7"));
		assertEquals(0.0, board.whiteStrength());
		assertEquals(6.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createBlackPawn(), "c7"));
		assertEquals(0.0, board.whiteStrength());
		assertEquals(7.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createBlackBishop(), "d7"));
		assertEquals(0.0, board.whiteStrength());
		assertEquals(10.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createBlackPawn(), "b6"));
		assertEquals(0.0, board.whiteStrength());
		assertEquals(11.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createBlackQueen(), "e6"));
		assertEquals(0.0, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhiteKnight(), "f4"));
		assertEquals(2.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhiteQueen(), "g4"));
		assertEquals(11.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhitePawn(), "h3"));
		assertEquals(12.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhitePawn(), "f2"));
		assertEquals(13.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhitePawn(), "g2"));
		assertEquals(14.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhiteRook(), "e1"));
		assertEquals(19.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhiteKing(), "f1"));
		assertEquals(19.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertTrue(board.placePiece(Piece.createWhitePawn(), "f3"));
		assertEquals(19.5, board.whiteStrength());
		assertEquals(20.0, board.blackStrength());
		
		assertEquals(8, Board.getWhiteCount());
		assertEquals(7, Board.getBlackCount());
		
		System.out.println(board.print());
	}
	
	
	

	
	

}
