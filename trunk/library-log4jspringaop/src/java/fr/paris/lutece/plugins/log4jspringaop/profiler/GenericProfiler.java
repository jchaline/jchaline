package fr.paris.lutece.plugins.log4jspringaop.profiler;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;


public class GenericProfiler
{
    static final Logger logger = Logger.getLogger( GenericProfiler.class );

    public Object afficherTrace( ProceedingJoinPoint joinPoint ) throws Throwable
    {
        long debut = System.currentTimeMillis( );
        Object sortie = joinPoint.proceed( );
        long tempsPasse = System.currentTimeMillis( ) - debut;

        logger.info( "Temps d'exécution de la fonction (" + joinPoint.getSignature( ).getName( ) + "): " + tempsPasse
                + " milliseconds." );
        
        System.out.println("test");

        return sortie;
    }
}
