package game.chess.pieces;

import junit.framework.TestCase;

public class PieceTest extends TestCase{
	private Piece whitePawn;
	private Piece blackPawn;
	
	private final char KING_REPRESENTATION = 'k';
	private final char QUEEN_REPRESENTATION = 'q';
	private final char BISHOP_REPRESENTATION = 'b';
	private final char KNIGHT_REPRESENTATION = 'n';
	private final char ROOK_REPRESENTATION = 'r';
	private final char PAWN_REPRESENTATION = 'p';
	
	
	public void setUp() {
		Piece.resetCount();
		whitePawn = Piece.createWhitePawn();
		blackPawn = Piece.createBlackPawn();
		
	}
	
	public void testCreateIndividual() {
		
		verifyCreation(
				Piece.createWhitePawn(), Piece.createBlackPawn(),
				Piece.Type.Pawn, PAWN_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteRook(), Piece.createBlackRook(),
				Piece.Type.Rook, ROOK_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteKnight(), Piece.createBlackKnight(),
				Piece.Type.Knight, KNIGHT_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteBishop(), Piece.createBlackBishop(),
				Piece.Type.Bishop, BISHOP_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteQueen(), Piece.createBlackQueen(),
				Piece.Type.Queen, QUEEN_REPRESENTATION);
		verifyCreation(
				Piece.createWhiteKing(), Piece.createBlackKing(),
				Piece.Type.King, KING_REPRESENTATION);
		Piece blank = Piece.noPiece();
		assertEquals('.', blank.getRepresentation());
		assertEquals(Piece.Type.NO_PIECE, blank.getType());
	}
	
	public void testCreateType() {
		verifyCreation(
				Piece.createWhite(Piece.Type.Pawn), Piece.createBlack(Piece.Type.Pawn),
				Piece.Type.Pawn, PAWN_REPRESENTATION);
		verifyCreation(
				Piece.createWhite(Piece.Type.Rook), Piece.createBlack(Piece.Type.Rook),
				Piece.Type.Rook, ROOK_REPRESENTATION);
		verifyCreation(
				Piece.createWhite(Piece.Type.Knight), Piece.createBlack(Piece.Type.Knight),
				Piece.Type.Knight, KNIGHT_REPRESENTATION);
		verifyCreation(
				Piece.createWhite(Piece.Type.Bishop), Piece.createBlack(Piece.Type.Bishop),
				Piece.Type.Bishop, BISHOP_REPRESENTATION);
		verifyCreation(
				Piece.createWhite(Piece.Type.Queen), Piece.createBlack(Piece.Type.Queen),
				Piece.Type.Queen, QUEEN_REPRESENTATION);
		verifyCreation(
				Piece.createWhite(Piece.Type.King), Piece.createBlack(Piece.Type.King),
				Piece.Type.King, KING_REPRESENTATION);
	}
	
	private void verifyCreation(Piece whitePiece, Piece blackPiece, Piece.Type type, char representation) {
		assertTrue(whitePiece.isWhite());
		assertEquals(type, whitePiece.getType());
		assertEquals(representation, whitePiece.getRepresentation());
		
		assertTrue(blackPiece.isBlack());
		assertEquals(type, blackPiece.getType());
		assertEquals(Character.toUpperCase(representation), blackPiece.print());
	}
	
	public void testCount() {
		assertEquals(1, Piece.getWhiteCount());
		assertEquals(1, Piece.getBlackCount()); 
		
		Piece.resetCount();
		assertEquals(0, Piece.getWhiteCount());
		assertEquals(0, Piece.getBlackCount()); 
		
	}
	public void testIsWhite() {
		assertTrue(whitePawn.isWhite());
		assertFalse(blackPawn.isWhite());
	}
	
	public void testIsBlack() {
		assertTrue(blackPawn.isBlack());
		assertFalse(whitePawn.isBlack());		
	}
	
	public void testStrength() {
		assertEquals(0, whitePawn.getStrength());
		whitePawn.setStrength(3);
		assertEquals(3, whitePawn.getStrength());
	}
}
