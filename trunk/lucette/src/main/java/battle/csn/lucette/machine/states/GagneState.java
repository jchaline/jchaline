package battle.csn.lucette.machine.states;

import org.apache.log4j.Logger;

import battle.csn.lucette.machine.Chain;
import battle.csn.lucette.machine.states.framework.AbstractFinalState;


public class GagneState extends AbstractFinalState
{
    private static final Logger LOGGER = Logger.getLogger( GagneState.class );

    @Override
    public void pull( Chain wrapper )
    {
        LOGGER.warn( "GAGNE" );
    }

}
