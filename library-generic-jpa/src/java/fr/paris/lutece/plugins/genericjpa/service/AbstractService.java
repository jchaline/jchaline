package fr.paris.lutece.plugins.genericjpa.service;

import fr.paris.lutece.plugins.genericjpa.bean.AbstractBean;
import fr.paris.lutece.plugins.genericjpa.bean.AbstractFilter;
import fr.paris.lutece.plugins.genericjpa.dao.IPluginDAO;
import fr.paris.lutece.plugins.genericjpa.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.genericjpa.dao.commons.ResultList;


public abstract class AbstractService<K, E extends AbstractBean<K>> implements IPluginService<K, E>
{

    public abstract IPluginDAO<K, E> getPluginDao( );

    @Override
    public void doSaveBean( E bean )
    {
        if ( bean.getId( ) != null )
        {
            getPluginDao( ).update( bean );
        }
        else
        {
            getPluginDao( ).create( bean );
        }
    }

    @Override
    public void doDeleteBean( K key )
    {
        getPluginDao( ).remove( key );
    }

    @Override
    public ResultList<E> findAll( PaginationProperties paginationProperties )
    {
        return getPluginDao( ).findAll( paginationProperties );
    }

    @Override
    public ResultList<E> find( AbstractFilter<K> filter, PaginationProperties paginationProperties )
    {
        return getPluginDao( ).find( filter, paginationProperties );
    }
    
    @Override
    public E findByPrimaryKey(K key){
        return getPluginDao( ).findById( key );
    }

}
