package battle.csn.lucette2.engine

import battle.csn.lucette2.game.board.Board
import org.springframework.context.ApplicationContext
import battle.csn.lucette2.game.bot.Bot
import org.springframework.context.support.ClassPathXmlApplicationContext
import battle.csn.lucette2.game.logic.Logic
import org.apache.log4j.Logger

class Engine[T](idGame: String) {

  def LOGGER = Logger.getLogger(classOf[Engine[T]]);

  val SPRING_BEANS_XML = "SpringBeans.xml"
  var context: ApplicationContext = _
  var board: Option[Board[T]] = _
  var bot: Option[Bot[T]] = _

  {
    context = new ClassPathXmlApplicationContext(SPRING_BEANS_XML)
    board = Some(context.getBean(classOf[Board[T]]))
    bot = Some(context.getBean(classOf[Bot[T]]))

    var logic = Option(context.getBean(classOf[Logic]));

    bot match {
      case Some(b) => b.logic = logic
      case None => LOGGER.error("Error while getting logic bean")
    }
  }

}