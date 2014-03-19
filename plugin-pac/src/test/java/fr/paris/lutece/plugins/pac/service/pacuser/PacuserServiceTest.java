package fr.paris.lutece.plugins.pac.service.pacuser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import fr.paris.lutece.plugins.pac.bean.pacconfig.Pacconfig;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.service.pacconfig.IPacconfigService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.test.LuteceTestCase;


/**
 * JUnit for @link{PacuserService}
 * @author jchaline
 */
public class PacuserServiceTest extends LuteceTestCase
{
    private static final int NB_USER = 4;

    public void testOrderWithDate( )
    {
        List<Pacuser> list = initListPacuser( false );

        PacuserService.doOrderNextPac( list );

        assertTrue( list.get( 0 ).getId( ).equals( 1 ) );
        assertTrue( list.get( 1 ).getId( ).equals( 2 ) );
        assertTrue( list.get( 2 ).getId( ).equals( 0 ) );
        assertTrue( list.get( 3 ).getId( ).equals( 3 ) );
    }

    public void testOrderWithDelayBeforePac( )
    {
        List<Pacuser> list = initListPacuser( true );

        PacuserService.doOrderNextPac( list );

        assertTrue( list.get( 0 ).getId( ).equals( 1 ) );
        assertTrue( list.get( 1 ).getId( ).equals( 2 ) );
        assertTrue( list.get( 2 ).getId( ).equals( 0 ) );
        assertTrue( list.get( 3 ).getId( ).equals( 3 ) );

        Pacconfig config = new Pacconfig( );
        config.setDayFrequency( 7 );
        config.setMonthFrequency( 0 );
        config.setDayWait( 0 );
        config.setMonthWait( 2 );
        GregorianCalendar calendar = new GregorianCalendar( );
        calendar.set( 2014, Calendar.JANUARY, Calendar.FRIDAY );
        config.setFirstDate( calendar.getTime( ) );
        config.setTeam( "Mairie de Paris" );

        IPacuserService userService = (IPacuserService) SpringContextService.getBean( PacConfigs.BEAN_PACUSER_SERVICE );

        userService.associatePacDate( list, new Date( ), config );
    }

    private static List<Pacuser> initListPacuser( boolean withDateDernierPac )
    {
        GregorianCalendar gCalendar = new GregorianCalendar( );
        List<Date> listDateEntrees = new ArrayList<Date>( );
        gCalendar.set( 2013, 2, 4 );
        listDateEntrees.add( gCalendar.getTime( ) );
        gCalendar.set( 2012, 2, 1 );
        listDateEntrees.add( gCalendar.getTime( ) );
        gCalendar.set( 2012, 6, 13 );
        listDateEntrees.add( gCalendar.getTime( ) );
        gCalendar.set( 2013, 8, 26 );
        listDateEntrees.add( gCalendar.getTime( ) );

        List<Pacuser> list = new ArrayList<Pacuser>( );
        for ( int i = 0; i < NB_USER; i++ )
        {
            Pacuser user = new Pacuser( );
            user.setId( i );
            user.setNom( "Nom" + i );
            user.setNom( "Prenom" + i );
            user.setDateEntree( listDateEntrees.get( i ) );
            if ( withDateDernierPac )
            {
                user.setDernierPac( DateUtils.add( user.getDateEntree( ), Calendar.MONTH, 2 ) );
            }
            list.add( user );
        }
        return list;
    }
}
