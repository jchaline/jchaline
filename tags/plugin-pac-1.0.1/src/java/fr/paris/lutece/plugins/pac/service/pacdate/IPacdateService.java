package fr.paris.lutece.plugins.pac.service.pacdate;

import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.service.IPacService;

public interface IPacdateService extends IPacService<Integer,Pacdate> 
{
    /**
     * Remove all date with owner id
     * @param idOwner the owner id
     */
    void removeWithOwnerId( Integer idOwner );
}
