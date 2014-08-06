package fr.paris.lutece.plugins.pac.service.pacconfig;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import fr.paris.lutece.plugins.pac.bean.pacconfig.Pacconfig;
import fr.paris.lutece.plugins.pac.dao.IPacDAO;
import fr.paris.lutece.plugins.pac.dao.pacconfig.IPacconfigDAO;
import fr.paris.lutece.plugins.pac.service.AbstractPacService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;


public class PacconfigService extends AbstractPacService<Integer, Pacconfig> implements IPacconfigService
{
    private static Logger logger = Logger.getLogger( PacconfigService.class );
    @Inject
    @Named( PacConfigs.BEAN_PACCONFIG_DAO )
    private IPacconfigDAO _daoConfig;

    /**
     * This method find the first day to attribute for user
     * @param startingDate the date where pac should be calculate
     * @param config the config to use
     * @return the first day
     */
    public static Date findFirstDayForPac( Date startingDate, Pacconfig config )
    {
        Date firstPac = config.getFirstDate( );

        //get the first day for pac
        while ( startingDate.after( firstPac ) )
        {
            firstPac = findNextDate( config, firstPac );
        }
        return firstPac;
    }

    /**
     * Find next date for pac
     * @param config the config to use
     * @param date the date reference
     * @return the next date
     */
    public static Date findNextDate( Pacconfig config, Date date )
    {
        date = DateUtils.addDays( date, config.getDayFrequency( ) );
        date = DateUtils.addMonths( date, config.getMonthFrequency( ) );
        return date;
    }

    @Override
    public IPacDAO<Integer, Pacconfig> getPacDao( )
    {
        return _daoConfig;
    }

    @Override
    public Pacconfig findByStrPrimaryKey( String primaryKey )
    {
        Pacconfig bean = null;
        Integer id = null;
        try
        {
            id = Integer.valueOf( primaryKey );
            bean = _daoConfig.findById( id );
        }
        catch ( NumberFormatException e )
        {
            logger.error( e );
        }
        return bean;
    }
}