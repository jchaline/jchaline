package battle.csn.lucette2.board

import org.scalatest.junit.AssertionsForJUnit
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import battle.csn.lucette2.game.logic.NegaMax
import battle.csn.lucette.game.board.IBoard
import org.junit.Assert._
import org.junit._
import org.scalatest.mock._
import org.mockito.Mockito._
import battle.csn.lucette2.game.board.DameBoard

@RunWith(classOf[MockitoJUnitRunner])
class DameBoardTest {

  @Test
  def moveAvailablesTest() {
    var board = new DameBoard()
    var ms = board.moveAvailables(1)
    println(ms.size)
  }

  @Test
  def toStringTest() {
    var board = new DameBoard()
    var res = board.toString()
    board.updateBoard(res)
    var res2 = board.toString
    assertTrue(res.equals(res))
  }

}