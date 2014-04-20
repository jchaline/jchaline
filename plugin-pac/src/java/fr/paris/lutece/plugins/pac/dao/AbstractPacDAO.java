package fr.paris.lutece.plugins.pac.dao;

import fr.paris.lutece.plugins.genericjpa.dao.AbstractDAO;

public abstract class AbstractPacDAO<K,E> extends AbstractDAO<K, E>
{

    @Override
    public String getPluginName( )
    {
        return "pac";
    }

}
