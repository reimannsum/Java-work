package game.chess.pieces;



public class Queen extends Piece{
	private double strength = 9.0;
	
	Queen(Piece.Color color){
		super(color);
	}
	
	public static Queen createWhite() {
		return new Queen(Color.White);
	}
	
	public static Queen createBlack() {
		return new Queen(Color.Black);
	}

}
