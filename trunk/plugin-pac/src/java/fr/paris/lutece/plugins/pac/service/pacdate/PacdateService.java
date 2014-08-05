package fr.paris.lutece.plugins.pac.service.pacdate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import fr.paris.lutece.plugins.genericjpa.dao.IPluginDAO;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.dao.pacdate.IPacdateDAO;
import fr.paris.lutece.plugins.pac.service.AbstractPacService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;


public class PacdateService extends AbstractPacService<Integer, Pacdate> implements IPacdateService
{
    private static Logger logger = Logger.getLogger( PacdateService.class );
    @Inject
    @Named( PacConfigs.BEAN_PACDATE_DAO )
    private IPacdateDAO _daoDate;
    
    public static Date getToday(){
    	Calendar cal = new GregorianCalendar();
    	cal.set(Calendar.SECOND,0);
    	cal.set(Calendar.MINUTE,0);
    	cal.set(Calendar.HOUR_OF_DAY,0);
    	return cal.getTime();
    }

    @Override
    public IPluginDAO<Integer, Pacdate> getPluginDao( )
    {
        return _daoDate;
    }

    @Override
    public Pacdate findByStrPrimaryKey( String primaryKey )
    {
        Pacdate bean = null;
        Integer id = null;
        try
        {
            id = Integer.valueOf( primaryKey );
            bean = _daoDate.findById( id );
        }
        catch ( NumberFormatException e )
        {
            logger.error( e );
        }
        return bean;
    }
    
    @Override
    public void removeWithOwnerId( Integer idOwner ){
        _daoDate.removeWithOwnerId( idOwner );
    }
}
