package monia.game.effect

import org.apache.log4j.Logger

class DefaultEffect extends AbstractEffect(0) {
  val logger = Logger.getLogger(classOf[DefaultEffect])
  def apply() {
    logger.error("in default effectclass")
  }
}