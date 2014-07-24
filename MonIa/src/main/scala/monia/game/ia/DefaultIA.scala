package monia.game.ia

import monia.game.monster.Monster
import org.apache.log4j.Logger

class DefaultIA extends IA {

  val logger = Logger.getLogger(classOf[DefaultIA])
  def attack(owner: Monster, target: Monster) {
    logger.debug("use default ia for +" + owner + " on " + target)
  }
}