package battle.csn.lucette2.engine.states

import battle.csn.lucette2.engine.states.framework.State
import battle.csn.lucette2.engine.states.framework.AbstractIntermediateState
import battle.csn.lucette2.engine.Chain
import battle.csn.lucette2.network.RestClient

class InitialState extends AbstractIntermediateState {

  override def pull(chain: Chain) {
    var stateMachine = chain.stateMachine
    var idPartie = stateMachine.game
    var idEquipe = stateMachine.idEquipe
    var gameStatus = new RestClient().getGameStatus(idPartie, idEquipe)
    gameStatus match {
      case OUI =>
        chain.current = new PlayState()
      case NON =>
        Thread.sleep(200)
        chain.current = new InitialState()
      case GAGNE =>
        chain.current = new WinState()

      case PERDU =>
        chain.current = new LoseState()

      case default =>
        chain.current = new ErrorState("I don't know what to do with this")
    }
  }
}