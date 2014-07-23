package monia.app

import org.apache.log4j.Logger
import monia.app.service.SpringService
import monia.game.effect.Effect

/**
 * Init some services and test game
 */
object Application {

  val logger = Logger.getLogger(classOf[Application])

  def main(args: Array[String]) {
    logger.debug("init Monia")

//    val beans = SpringService.getBeansOfType(classOf[Effect])
//    logger.debug("find this : " + beans)

  }

}