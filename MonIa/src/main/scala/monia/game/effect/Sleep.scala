package monia.game.effect

import org.springframework.stereotype.Service
import org.apache.log4j.Logger

@Service("sleep")
class Sleep(probability: Int) extends AbstractEffect(probability) {
  val logger = Logger.getLogger(classOf[Sleep])
  def apply() {
	  logger.debug("in Sleep class")
  }
}