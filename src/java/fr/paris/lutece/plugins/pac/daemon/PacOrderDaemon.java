package fr.paris.lutece.plugins.pac.daemon;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import fr.paris.lutece.portal.service.daemon.Daemon;
import fr.paris.lutece.portal.service.util.AppLogService;

/**
 * The PAC daemon : send mail and recalculate the date for the next pac
 * @author jchaline
 */
public class PacOrderDaemon extends Daemon
{

    @Override
    public void run( )
    {
        AppLogService.debug( "Run " + this.getClass( ).getName( ) + "Daemon" );
        
        //utiliser une table pour determiner si un daemon est déjà passé pour une date donnée

        //use configuration to ensure send mail x day before the pac day for each team
        Date today = new Date( );
        Calendar cal = new GregorianCalendar( );
        cal.setTime( today );
        switch ( cal.get( Calendar.DATE ) )
        {
        case Calendar.THURSDAY:
            AppLogService.info( "Jeudi : Envoi du mail pour prevenir les personnes concernées" );
            break;
        case Calendar.FRIDAY:
            AppLogService.info( "Vendredi : calcule des prochains PAC" );
            break;
        default:
            ;
        }

    }
}
