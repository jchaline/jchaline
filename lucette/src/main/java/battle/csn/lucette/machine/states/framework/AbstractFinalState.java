package battle.csn.lucette.machine.states.framework;


public abstract class AbstractFinalState implements IState {

	@Override
	public final boolean done() {
		return true;
	}

}
