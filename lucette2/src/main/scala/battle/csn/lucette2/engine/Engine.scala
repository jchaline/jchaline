package battle.csn.lucette2.engine

import battle.csn.lucette2.game.board.Board
import org.springframework.context.ApplicationContext
import battle.csn.lucette2.game.bot.Bot
import org.springframework.context.support.ClassPathXmlApplicationContext
import battle.csn.lucette2.game.logic.Logic
import org.apache.log4j.Logger
import battle.csn.lucette2.game.structure.Move
import battle.csn.lucette2.game.structure.State

class Engine[T](idGame: String) {

  def LOGGER = Logger.getLogger(classOf[Engine[T]]);

  val SPRING_BEANS_XML = "SpringBeans.xml"
  var context: ApplicationContext = _
  var board: Option[Board[T]] = _
  var bot: Option[Bot[T]] = _

  {
    LOGGER.debug("Init spring context")

    context = new ClassPathXmlApplicationContext(SPRING_BEANS_XML)
    board = Some(context.getBean(classOf[Board[T]]))
    bot = Some(context.getBean(classOf[Bot[T]]))

    var logic = Option(context.getBean(classOf[Logic]));

    bot match {
      case Some(b) => b.logic = logic
      case None => LOGGER.error("Error while getting logic bean")
    }
  }

  def play(player: String, move: Move) {
    board match {
      case Some(b) => b.play(player, move)
      case default => LOGGER.error("Error while playing move")
    }
  }
  
  def gameStatus(playerName:String)={
    board match{
      case Some(b) => b.gameStatus(playerName)
      case default => Board.ERROR
    }
  }

  def chooseMove(player: String) = {
    bot match {
      case Some(robot) =>
        board match {
          case Some(b) => robot.chooseMove(player, b)
          case default => None
        }
      case default => None
    }
  }

}