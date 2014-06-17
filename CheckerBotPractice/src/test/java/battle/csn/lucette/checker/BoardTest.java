package battle.csn.lucette.checker;
import junit.framework.Assert;

import org.junit.Test;


public class BoardTest {

	@Test
	public void testPossibleMoveAtStart() {
		Board b = new Board();
		b.reset();
		Assert.assertEquals(Board.SIZE-1, b.getPossibleMoves().size());		
		Assert.assertEquals(0, b.evaluateBoard());	
	}
	
	@Test
	public void testNoMove() {
		Board b = new Board();
		//only one white an black reach the other side.
		b.cases[0][0] = Board.BLACK;
		b.cases[Board.SIZE-1][0] = Board.WHITE;
		Assert.assertEquals(0, b.getPossibleMoves().size());
		//in case of equality white win
		Assert.assertEquals(Integer.MAX_VALUE, b.evaluateBoard());
		//we add a black black become the winner
		b.cases[0][2] = Board.BLACK;
		Assert.assertEquals(Integer.MIN_VALUE, b.evaluateBoard());
	}
	
	

}
