package battle.csn.lucette2.engine

import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._
import org.scalatest.mock.MockitoSugar
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Test
import scala.None
import org.apache.log4j.Logger
import battle.csn.lucette2.game.board.Board

@RunWith(classOf[MockitoJUnitRunner])
class EngineTest extends AssertionsForJUnit with MockitoSugar {
  
  var LOGGER = Logger.getLogger(classOf[EngineTest])

  @Test
  def initEngineTest() {
    var e = new Engine[Int]("game1");
  }

  @Test
  def moveAvailableTest() {
    var players = Map(1-> "player1", -1->"player2")
    var turn = 1
    var e = new Engine[Int]("game1");
    
    var status = e.gameStatus(players(turn))
    while(status.equals(Board.OUI)){
      
        var optM = e.chooseMove(players(turn))
                optM match {
                case Some(m) => e.play(players(turn), m);LOGGER.debug(m)
                case default => fail("No move find")
        }
        turn *= -1
    }
  }

}