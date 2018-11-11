package game.chess;

import game.chess.pieces.*;
import game.chess.pieces.Piece.Type;
import junit.framework.TestCase;

public class GameTest extends TestCase{
	private Game game;
	
	public void setUp() {
		game = new Game();
	}
	public void testColorCount() {
		game.initialize();
		assertEquals(16, Piece.getWhiteCount());
		assertEquals(16, Piece.getBlackCount()); 
	}
	
	
	
	public void testStrength() {
		game.getBoard().resetCount();
		assertEquals(0.0, game.whiteStrength());
		assertEquals(0.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createBlackKing(), "b8"));
		assertEquals(0.0, game.whiteStrength());
		assertEquals(0.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createBlackRook(), "c8"));
		assertEquals(0.0, game.whiteStrength());
		assertEquals(5.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createBlackPawn(), "a7"));
		assertEquals(0.0, game.whiteStrength());
		assertEquals(6.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createBlackPawn(), "c7"));
		assertEquals(0.0, game.whiteStrength());
		assertEquals(7.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createBlackBishop(), "d7"));
		assertEquals(0.0, game.whiteStrength());
		assertEquals(10.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createBlackPawn(), "b6"));
		assertEquals(0.0, game.whiteStrength());
		assertEquals(11.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createBlackQueen(), "e6"));
		assertEquals(0.0, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhiteKnight(), "f4"));
		assertEquals(2.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhiteQueen(), "g4"));
		assertEquals(11.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhitePawn(), "h3"));
		assertEquals(12.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhitePawn(), "f2"));
		assertEquals(13.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhitePawn(), "g2"));
		assertEquals(14.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhiteRook(), "e1"));
		assertEquals(19.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhiteKing(), "f1"));
		assertEquals(19.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertTrue(game.placePiece(Piece.createWhitePawn(), "f3"));
		assertEquals(19.5, game.whiteStrength());
		assertEquals(20.0, game.blackStrength());
		
		assertEquals(8, game.getBoard().getWhiteCount());
		assertEquals(7, game.getBoard().getBlackCount());
	}

	public void testMoveKing() {
		assertTrue(game.placePiece(Piece.createWhiteKing(), "d4"));
		//board.movePiece("d4", "e6");
		game.movePiece("d4", "e5");
		assertCorrectPiece(Type.King, Piece.Color.White, "e5");
		assertEquals(Type.NO_PIECE, game.getPieceAt("d4").getType());
	}
	public void testMoveQueen() {
		assertTrue(game.placePiece(Piece.createWhiteQueen(), "d4"));
		game.movePiece("d4", "e6");
		game.movePiece("d4", "f6");
		assertCorrectPiece(Type.Queen, Piece.Color.White, "f6");
		assertEquals(Type.NO_PIECE, game.getPieceAt("d4").getType());
	}
	
	
	
	public void assertCorrectPiece(Type type, Piece.Color color, String position) {
		assertEquals(type, game.getPieceAt(position).getType());
		if(color != null)
			assertEquals(color, game.getPieceAt(position).getColor());
		
	}
}
