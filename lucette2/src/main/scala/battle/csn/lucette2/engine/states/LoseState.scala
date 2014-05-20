package battle.csn.lucette2.engine.states

import org.apache.log4j.Logger
import battle.csn.lucette2.engine.Chain
import battle.csn.lucette2.engine.states.framework.AbstractFinalState

class LoseState extends AbstractFinalState {
  def LOGGER = Logger.getLogger(classOf[LoseState]);

  override def pull(wrapper: Chain) {
    LOGGER.warn("PERDU");
  }
}