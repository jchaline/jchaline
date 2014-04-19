package fr.paris.lutece.plugins.pac.service.pacuser;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.pac.bean.pacconfig.Pacconfig;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.service.IPacService;


/**
 * The Pacuser interface for service
 * @author jchaline
 */
@Transactional
public interface IPacuserService extends IPacService<Integer, Pacuser>
{
    /**
     * Ordonne les users en fonction de leur derniere date de pac ou de leur
     * date d'entrée
     */
    void doOrderNextPac( );

    /**
     * Test si un utilisateur peut être de PAC à la date donnée
     * @param user l'utilisateur à tester
     * @param date la date à tester
     * @param config la configuration à utiliser
     */
    boolean userAcceptDatePac( Pacuser user, Date date, Pacconfig config );

    /**
     * Use any config to affect PAC date for the users list
     * @param listUsers the users to process
     * @param startingDate the date to start the process
     * @param pacConfig the config to use, can be null and use default config
     *            (all friday, 2 months after incoming)
     */
    void associatePacDate( List<Pacuser> listUsers, Date startingDate, Pacconfig pacConfig );

    /**
     * Get the data for all the pacuser
     * @return the pacuser data
     */
    List<String[]> doDownload( );

    /**
     * Parse list of data and create pacuser list
     * @param fileContent the data from csv file
     */
    void doUpload( List<List<String>> fileContent );

    /**
     * Get the list of user present for any day
     * @param day the date for find user
     * @return the list of users present at this day
     */
    List<Pacuser> findUserPresent( Date day);
}
