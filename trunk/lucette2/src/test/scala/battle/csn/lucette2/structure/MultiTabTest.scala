package battle.csn.lucette2.structure

import org.scalatest.junit.AssertionsForJUnit
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import battle.csn.lucette2.game.logic.NegaMax
import battle.csn.lucette.game.board.IBoard
import org.junit.Assert._
import org.junit._
import org.scalatest.mock._
import org.mockito.Mockito._
import battle.csn.lucette2.game.structure.MultiTab

@RunWith(classOf[MockitoJUnitRunner])
class MultiTabTest extends AssertionsForJUnit with MockitoSugar {

  @Test
  def updateTabTest() {
    var t = new MultiTab[Int]()
    t.updateSizes(Seq(3, 3))
    println (t.toString())
  }


}