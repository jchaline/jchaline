package battle.csn.lucette.machine;

import battle.csn.lucette.machine.states.InitialState;
import battle.csn.lucette.machine.states.framework.IState;


public class Chain
{
    private IState current;
    private FightStateMachine stateMachine;

    public Chain( FightStateMachine stateMachine )
    {
        this.setStateMachine( stateMachine );
        current = new InitialState( );
    }

    public void setState( IState s )
    {
        current = s;
    }

    public void pull( )
    {
        current.pull( this );
    }

    public boolean done( )
    {
        return current.done( );

    }

    public FightStateMachine getStateMachine( )
    {
        return stateMachine;
    }

    public void setStateMachine( FightStateMachine stateMachine )
    {
        this.stateMachine = stateMachine;
    }
}
