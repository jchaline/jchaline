package battle.csn.lucette2.engine

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.scalatest.mock.MockitoSugar
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test
import scala.None

@RunWith(classOf[MockitoJUnitRunner])
class EngineTest extends AssertionsForJUnit with MockitoSugar {

  @Test
  def initEngineTest() {
    var e = new Engine[Int]("game1");
  }

  @Test
  def moveAvailableTest() {
    var e = new Engine[Int]("game1");
    var currentPlayer = "player1"
    var optM = e.chooseMove(currentPlayer)
    optM match {
      case Some(m) => e.play(currentPlayer, m)
      case default => fail("No move find")
    }
    currentPlayer = "player2"
    optM = e.chooseMove(currentPlayer)
    optM match {
      case Some(m) => e.play(currentPlayer, m)
      case default => fail("No move find")
    }
    currentPlayer = "player2"
    optM = e.chooseMove(currentPlayer)
    optM match {
      case Some(m) => e.play(currentPlayer, m)
      case default => fail("No move find")
    }
  }

}