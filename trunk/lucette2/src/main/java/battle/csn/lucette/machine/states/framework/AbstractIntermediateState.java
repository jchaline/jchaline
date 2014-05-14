package battle.csn.lucette.machine.states.framework;


public abstract class AbstractIntermediateState implements IState {

	@Override
	public final boolean done() {
		return false;
	}

}
