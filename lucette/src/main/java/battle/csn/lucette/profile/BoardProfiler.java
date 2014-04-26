package battle.csn.lucette.profile;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class BoardProfiler
{
    static final Logger logger = Logger.getLogger( BoardProfiler.class );

    @Pointcut( "execution(* battle.csn.lucette.game.board.IBoard.getBoard(..))" )
    public void getStringBoard( )
    {
    }

    @Around( "getStringBoard()" )
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
