package fr.paris.lutece.plugins.pac.utils.dozer.pacuser;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.dto.pacdate.PacdateDTO;


public class PacdateConverter implements CustomConverter
{

    @Override
    public Object convert( Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass )
    {
        Object result = null;
        if ( source != null )
        {
            if ( source instanceof PacdateDTO )
            {

            }
            else if ( source instanceof Pacdate )
            {

            }
            else
            {
                throw new MappingException( "Converter exception : Args is " + destination + " and " + source );
            }
        }

        return result;
    }

}
