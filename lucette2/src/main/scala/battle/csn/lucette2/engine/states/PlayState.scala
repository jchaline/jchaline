package battle.csn.lucette2.engine.states

import battle.csn.lucette2.engine.states.framework.AbstractIntermediateState
import battle.csn.lucette2.engine.Chain
import battle.csn.lucette2.network.RestClient
import battle.csn.lucette2.engine.FightStateMachine
import battle.csn.lucette2.engine.Engine
import scala.collection.mutable.MutableList
import battle.csn.lucette2.game.structure.Move
import org.apache.log4j.Logger

class PlayState extends AbstractIntermediateState {

  def LOGGER = Logger.getLogger(classOf[PlayState]);

  def pull(chain: Chain) {
    var stateMachine: FightStateMachine = chain.stateMachine

    var strBoard = new RestClient().getBoard(chain.stateMachine.game)

    var engine = new Engine("")
    var plateau = engine.board

    plateau match {
      case Some(p) => p.updateBoard(strBoard)
      case None => LOGGER.error("Error while updating board")
    }

    var idEquipes = MutableList[String]()
    idEquipes += chain.stateMachine.idEquipe
    idEquipes += "TODO adv"
    //TODO implement new solution
    //plateau.( idEquipes );

    //TODO implement
    //var move = new GameEngine( ).getMove( plateau, chain.getStateMachine( ).getIdEquipe( ) );
    var move = Move()

    var result = new RestClient().play(stateMachine.game, stateMachine.idEquipe, move.positions)

    result match {
      case OUI =>
        chain.current = new InitialState()
      case KO =>
        chain.current = new ErrorState("We did a forbidden move here :/")
      case PTT =>
        System.out.println("Why the f**k did we played now ???")
        chain.current = new InitialState()
      case GAGNE =>
        chain.current = new WinState()
      case default =>
        chain.current = new ErrorState("I don't know what to do with this")
    }
  }

}