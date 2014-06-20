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
import scala.concurrent._
import battle.csn.lucette2.game.board.Board
import scala.concurrent.ExecutionContext.Implicits.global
import battle.csn.lucette2.game.board.DameBoard
import battle.csn.lucette2.game.bot.DameBot

@RunWith(classOf[MockitoJUnitRunner])
class NegaMaxTest extends AssertionsForJUnit with MockitoSugar {

  @Test
  def solveBoardTest() {
    var solver: NegaMax = new NegaMax
    var board = new DameBoard
    
    println("Begin with board : ")
    println(board)
    
    var firstEvaluate = solver.solve(board, (new DameBot).evaluateBoard, 0, Int.MinValue, Int.MaxValue, 1)
    assertTrue(firstEvaluate==0)
    
    println("End with board : ")
    println(board)

    //FIXME
    //var eval = solver.solve(1, mockBoard, 1000, -1000, (x:Int, y: Board[Int]) => 0, true, 2)
    //assertTrue(eval==0)
  }
}