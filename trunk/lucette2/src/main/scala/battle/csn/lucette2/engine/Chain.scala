package battle.csn.lucette2.engine

import battle.csn.lucette2.engine.states.framework.State
import battle.csn.lucette2.engine.states.InitialState

class Chain (var stateMachine:FightStateMachine){
    var current:State = new InitialState( )

    def pull( ){
        current.pull( this )
    }

    def done( )={
        current.done( )
    }
}