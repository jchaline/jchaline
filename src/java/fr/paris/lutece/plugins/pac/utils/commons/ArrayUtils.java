package fr.paris.lutece.plugins.pac.utils.commons;

import org.apache.commons.lang.StringUtils;


/**
 * Array utils
 * @author jchaline
 */
public class ArrayUtils
{
    /**
     * Concat all the elements into one string
     * @param separator the separator
     * @param array the array of string
     * @return the string concatenation
     */
    public static <K> String concat( String separator, K[] array )
    {
        String result = StringUtils.EMPTY;
        if ( array.length > 0 )
        {
            for ( int i = 0; i < array.length; i++ )
            {
                if ( array[i] != null )
                {
                    result+= array[i].toString( ) ;
                    if ( (i+1) < array.length )
                    {
                        result+=separator;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Unconcat all the elements in the string into array
     * @param separator the separator
     * @param list the string to split
     * @return the string array
     */
    public static String[] unconcat( String separator, String list )
    {
        String[] result = null;
        if ( StringUtils.isNotBlank( separator ) && StringUtils.isNotBlank( list ) )
        {
            result = list.split( separator );
        }
        return result;
    }
}
