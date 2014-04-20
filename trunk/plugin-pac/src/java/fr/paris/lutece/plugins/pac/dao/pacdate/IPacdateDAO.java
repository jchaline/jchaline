package fr.paris.lutece.plugins.pac.dao.pacdate;

import fr.paris.lutece.plugins.genericjpa.dao.IPluginDAO;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;


public interface IPacdateDAO extends IPluginDAO<Integer, Pacdate>
{
    /**
     * Remove all date with owner id
     * @param idOwner the owner id
     */
    void removeWithOwnerId( Integer idOwner );
}
