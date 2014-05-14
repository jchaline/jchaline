package battle.csn.lucette.machine.states;

import org.apache.log4j.Logger;

import battle.csn.lucette.machine.Chain;
import battle.csn.lucette.machine.states.framework.AbstractFinalState;


public class PerduState extends AbstractFinalState
{
    private static final Logger LOGGER = Logger.getLogger( PerduState.class );

    @Override
    public void pull( Chain wrapper )
    {
        LOGGER.warn( "PERDU" );
    }

}
