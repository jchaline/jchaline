package monia.game.effect

import org.springframework.stereotype.Service
import org.apache.log4j.Logger

@Service("poison")
class Poison(probability: Int) extends AbstractEffect(probability) {
  val logger = Logger.getLogger(classOf[Poison])
  def apply() {
    logger.debug("in Poison class")
  }
}