package game.chess;

import java.util.ArrayList;
import java.util.List;
import game.chess.pieces.Piece.Moves;
import game.chess.pieces.Piece;

public class Game {
	private Board board = new Board();
	
	public void initialize(){
		board.initialize();
	}
	
	public Board getBoard() {
		return board;
	}
	
	
	public double sideStrength(Piece.Color color) {
		double strength = 0.0;
		for (int i = 0; i < 8; i++) { // proccessing file by file
			List<Piece> piecesInFile = board.getFile(i);
			List<Piece> oneSide = new ArrayList<Piece>();
			for (Piece piece : piecesInFile) {
				switch(color) {
				case Black:
					if(piece.isBlack())
						oneSide.add(piece);
					break;
				case White:
					if(piece.isWhite())
						oneSide.add(piece);
					break;
				}
			}
			strength += strengthPerFile(oneSide);
		}
		return strength;
	}
	public double whiteStrength() {
		return sideStrength(Piece.Color.White);
	}
	public double blackStrength() {
		return sideStrength(Piece.Color.Black);
	}
	private double strengthPerFile(List<Piece> file) {
		boolean isAPawnInFile = false;
		boolean arePawns = false;
		double strength = 0.0;
		
		for (Piece piece : file) {
			strength += piece.getStrength();
			if(piece.getType() == Piece.Type.Pawn &! arePawns) { // if we have seen two pawns there is no need to check if further pieces are pawns
				if(isAPawnInFile)
					arePawns = true;  // If there has already been a pawn this is the second pawn
				isAPawnInFile = true;  // Record that we have seen a pawn
			}
		}
		if(isAPawnInFile &! arePawns) // If there was only one pawn correct strength for this fact
			strength += 0.5;
		return strength;
	}
	
	public boolean  placePiece(Piece piece, String square) {
		return board.put(piece, square);
	}
	public String printBoard() {
		return board.print();
	}
	public void movePiece(String start, String end) {
		Piece movingPiece = board.getPiece(start);
		
		if(start.equals(end)) {
			System.out.println("This is not a move!");
			return;
		}
		if (movingPiece.getType() == Piece.Type.King) {
			if (2 < movingPiece.moveLength(Board.getPosition(start), Board.getPosition(end))) {
				System.out.println("this is an invalid move");
				return;}
		}
		if(movingPiece.getType() == Piece.Type.Queen) {
			
		}
		board.movePiece(start, end);
		
	}
	
	Piece getPieceAt(String square) {
		return board.getPiece(square);
	}
	
	

}
