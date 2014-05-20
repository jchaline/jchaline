package battle.csn.lucette2.engine

class FightStateMachine(var idEquipe:String, var game:String) {

    def start( )=
    {
        var chain = new Chain( this )
        while ( !chain.done( ) )
        {
            chain.pull( );
        }
        chain.pull( );
    }
}