package fr.paris.lutece.plugins.pac.service.pacuser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import fr.paris.lutece.plugins.pac.bean.pacconfig.Pacconfig;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.service.pacconfig.PacconfigService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConstants;
import fr.paris.lutece.test.LuteceTestCase;


/**
 * JUnit for @link{PacuserService}
 * @author jchaline
 */
public class PacuserServiceTest extends LuteceTestCase
{
    private static final int NB_USER = 4;
    
    @Test
    public void testUserAcceptDate(){
    	Calendar cal = new GregorianCalendar(Locale.FRANCE);
    	cal.set(2014, Calendar.JANUARY, 5);
    	Date dateEntree = cal.getTime();
    	cal.set(2014, Calendar.APRIL, 5);
    	Date dateDernierPac = cal.getTime();
    	cal.set(2014, Calendar.JULY, 14);
    	Date dateConge = cal.getTime();
    	Date dateTestFailed = cal.getTime();
    	cal.set(2014, Calendar.AUGUST, 14);
    	Date dateTestSuccess = cal.getTime();
    	
    	Pacuser user = new Pacuser( );
        user.setId( 1 );
        user.setNom( "Nadal" );
        user.setNom( "Rafael" );
        user.setDateEntree(dateEntree);
    	user.setDernierPac(dateDernierPac);
    	
    	Pacdate pacdate = new Pacdate();
    	pacdate.setDate(dateConge);
    	pacdate.setId(1);
    	pacdate.setPacuser(user);
    	pacdate.setType(PacConstants.TYPE_DATE_CONGE);
    	user.getJoursConges().add(pacdate);
    	
    	PacuserService pacuserService = new PacuserService();
    	boolean acceptDateTrue = pacuserService.userAcceptDatePac(user, dateTestFailed, PacconfigService.getDefaultConfig());
    	assertFalse(acceptDateTrue);

    	boolean acceptDateFalse = pacuserService.userAcceptDatePac(user, dateTestSuccess, PacconfigService.getDefaultConfig());
    	assertTrue(acceptDateFalse);
    }

    @Test
    public void testOrderWithDate( )
    {
        List<Pacuser> list = initListPacuser( true );

        PacuserService.doOrderNextPac( list );

        assertTrue( list.get( 0 ).getId( ).equals( 1 ) );
        assertTrue( list.get( 1 ).getId( ).equals( 2 ) );
        assertTrue( list.get( 2 ).getId( ).equals( 0 ) );
        assertTrue( list.get( 3 ).getId( ).equals( 3 ) );
    }
    
    @Test
    public void testOrderWithDelayBeforePac( )
    {
        List<Pacuser> list = initListPacuser( true );

        PacuserService.doOrderNextPac( list );

        assertTrue( list.get( 0 ).getId( ).equals( 1 ) );
        assertTrue( list.get( 1 ).getId( ).equals( 2 ) );
        assertTrue( list.get( 2 ).getId( ).equals( 0 ) );
        assertTrue( list.get( 3 ).getId( ).equals( 3 ) );

        Pacconfig config = PacconfigService.getDefaultConfig();

        PacuserService userService = new PacuserService();

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
