package battle.csn.lucette2.structure

import org.scalatest.junit.AssertionsForJUnit
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import battle.csn.lucette2.game.logic.NegaMax
import org.junit.Assert._
import org.junit._
import org.scalatest.mock._
import org.mockito.Mockito._
import battle.csn.lucette2.game.board.DameBoard
import battle.csn.lucette2.game.structure.Move


@RunWith(classOf[MockitoJUnitRunner])
class MoveTest extends AssertionsForJUnit with MockitoSugar {
  
  @Test
  def compagnionCreateTest(){
    var m1 = Move(1,2)
    var m2 = new Move(Seq(1,2))
    
  }

}