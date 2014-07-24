package monia.game.effect

import org.springframework.stereotype.Service
import org.apache.log4j.Logger
import monia.game.monster.Monster

@Service("sleep")
class Sleep(probability: Int) extends AbstractEffect(probability) {
  val logger = Logger.getLogger(classOf[Sleep])
  def process(target: Monster) {
    logger.debug("in Sleep class")
  }
}