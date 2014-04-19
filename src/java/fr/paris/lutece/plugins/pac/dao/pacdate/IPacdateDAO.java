package fr.paris.lutece.plugins.pac.dao.pacdate;

import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.dao.IPacDAO;


public interface IPacdateDAO extends IPacDAO<Integer, Pacdate>
{
    /**
     * Remove all date with owner id
     * @param idOwner the owner id
     */
    void removeWithOwnerId( Integer idOwner );
}
