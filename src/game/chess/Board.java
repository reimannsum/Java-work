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
	private final String FILE = "abcdefgh";
	List<Piece> pieces;
	Piece[][] boardState;
	static int blackCount;
	static int whiteCount;
	
	
	
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
			boardState[0][file] = whiteMainRankPiece;
			
			Piece whitePawn = Piece.createWhitePawn();
			pieces.add(whitePawn);
			boardState[1][file] = whitePawn;
			
			boardState[2][file] = Piece.noPiece();
			boardState[3][file] = Piece.noPiece();
			boardState[4][file] = Piece.noPiece();
			boardState[5][file] = Piece.noPiece();
			
			Piece blackPawn = Piece.createBlackPawn();
			pieces.add(blackPawn);
			boardState[6][file] = blackPawn;
			
			Piece blackMainRankPiece = Piece.createBlack( mainRank[file]);
			pieces.add(blackMainRankPiece);
			boardState[7][file] = blackMainRankPiece;
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
	public Piece getPiece(String place) {
		int[] index = getPosition(place);
		return boardState[index[0]][index[1]];
	}
	public int pieceCount() {
		return pieces.size();
	}
	public int getNumberOf(String color, Piece.Type type) {
		int count = 0;
		if (Piece.WHITE.equals(color))
			count = countWhite(type);
		else
			count = countBlack(type);
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
	public List<Piece> getAllPieces(){
		return pieces;
	}
	public String print() {
		StringBuilder buffer = new StringBuilder();
		// Start by printing the far side of the board so it looks like you were sitting in white's seat
		for (int i = 7; i >= 0; i--) {
			for(int j = 0;j < 8;j++) {
			buffer.append(boardState[i][j].print());
			buffer.append(" ");
				
			}
			buffer.append(i+1);
			buffer.append(util.StringUtil.NEWLINE);
		}
		for(int i = 0; i < 8; i++) {
			buffer.append(FILE.charAt(i));
			buffer.append(' ');
		}
		buffer.append('+');
		buffer.append(util.StringUtil.NEWLINE);
		
		return buffer.toString();
	}
	
	List<Piece> getFile(int file) {
		List<Piece> activePieces = new ArrayList<Piece>();
		for (int i = 0; i < 8; i++) {
			Piece piece = boardState[i][file];
			if (!piece.isEmpty())
				activePieces.add(piece);
		}
		return activePieces;
	}
	public double whiteStrength() {
		double strength = 0.0;
		for (int i = 0; i < 8; i++) { // proccessing file by file
			List<Piece> piecesInFile = getFile(i);
			List<Piece> whitePieces = new ArrayList<Piece>();
			for (Piece piece : piecesInFile) 
				if(piece.isWhite())
					whitePieces.add(piece);
			strength += strengthPerFile(whitePieces);
		}
		return strength;
	}
	public double blackStrength() {
		double strength = 0.0;
		for (int i = 0; i < 8; i++) { // proccessing file by file
			List<Piece> piecesInFile = getFile(i);
			List<Piece> blackPieces = new ArrayList<Piece>();
			for (Piece piece : piecesInFile) 
				if(piece.isBlack())
					blackPieces.add(piece);
			strength += strengthPerFile(blackPieces);
		}
		return strength;
	}
	
	private double strengthPerFile(List<Piece> file) {
		boolean arePawns = false;
		double strength = 0.0;
		for (Piece piece : file) {
			strength += piece.getType().getPoints();
		}
		return strength;
	}
	
	public void clear() {
		Piece.resetCount();
		pieces = new ArrayList<Piece>();
		for(int i = 0;i < 8;i++) {
			for(int j = 0;j < 8;j++) {
				boardState[i][j] = Piece.noPiece();
			}
		}
	}
	
	public boolean  placePiece(Piece piece, String square) {
		if (!getPiece(square).isEmpty())
			return false;
		int[] index = getPosition(square);
		boardState[index[0]][index[1]] = piece;
		addPiece(piece);
		return true;
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
	private int[] getPosition(String place) {
		int[] index = new int[2];
		place = place.toLowerCase();
		index[1] = FILE.indexOf(place.charAt(0));
		index[0] = Integer.parseUnsignedInt(place.substring(1))-1;
		return index;
	}
	

}
