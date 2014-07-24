package monia.game.effect

import org.apache.log4j.Logger
import monia.game.monster.Monster

class DefaultEffect extends AbstractEffect(0) {
  val logger = Logger.getLogger(classOf[DefaultEffect])
  def process(target: Monster) {
    logger.error("in default effectclass")
  }
}