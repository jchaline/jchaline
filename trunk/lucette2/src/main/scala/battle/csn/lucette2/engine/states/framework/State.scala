package battle.csn.lucette2.engine.states.framework

import battle.csn.lucette2.engine.Chain

trait State {
  val OUI = "OK";
  val NON = "NON";
  val KO = "KO";
  val PTT = "PTT";
  val GAGNE = "GAGNE";
  val PERDU = "PERDU";

  def pull(wrapper: Chain): Unit

  def done(): Boolean
}