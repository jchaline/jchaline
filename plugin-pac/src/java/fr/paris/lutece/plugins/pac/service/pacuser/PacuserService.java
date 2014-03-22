package fr.paris.lutece.plugins.pac.service.pacuser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import fr.paris.lutece.plugins.pac.bean.Closure;
import fr.paris.lutece.plugins.pac.bean.pacconfig.Pacconfig;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.bean.pacuser.PacuserComparator;
import fr.paris.lutece.plugins.pac.dao.IPacDAO;
import fr.paris.lutece.plugins.pac.dao.pacuser.IPacuserDAO;
import fr.paris.lutece.plugins.pac.dto.pacuser.PacuserDTO;
import fr.paris.lutece.plugins.pac.service.AbstractPacService;
import fr.paris.lutece.plugins.pac.service.pacconfig.IPacconfigService;
import fr.paris.lutece.plugins.pac.service.pacconfig.PacconfigService;
import fr.paris.lutece.plugins.pac.service.pacdate.IPacdateService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.beanvalidation.BeanValidationUtil;


/**
 * The Pacuser class service
 * @author jchaline
 */
public class PacuserService extends AbstractPacService<Integer, Pacuser> implements IPacuserService
{
    private static Logger logger = Logger.getLogger( PacuserService.class );

    @Inject
    @Named( PacConfigs.BEAN_PACUSER_DAO )
    private IPacuserDAO _daoPacuser;

    @Inject
    @Named( PacConfigs.BEAN_PACDATE_SERVICE )
    private IPacdateService _servicePacdate;

    @Inject
    @Named( PacConfigs.BEAN_PACCONFIG_SERVICE )
    private IPacconfigService _pacConfigService;

    @Override
    public Pacuser findByStrPrimaryKey( String primaryKey )
    {
        Pacuser bean = null;
        Integer id = null;
        try
        {
            id = Integer.valueOf( primaryKey );
            bean = _daoPacuser.findById( id );
        }
        catch ( NumberFormatException e )
        {
            logger.error( e );
        }
        return bean;
    }

    @Override
    public IPacDAO<Integer, Pacuser> getPacDao( )
    {
        return _daoPacuser;
    }

    @Override
    public void doSaveBean( Pacuser bean )
    {
        //delete old date before update pacuser
        if ( bean.getId( ) != null )
        {
            _servicePacdate.removeWithOwnerId( bean.getId( ) );
        }
        super.doSaveBean( bean );
    }

    @Override
    public void doOrderNextPac( )
    {
        //a l'avenir, utilisation d'une configuration @link{Pacconfig} spécifique à chaque équipe
        List<Pacuser> listPacuser = _daoPacuser.findAll( );

        doSavePreviousPac( listPacuser );

        doOrderNextPac( listPacuser );

        Pacconfig config = new Pacconfig( );
        config.setDayFrequency( 7 );
        config.setMonthFrequency( 0 );
        config.setDayWait( 0 );
        config.setMonthWait( 2 );
        GregorianCalendar calendar = new GregorianCalendar( );
        calendar.set( 2014, Calendar.JANUARY, 3 );
        config.setFirstDate( calendar.getTime( ) );
        config.setTeam( "Mairie de Paris" );

        //determination des prochains pacs
        associatePacDate( listPacuser, new Date( ), config );

        //sauvegarde des pacuser mis à jour
        for ( Pacuser user : listPacuser )
        {
            doSaveBean( user );
        }
    }

    @Override
    public void associatePacDate( List<Pacuser> listUsers, Date startingDate, Pacconfig config )
    {
        List<Pacuser> refCopy = new ArrayList<Pacuser>( );
        refCopy.addAll( listUsers );
        Date firstPac = PacconfigService.findFirstDayForPac( startingDate, config );

        //tant qu'il reste des pacuser sans prochain pac de determiné
        //ne pas tenir compte des jours fériés => créer une liste de jours sans pac dans la config de chaque equipe
        while ( !refCopy.isEmpty( ) )
        {
            boolean notFound = true;
            Iterator<Pacuser> userIterator = refCopy.iterator( );
            while ( userIterator.hasNext( ) && notFound )
            {
                Pacuser user = userIterator.next( );
                if ( userAcceptDatePac( user, firstPac, config ) )
                {
                    user.setProchainPac( firstPac );
                    userIterator.remove( );
                    notFound = false;
                }
            }
            firstPac = PacconfigService.findNextDate( config, firstPac );
        }

        // be aware about hollydays and incoming date
    }

    @Override
    public boolean userAcceptDatePac( Pacuser user, Date date, Pacconfig config )
    {
        boolean result = false;

        Date firstDayAllowed = user.getDateEntree( );
        firstDayAllowed = DateUtils.addMonths( firstDayAllowed, config.getMonthWait( ) );
        firstDayAllowed = DateUtils.addDays( firstDayAllowed, config.getDayWait( ) );
        //si la date actuelle n'est pas dans la période sans pac pour l'utilisateur
        if ( !date.before( firstDayAllowed ) )
        {
            result = !user.getJoursConges( ).contains( date );
        }

        return result;
    }

    /**
     * Save precious pac if exist
     * @param listPacuser the list of users
     */
    private void doSavePreviousPac( List<Pacuser> listPacuser )
    {
        Date today = new Date( );

        Iterator<Pacuser> iterator = listPacuser.iterator( );
        while ( iterator.hasNext( ) )
        {
            Pacuser user = iterator.next( );
            doSaveBean( user );
            if ( user.getProchainPac( ) == null || !today.before( user.getProchainPac( ) ) )
            {
                user.setDernierPac( user.getProchainPac( ) );
                user.setProchainPac( null );
                doSaveBean( user );
            }
        }
    }

    /**
     * Ordonne les users en fonction de leur derniere date de pac ou de leur
     * date d'entrée
     * @param list les users à ordonner
     */
    public static void doOrderNextPac( List<Pacuser> list )
    {
        Collections.sort( list, new PacuserComparator( ) );
    }

    @Override
    public List<String[]> doDownload( )
    {
        List<Pacuser> all = _daoPacuser.findAll( );
        List<PacuserDTO> allDto = PacuserDTO.convert( all );

        List<String[]> data = new ArrayList<String[]>( );
        String[] header = { "Id", "Nom", "Prenom", "Email", "Entree", "Sortie", "Conges", "Prochain PAC" };
        data.add( header );

        for ( PacuserDTO pacuser : allDto )
        {
            String[] array = transform( pacuser );
            data.add( array );
        }

        return data;
    }

    /**
     * Make String array with dto datas
     * @param pacuser the dto to transform
     * @return the string datas array
     */
    private String[] transform( PacuserDTO pacuser )
    {
        String[] array = new String[8];
        array[0] = String.valueOf( pacuser.getId( ) );
        array[1] = pacuser.getNom( );
        array[2] = pacuser.getPrenom( );
        array[3] = pacuser.getEmail( );
        array[4] = pacuser.getDateEntree( );
        array[5] = pacuser.getDateSortie( );
        array[6] = pacuser.getJoursConges( );
        array[7] = pacuser.getProchainPac( );
        return array;
    }

    /**
     * Make dto with String array datas
     * @param pacuser the dto to transform
     * @return the string datas array
     */
    private PacuserDTO transform( List<String> line )
    {
        int i = 0;
        PacuserDTO pacuser = new PacuserDTO( );
        try
        {
            pacuser.setId( Integer.valueOf( line.get( i++ ) ) );
            pacuser.setNom( line.get( i++ ) );
            pacuser.setPrenom( line.get( i++ ) );
            pacuser.setEmail( line.get( i++ ) );
            pacuser.setDateEntree( line.get( i++ ) );
            pacuser.setDateSortie( line.get( i++ ) );
            pacuser.setJoursConges( line.get( i++ ) );
            pacuser.setProchainPac( line.get( i++ ) );
        }
        catch ( NumberFormatException e )
        {
            AppLogService.info( "Error while getting PacuserDTO from List<String>" );
            pacuser = null;
        }
        return pacuser;
    }

    /**
     * Get the converter function between Pacuser and PacuserDTO as Closure
     * object
     * @return the closure
     * @throws NoSuchMethodException exception when method doesn't exist
     * @throws SecurityException exception when canno't invoke the method
     */
    public static Closure getFuncConverter( ) throws SecurityException, NoSuchMethodException
    {
        Closure converter = null;
        Method convertMethod = null;
        convertMethod = PacuserDTO.class.getMethod( "convert", List.class );
        converter = new Closure( PacuserDTO.class, convertMethod );
        return converter;
    }

    @Override
    public void doUpload( List<List<String>> lines )
    {
        for ( List<String> line : lines )
        {
            PacuserDTO dto = transform( line );
            if ( dto != null && BeanValidationUtil.validate( dto ).isEmpty( ) )
            {

                doSaveBean( dto.convert( ) );
            }
        }
    }
}