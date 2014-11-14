package fr.jchaline.library.profiler;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;


public class ArgsProfiler
{
    private static final Logger logger = Logger.getLogger( ArgsProfiler.class );

    /**
     * 0 for args and return, 1 for args, 2 for return
     */
    private int config = 0;

    /**
     * Display args of the method call
     * @param joinPoint le pointcut sur l'execution de la méthode
     * @return le résultat de l'execution de la méthode
     * @throws Throwable diverses erreurs possibles
     */
    public Object argsProfile( ProceedingJoinPoint joinPoint ) throws Throwable
    {
        String methodName = joinPoint.getSignature( ).getName( );
        StringBuilder sb = new StringBuilder( );
        sb.append( methodName + " args : " );
        for ( Object arg : joinPoint.getArgs( ) )
        {
            sb.append( arg + "; " );
        }
        if ( joinPoint.getArgs( ).length > 0 )
        {
            logger.info( sb );
        }

        Object sortie = joinPoint.proceed( );

        logger.info( methodName + " return : " + sortie );

        return sortie;
    }

    /**
     * @param config the config to set
     */
    public void setConfig( int config )
    {
        this.config = config;
    }
}
