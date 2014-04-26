package battle.csn.lucette.machine.states;

import battle.csn.lucette.machine.Chain;
import battle.csn.lucette.machine.FightStateMachine;
import battle.csn.lucette.machine.states.framework.AbstractIntermediateState;
import battle.csn.lucette.service.RestClientAPI;

public class InitialState extends AbstractIntermediateState {

	private static final String OUI = "OUI";
	private static final String NON = "NON";
	private static final String GAGNE = "GAGNE";
	private static final String PERDU = "PERDU";

	@Override
	public void pull(Chain chain) {
		FightStateMachine stateMachine = chain.getStateMachine();
		String idPartie = stateMachine.getGame();
		String idEquipe = stateMachine.getIdEquipe();
		String gameStatus = new RestClientAPI().getGameStatus(idPartie,
				idEquipe);
		switch (gameStatus) {
		case OUI:
			chain.setState(new PlayState());
			break;
		case NON:
			try {
				Thread.sleep(200);
				chain.setState(new InitialState());
			} catch (InterruptedException e) {
				chain.setState(new ErrorState(e.getMessage()));
			}
			break;
		case GAGNE:
			chain.setState(new GagneState());
			break;

		case PERDU:
			chain.setState(new PerduState());
			break;

		default:
			chain.setState(new ErrorState("I don't know what to do with this"));
			break;
		}
	}

}
