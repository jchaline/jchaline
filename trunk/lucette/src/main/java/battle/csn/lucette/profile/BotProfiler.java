package battle.csn.lucette.profile;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;


public class BotProfiler
{
    static final Logger logger = Logger.getLogger( BotProfiler.class );

    public Object profile( ProceedingJoinPoint joinPoint ) throws Throwable
    {
        long debut = System.currentTimeMillis( );
        Object sortie = joinPoint.proceed( );
        long tempsPasse = System.currentTimeMillis( ) - debut;

        logger.info( "Temps d'exécution de la fonction (" + joinPoint.getSignature( ).getName( ) + "): " + tempsPasse
                + " milliseconds." );

        return sortie;
    }
}
