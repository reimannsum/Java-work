package game;

import junit.framework.TestSuite;

public class ChessTests{
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(game.chess.BoardTest.class);
		suite.addTestSuite(game.chess.GameTest.class);
		suite.addTestSuite(game.chess.pieces.PieceTest.class);
		return suite;
		
	}
	

}
