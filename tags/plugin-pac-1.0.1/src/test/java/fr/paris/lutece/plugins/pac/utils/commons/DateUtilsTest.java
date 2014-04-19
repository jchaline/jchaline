package fr.paris.lutece.plugins.pac.utils.commons;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import fr.paris.lutece.test.LuteceTestCase;


/**
 * JUnit for @link{DateUtils}
 * @author jchaline
 */
public class DateUtilsTest extends LuteceTestCase
{
    @Test
    public void testDateCompare( )
    {
        GregorianCalendar cal = new GregorianCalendar( );
        cal.set( 2013, 12, 31 );
        Date dayOne = cal.getTime( );

        cal.set( 2013, 12, 30 );
        Date dayTwo = cal.getTime( );

        cal.set( 2014, 1, 1 );
        Date dayThree = cal.getTime( );
        assertEquals( DateUtils.compare( dayOne, dayTwo ), 1 );
        assertEquals( DateUtils.compare( dayThree, dayTwo ), 1 );
        assertEquals( DateUtils.compare( dayOne, dayThree ), -1 );
    }
}
