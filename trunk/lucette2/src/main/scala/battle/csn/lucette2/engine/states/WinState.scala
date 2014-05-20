package battle.csn.lucette2.engine.states

import battle.csn.lucette2.engine.Chain
import org.apache.log4j.Logger
import battle.csn.lucette2.engine.states.framework.AbstractFinalState

class WinState extends AbstractFinalState {
  def LOGGER = Logger.getLogger(classOf[WinState]);

  override def pull(wrapper: Chain) {
    LOGGER.warn("");
  }
}