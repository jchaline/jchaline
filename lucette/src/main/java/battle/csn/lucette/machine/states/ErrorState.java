package battle.csn.lucette.machine.states;

import org.apache.log4j.Logger;

import battle.csn.lucette.machine.Chain;
import battle.csn.lucette.machine.states.framework.AbstractFinalState;


public class ErrorState extends AbstractFinalState
{
    private static final Logger LOGGER = Logger.getLogger( ErrorState.class );
    private String message;

    public ErrorState( String message )
    {
        this.message = message;
    }

    @Override
    public void pull( Chain wrapper )
    {
        LOGGER.warn( "ERREUR : " + message );
    }

}
