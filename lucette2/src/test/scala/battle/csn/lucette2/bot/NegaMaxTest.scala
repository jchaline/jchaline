package battle.csn.lucette2.bot

import org.scalatest.junit.AssertionsForJUnit
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import battle.csn.lucette2.game.logic.NegaMax
import battle.csn.lucette.game.board.IBoard
import org.junit.Assert._
import org.junit._
import org.scalatest.mock._
import org.mockito.Mockito._

@RunWith(classOf[MockitoJUnitRunner])
class NegaMaxTest extends AssertionsForJUnit with MockitoSugar {

  @Test
  def solveBoardTest() {
    var solver: NegaMax = new NegaMax

    val mockBoard = mock[IBoard[Int]]
    when(mockBoard.deepCopy()).thenReturn(mockBoard)

    var eval = solver.solve(mockBoard, 1000, -1000, (x: IBoard[Int]) => 0, true, 2)

    assertTrue(eval==0)
  }
}