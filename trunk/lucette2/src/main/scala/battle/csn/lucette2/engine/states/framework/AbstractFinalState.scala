package battle.csn.lucette2.engine.states.framework

trait AbstractFinalState extends State {

  override def done() = {
    true;
  }
}