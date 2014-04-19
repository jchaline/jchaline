package fr.paris.lutece.plugins.pac.dao.pacconfig;

import fr.paris.lutece.plugins.pac.bean.pacconfig.Pacconfig;
import fr.paris.lutece.plugins.pac.dao.AbstractPacDAO;


/**
 * The Pacuser DAO class
 * @author jchaline
 */
public class PacconfigDAO extends AbstractPacDAO<Integer, Pacconfig> implements IPacconfigDAO
{

    protected Class<Pacconfig> getBeanClass( )
    {
        return Pacconfig.class;
    }
}