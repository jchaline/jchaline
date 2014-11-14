package battle.csn.lucette2.engine.states

import battle.csn.lucette2.engine.states.framework.AbstractFinalState
import battle.csn.lucette2.engine.Chain
import org.apache.log4j.Logger

class ErrorState(message: String) extends AbstractFinalState {
  def LOGGER = Logger.getLogger(classOf[ErrorState]);

  override def pull(wrapper: Chain) {
    LOGGER.error("ERREUR : " + message)
  }
}