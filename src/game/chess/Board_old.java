package game.chess;

import java.util.*;
import game.chess.pieces.*;

/**
 * This class represents the game board and holds a list of the pieces on it
 * 
 * @author Burke
 *
 */
public class Board_old {
	private final String FILE = "ABCDEFGH";
	List<Piece> pieces;
	Piece[][] boardState;
	static int blackCount;
	static int whiteCount;
	
	
	
	Board(){
		pieces = new ArrayList<Piece>();
		boardState = new Piece[8][8];
		initialize();
	}
	
	public void initialize() {
		Piece.resetCount();
		pieces = new ArrayList<Piece>();
		String mainRank = "rnbqkbnr";
		
		
		for (int i = 0; i < 8; i++) {
			Piece whiteMainRankPiece = Piece.create(Piece.WHITE, mainRank.charAt(i));
			pieces.add(whiteMainRankPiece);
			boardState[0][i] = whiteMainRankPiece;
			
			Piece whitePawn = Piece.createWhitePawn();
			pieces.add(whitePawn);
			boardState[1][i] = whitePawn;
			
			Piece blackPawn = Piece.createBlackPawn();
			pieces.add(blackPawn);
			boardState[6][i] = blackPawn;
			
			Piece blackMainRankPiece = Piece.create(Piece.BLACK, mainRank.charAt(i));
			pieces.add(blackMainRankPiece);
			boardState[7][i] = blackMainRankPiece;
		}
	}
	
	
	/**
	 * @param piece  adds a Piece object to the list of pieces on the board
	 */
	public void addPiece(Piece piece) {
		pieces.add(piece);
	}
	
	public int pieceCount() {
		return pieces.size();
	}
	
	/**
	 * @return ArrayList the board does not act on the pieces on it
	 */
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	
	public String print() {
		StringBuilder buffer = new StringBuilder();
		// Start by printing the far side of the board so it looks like you were sitting in white's seat
		for (int i = 7; i >= 0; i--) {
			for(int j = 0;j < 8;j++) {
				if (boardState[i][j] != null)
					buffer.append(boardState[i][j].print());
				else
					buffer.append('.');
			}
			buffer.append(util.StringUtil.NEWLINE);
		}
		
		return buffer.toString();
	}
	
	Piece[] getRank(int rank) {
		return this.boardState[rank-1];
	}

	static void incrementWhiteCount() {
		whiteCount++;
	}
	static void incrementBlackCount() {
		blackCount++;
	}
	static int getWhiteCount() {
		return whiteCount;
	}
	static int getBlackCount() {
		return blackCount;
	}
	

}
