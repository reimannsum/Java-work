package game.chess.pieces;

public class Piece implements Comparable<Piece>{
	private Colors color;
	private Type type;
	static int blackCount;
	static int whiteCount;
	private enum Colors { Black, White };
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
		}
		char getRepresentation() {
			return representation;
		}
		double getPoints() {
			return points;
		}
	};
	public static final String BLACK = "black";
	public static final String WHITE = "white";
	private double strength = 0;
	

	private Piece(Type type){
		this.type = type;
		this.strength = type.getPoints();
	}
	private Piece() {
		type = Type.NO_PIECE;
	}
	public static Piece createWhite(Type type) {
		Piece piece =  new Piece(type);
		piece.color = Colors.White;
		incrementWhiteCount();
		return piece;
	}
	public static Piece createBlack(Type type) {
		Piece piece =  new Piece(type);
		piece.color = Colors.Black;
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
		return (this.color == Colors.White);
	}
	public boolean isBlack() {
		return (this.color == Colors.Black);
	}
	public boolean isEmpty() {
		return (this.type == Type.NO_PIECE);
	}
	public String getColor() {
		if (color == Colors.Black)
			return BLACK;
		else
			return WHITE;
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
		return type.getPoints();
	}
	public void setStrength(double value) {
		this.strength = value;
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
	public int compareTo(Piece that) {
		return Integer.compare(this.getStrength(), that.getStrength());
	}
	

}
