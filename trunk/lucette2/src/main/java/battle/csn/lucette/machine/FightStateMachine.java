package battle.csn.lucette.machine;

public class FightStateMachine
{
    private final String idEquipe;
    private final String game;

    public String getIdEquipe( )
    {
        return idEquipe;
    }

    public String getGame( )
    {
        return game;
    }

    public FightStateMachine( String idEquipe, String game )
    {
        this.idEquipe = idEquipe;
        this.game = game;
    }

    public void start( )
    {
        Chain chain = new Chain( this );
        while ( !chain.done( ) )
        {
            chain.pull( );
        }
        chain.pull( );
    }
}
