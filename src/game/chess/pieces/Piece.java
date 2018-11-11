package game.chess.pieces;

import java.util.*;

public class Piece{
	private Color color;
	private Type type;
	private int[] pos = {-1,-1};
	static int blackCount;
	static int whiteCount;
	protected char representation;
	public enum Color { Black, White };
	public enum Moves { North, Northeast, East, Southeast, South, Southwest, West, Northwest };
	public enum Type {
		Pawn('p', 0.5),
		Rook('r', 5),
		Knight('n', 2.5),
		Bishop('b', 3),
		Queen('q', 9),
		King('k', 0),
		NO_PIECE('.', 0);
		
		private double points;
		private char representation;
		Type(char representation, double points) {
			this.representation = representation;
			this.points = points;
		}
		char getRepresentation() {
			return representation;
		}
		double getPoints() {
			return points;
		}
	};
	private double strength = 0.0;
	

	protected Piece(Type type){
		this.type = type;
		this.strength = type.getPoints();
	}
	//	Constructor for SubClasses
	protected Piece(Color color) {
		this.color = color;
	}
	protected Piece() {
		type = Type.NO_PIECE;
	}
	public static Piece createWhite(Type type) {
		Piece piece =  new Piece(type);
		piece.color = Color.White;
		incrementWhiteCount();
		return piece;
	}
	public static Piece createBlack(Type type) {
		Piece piece =  new Piece(type);
		piece.color = Color.Black;
		incrementBlackCount();
		return piece;
	}
	public static Piece createWhitePawn() {
		return createWhite(Type.Pawn);
	}
	public static Piece createWhiteRook() {
		return createWhite(Type.Rook);
	}
	public static Piece createWhiteBishop() {
		return createWhite(Type.Bishop);
	}
	public static Piece createWhiteKnight() {
		return createWhite(Type.Knight);
	}
	public static Piece createWhiteQueen() {
		return createWhite(Type.Queen);
	}
	public static Piece createWhiteKing() {
		return createWhite(Type.King);
	}
	public static Piece createBlackPawn() {
		return createBlack(Type.Pawn);
	}
	public static Piece createBlackRook() {
		return createBlack(Type.Rook);
	}
	public static Piece createBlackBishop() {
		return createBlack( Type.Bishop);
	}
	public static Piece createBlackKnight() {
		return createBlack(Type.Knight);
	}
	public static Piece createBlackQueen() {
		return createBlack(Type.Queen);
	}
	public static Piece createBlackKing() {
		return createBlack(Type.King);
	}
	public static Piece noPiece() {
		return new Piece();
	}
	
	
	
//	Getter Methods
	public boolean isWhite() {
		return (this.color == Color.White);
	}
	public boolean isBlack() {
		return (this.color == Color.Black);
	}
	public boolean isEmpty() {
		return (this.type == Type.NO_PIECE);
	}
	public Color getColor() {
		return this.color;
	}
	public Type getType() {
		return type;
	}
	public static int getWhiteCount() {
		return whiteCount;
	}
	public static int getBlackCount() {
		return blackCount;
	}
	public double getStrength() {
		return strength;
	}
	public void setStrength(double value) {
		this.strength = value;
	}
	public int[] getPosition() {
		return this.pos;
	}
	public void setPosition(int[] position) {
		this.pos = position;
	}
	
	
	public char print() {
		if (type == Type.NO_PIECE)
			return this.getType().getRepresentation();
		if (this.isBlack())
			return Character.toUpperCase(this.getType().getRepresentation());
		return this.getType().getRepresentation();
	}
	
	
	

	public static void resetCount() {
		resetWhiteCount();
		resetBlackCount();
	}
	static void resetWhiteCount() {
		whiteCount = 0;
	}
	static void resetBlackCount() {
		blackCount = 0;
	}
	private static void incrementWhiteCount() {
		whiteCount++;
	}
	private static void incrementBlackCount() {
		blackCount++;
	}
	void decrementWhiteCount() {
		whiteCount--;
	}

	void decrementBlackCount() {
		blackCount--;
	}
	// @TODO
	List<String> getPossibleMoves(){
		List<String> moves = new ArrayList<String>();
		
		return null;
	}
	
	public int moveLength(int[] start, int[] end) {
		int rankMove = start[0] - end[0];
		int fileMove = start[1] - end[1];
		return (int) (Math.pow(rankMove, 2) + Math.pow(fileMove, 2));
	}
	
	Moves moveDirection(int[] start, int[] end) {
		int rankMove = start[0] - end[0];
		int fileMove = start[1] - end[1];
		int[] directionVector = new int[2];
		if (rankMove > 0)
			directionVector[1] = 1;
		if (rankMove < 0)
			directionVector[1] = -1;
		if (fileMove > 0)
			directionVector[0] = 1;
		if (fileMove < 0)
			directionVector[0] = -1;
		return getDirection(directionVector);
	}
	
	Moves getDirection(int[] vector) {
		switch(vector[0]) {
		//		moving along the same file
		case 0:
			switch(vector[1]) {
			case 1:
				return Moves.North;
			case -1:
				return Moves.South;
			}
			break;
		//		moving to higher letters
		case 1:
			switch(vector[1]) {
			case 1:
				return Moves.Northeast;
			case -1:
				return Moves.Southeast;
			default:
				return Moves.East;
			}
		//		Moving to lower letters
		case -1:
			switch(vector[1]) {
			case 1:
				return Moves.Northwest;
			case -1:
				return Moves.Southwest;
			default:
				return Moves.West;
			}
		default:
			return null;
		}
		return null;
	}

}
