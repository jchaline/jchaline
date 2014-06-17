package battle.csn.lucette.checker;
import junit.framework.Assert;

import org.junit.Test;


public class StupidIATest {

	/**
	 * Test we find the right move in a very trivial situation for the white :
	 * one white pawn in (0,2) one black pawn in (1,3). The white has the choice 
	 * to go to (1,1) or to take the black pawn by moving to (2,4).
	 */
	@Test	
	public void testFindMoveLowComplexity() {
		Board initial = new Board();
		initial.cases[0][2] = Board.WHITE;
		initial.cases[1][3] = Board.BLACK;
		System.out.println("Checking IA find best move on this board");
		System.out.println(initial);
		Move moveFound = StupidIA.findBestMove(Board.WHITE, initial, 2);
		Move expectedMove = new Move(0,2,2,4);
		Assert.assertEquals(expectedMove, moveFound);
		initial.move(moveFound);
		System.out.println("New board after " + moveFound);
		System.out.println(initial);
		
	}
	
	/**
	 * Test we find the right move in a less trivial situation for the white :
	 * one white pawn in (0,4) against three black pawn 
	 * one in (1,5) that could be taken
	 * one in (1,3) that could be taken either 
	 * one in (2,2) that can retake the white if white decide to take (1,3)
     * Thus the best choice for the white is to take (1,5).
	 */
	@Test	
	public void testFindMoveBiggerComplexity() {
		Board initial = new Board();
		initial.cases[0][4] = Board.WHITE;
		initial.cases[1][3] = Board.BLACK; //could be taken
		initial.cases[1][5] = Board.BLACK; //could be taken
		initial.cases[3][1] = Board.BLACK; //can retake if the taken is 1,3
		System.out.println("Checking IA find best move on this board");
		System.out.println(initial);
		Move moveFound = StupidIA.findBestMove(Board.WHITE, initial, 2);
		Move expectedMove = new Move(0,4,2,6);
		Assert.assertEquals(expectedMove, moveFound);
		initial.move(moveFound);
		System.out.println("New board after " + moveFound);
		System.out.println(initial);
		
	}

}
