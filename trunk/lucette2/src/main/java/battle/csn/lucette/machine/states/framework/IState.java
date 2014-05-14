package battle.csn.lucette.machine.states.framework;

import battle.csn.lucette.machine.Chain;

public interface IState {

	public abstract void pull(Chain wrapper);

	public abstract boolean done();
}
