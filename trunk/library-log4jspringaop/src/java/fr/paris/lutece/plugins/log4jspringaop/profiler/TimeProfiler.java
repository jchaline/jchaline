package fr.paris.lutece.plugins.log4jspringaop.profiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;


public class TimeProfiler
{
    private static final Logger logger = Logger.getLogger( TimeProfiler.class );
    private static final Map<String, List<Long>> _methodsTimes = new HashMap<String, List<Long>>( );

    /**
     * Enregistre le temps d'execution d'une methode et affiche la moyenne des
     * temps enregistrées jusqu'à présent
     * 
     * @param joinPoint le pointcut sur l'execution de la méthode
     * @return le résultat de l'execution de la méthode
     * @throws Throwable diverses erreurs possibles
     */
    public Object timeProfile( ProceedingJoinPoint joinPoint ) throws Throwable
    {
        long debut = System.currentTimeMillis( );
        Object sortie = joinPoint.proceed( );
        long tempsPasse = System.currentTimeMillis( ) - debut;

        logger.info( getStrLog( joinPoint, tempsPasse ) );

        return sortie;
    }
    
    /**
     * Get the str content for the method calling log
     * @param joinPoint the joinPoint
     * @param tempsPasse the time to process
     * @return the loged content
     */
    private String getStrLog( ProceedingJoinPoint joinPoint, long tempsPasse )
    {
        long moyenne = addTime( joinPoint.getSignature( ).toString( ), tempsPasse );
        int nbCall = _methodsTimes.get( joinPoint.getSignature( ).toString( ) ).size( );
        return "Time for method #" + joinPoint.getSignature( ).getName( ) + "# : " + tempsPasse + " ms (moyenne : "
                + moyenne + "ms pour " + nbCall + " appels).";
    }

    /**
     * Ajoute le temps d'execution d'une methode et retourne le temps moyen
     * 
     * @param signature identifiant unique de la methode analysé
     * @param time temps d'execution de la méthode
     * @return le temps moyen constaté jusqu'à présent
     */
    private Long addTime( final String signature, final long time )
    {
        if ( !_methodsTimes.containsKey( signature ) )
        {
            _methodsTimes.put( signature, new ArrayList<Long>( ) );
        }
        _methodsTimes.get( signature ).add( time );
        return moyenne( _methodsTimes.get( signature ) );
    }

    /**
     * Calcule la moyenne des elements d'une liste
     * 
     * @param list entiers
     * @return la valeur moyenne
     */
    private Long moyenne( final List<Long> list )
    {
        Long result = (long) 0;
        for ( Long elem : list )
        {
            result += elem;
        }
        result /= list.size( );

        return result;
    }

}
