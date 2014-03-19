package fr.paris.lutece.plugins.pac.utils.commons;

import java.util.Calendar;
import java.util.Date;


public final class DateUtils
{
    /**
     * Add or Substract day, month, year to any date
     * @param date the date to operate
     * @param nbDay the number of days to add or remove
     * @param nbMonth the number of months to add or remove
     * @param nbYear the number of years to add or remove
     * @return the date result
     */
    public static Date add( Date date, int nbDay, int nbMonth, int nbYear )
    {
        Calendar cal = Calendar.getInstance( );
        cal.setTime( date );
        cal.add( Calendar.DAY_OF_YEAR, nbDay );
        cal.add( Calendar.MONTH, nbMonth );
        cal.add( Calendar.YEAR, nbYear );
        return cal.getTime( );
    }

    /**
     * Compare two date, each can be null
     * @param date1 the first date to compare
     * @param date2 the second date to compare
     * @return -1 if date1 should be before date2, 1 for other side, 0 if
     *         equals. A null date is like the most recent date.
     *         So compare(date,null)==-1 and compare(null,date)==1
     */
    public static int compare( Date date1, Date date2 )
    {
        int result = 0;

        if ( date1 != null )
        {
            if ( date2 != null )
            {
                result = date1.compareTo( date2 );
            }
            else
            {
                result = -1;
            }
        }
        else if ( date2 != null )
        {
            result = 1;
        }

        return result;
    }
}
