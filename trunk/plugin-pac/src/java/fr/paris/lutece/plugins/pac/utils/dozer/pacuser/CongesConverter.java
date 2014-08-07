package fr.paris.lutece.plugins.pac.utils.dozer.pacuser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.utils.commons.PacConstants;
import fr.paris.lutece.portal.service.util.AppLogService;


/**
 * Convert Pacuser & PacuserDTO
 * @author jchaline
 * 
 */
public class CongesConverter implements CustomConverter
{
    private static final SimpleDateFormat formater = new SimpleDateFormat( "dd/MM/yyyy" );

    @Override
    public Object convert( Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass )
    {
        Object result = null;
        if ( source != null )
        {
            if ( source instanceof List )
            {
                List<Pacdate> sourceCast = (List<Pacdate>) source;
                StringBuilder target = new StringBuilder( );
                Iterator<Pacdate> iterator = sourceCast.iterator( );
                while ( iterator.hasNext( ) )
                {
                    Pacdate value = iterator.next( );
                    target.append( formater.format( value.getDate( ) ) );
                    if ( iterator.hasNext( ) )
                    {
                        target.append( PacConstants.PARAMETER_STR_DATE_SEPARATOR );
                    }
                }
                result = target.toString( );
            }
            else if ( source instanceof String )
            {
                String sourceCast = (String) source;
                if ( StringUtils.isNotBlank( sourceCast ) )
                {
                    String[] strDates = sourceCast.split( PacConstants.PARAMETER_STR_DATE_SEPARATOR );
                    List<Pacdate> target = new ArrayList<Pacdate>( );

                    for ( String strDate : strDates )
                    {
                        try
                        {
                            Date date = formater.parse( strDate );
                            Pacdate pacdate = new Pacdate( );
                            pacdate.setDate( date );
                            pacdate.setType( PacConstants.TYPE_DATE_CONGE );
                            target.add( pacdate );
                        }
                        catch ( ParseException e )
                        {
                            AppLogService.error( "Format de date incorrect : ", new MappingException(
                                    "Converter str to date exception : Args is " + destination + " and " + source ) );
                        }
                    }
                    result = target;
                }
            }
            else
            {
                throw new MappingException( "Converter exception : Args is " + destination + " and " + source );
            }
        }
        return result;
    }
}
