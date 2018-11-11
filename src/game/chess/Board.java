package game.chess;

import java.util.*;
import game.chess.pieces.*;

/**
 * This class represents the game board and holds a list of the pieces on it
 * 
 * @author Burke
 *
 */
public class Board {
	private final static String FILE = "abcdefgh";
	private List<Piece> pieces;
	/**
	 * boardState [rank] [file]
	 */
	private Piece[][] boardState;
	private int blackCount;
	private int whiteCount;
	
	
	
	Board(){
		pieces = new ArrayList<Piece>();
		boardState = new Piece[8][8];
		clear();
	}
	
	public void initialize() {
		clear();
		Piece.Type[] mainRank = {Piece.Type.Rook, Piece.Type.Knight, Piece.Type.Bishop, Piece.Type.Queen,
		                         Piece.Type.King, Piece.Type.Bishop, Piece.Type.Knight, Piece.Type.Rook};
		for (int file = 0; file < 8; file++) {
			Piece whiteMainRankPiece = Piece.createWhite(mainRank[file]);
			pieces.add(whiteMainRankPiece);
			incrementWhiteCount();
			boardState[0][file] = whiteMainRankPiece;
			
			Piece whitePawn = Piece.createWhitePawn();
			pieces.add(whitePawn);
			boardState[1][file] = whitePawn;
			incrementWhiteCount();
			
			boardState[2][file] = Piece.noPiece();
			boardState[3][file] = Piece.noPiece();
			boardState[4][file] = Piece.noPiece();
			boardState[5][file] = Piece.noPiece();
			
			Piece blackPawn = Piece.createBlackPawn();
			pieces.add(blackPawn);
			boardState[6][file] = blackPawn;
			incrementBlackCount();
			
			Piece blackMainRankPiece = Piece.createBlack( mainRank[file]);
			pieces.add(blackMainRankPiece);
			boardState[7][file] = blackMainRankPiece;
			incrementBlackCount();
		}
	}
	
	
	public void clear() {
		resetCount();
		Piece.resetCount();
		pieces = new ArrayList<Piece>();
		for(int i = 0;i < 8;i++) {
			for(int j = 0;j < 8;j++) {
				boardState[i][j] = Piece.noPiece();
			}
		}
	}

	/**
	 * @param piece  adds a Piece object to the list of pieces on the board
	 */
	private void addPiece(Piece piece) {
		if (piece.isBlack())
			incrementBlackCount();
		if (piece.isWhite())
			incrementWhiteCount();
		pieces.add(piece);
	}
	public String print() {
		StringBuilder buffer = new StringBuilder();
		// Start by printing the far side of the board so it looks like you were sitting in white's seat
		for (int i = 7; i >= 0; i--) {
			for(int j = 0;j < 8;j++) {
			buffer.append(boardState[i][j].print());
			buffer.append(" ");
				
			}
			//	and the rank number
			buffer.append(i+1);
			buffer.append(util.StringUtil.NEWLINE);
		}
		//	Add the file letters
		for(int i = 0; i < 8; i++) {
			buffer.append(FILE.charAt(i));
			buffer.append(' ');
		}
		buffer.append('+');
		buffer.append(util.StringUtil.NEWLINE);
		
		return buffer.toString();
	}
	public int pieceCount() {
		return pieces.size();
	}

	public int getNumberOf(Piece.Color color, Piece.Type type) {
		int count = 0;
		switch(color) {
		case White:
			count = countWhite(type);
			break;
		case Black:
			count = countBlack(type);
			break;
		}
		return count;
	}
	private int countWhite(Piece.Type type) {
		int count = 0;
		for (Piece piece: pieces) {
			if(piece.getType() == type)
				if(piece.isWhite())
					count += 1;
		}
		return count;
	}
	private int countBlack(Piece.Type type) {
		int count = 0;
		for (Piece piece: pieces) {
			if(piece.getType() == type)
				if(piece.isBlack())
					count += 1;
		}
		return count;
	}
	
	/**
	 * This is visible as the strength of a pawn is not modified as they move, this is more visibility than I would like for this method 
	 * @param file
	 * @return
	 */
	List<Piece> getFile(int file) {
		List<Piece> activePieces = new ArrayList<Piece>();
		for (int i = 0; i < 8; i++) {
			Piece piece = boardState[i][file];
			if (!piece.isEmpty())
				activePieces.add(piece);
		}
		return activePieces;
	}
	
	List<Piece> getRank(int rank){
		List<Piece> activePieces = new ArrayList<Piece>();
		for(Piece piece: boardState[rank])
			if(!piece.isEmpty())
				activePieces.add(piece);
		return activePieces;
	}
	
	
	 void resetCount() {
		resetWhiteCount();
		resetBlackCount();
	}
	 private void resetWhiteCount() {
		whiteCount = 0;
	}
	 private void resetBlackCount() {
		blackCount = 0;
	}
	private void incrementWhiteCount() {
		whiteCount++;
	}
	private void decrementWhiteCount() {
		whiteCount--;
	}
	private void incrementBlackCount() {
		blackCount++;
	}
	private void decrementBlackCount() {
		blackCount--;
	}
	 int getWhiteCount() {
		return whiteCount;
	}
	 int getBlackCount() {
		return blackCount;
	}
	/**
	 * returns null of not a valid board position, otherwise returns indexes for the board position array
	 * @param place
	 * @return [int rank, int file]
	 */
	public static int[] getPosition(String place) {
		int[] index = new int[2];
		place = place.toLowerCase();
		index[1] = FILE.indexOf(place.charAt(0));
		index[0] = Integer.parseUnsignedInt(place.substring(1))-1;
		if (0 > index[0] ||index[0] > 7)
			return null;	// if Rank is off board
		if (index[1] == -1)
			return null;	// if File is off board
		return index;
	}
	public static String getSquare(int[] index) {
		if (0 > index[0] ||index[0] > 7)
			return null;	// if Rank is off board
		if (index[1] == -1)
			return null;	// if File is off board
		StringBuilder square = new StringBuilder();
		square.append(FILE.charAt(index[1]));
		square.append(index[0]+1);
		return square.toString();
	}
	public Piece getPiece(String square) {
		int[] index = getPosition(square);
		return boardState[index[0]][index[1]];
	}

	public boolean  put(Piece piece, String square) {
		if (!getPiece(square).isEmpty())
			return false;
		int[] index = getPosition(square);
		piece.setPosition(index);
		boardState[index[0]][index[1]] = piece;
		addPiece(piece);
		return true;
	}
	public boolean removePiece(String square) {
		Piece piece = getPiece(square);
		if (piece.isEmpty())
			return false;
		if (piece.isBlack())
			decrementBlackCount();
		if (piece.isWhite())
			decrementWhiteCount();
		pieces.remove(pieces.indexOf(piece));
		int[] position = getPosition(square);
		boardState[position[0]][position[1]] = Piece.noPiece();
		return true;
	}

	/**
	 * This represents physically moving a piece on the board. Any piece at the end position is replaced.
	 * @param start
	 * @param end
	 */
	public void movePiece(String start, String end) {
		int[] newPosition = getPosition(end);
		int[] startingPoint = getPosition(start);
		Piece movedPiece = getPiece(start);
		
		boardState[newPosition[0]][newPosition[1]] = movedPiece;
		boardState[startingPoint[0]][startingPoint[1]] = Piece.noPiece();
		
	}
	

}
