package fr.paris.lutece.plugins.pac.service;

import fr.paris.lutece.plugins.pac.bean.GenericJPABean;
import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.IPacDAO;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;


public abstract class AbstractPacService<K, E extends GenericJPABean<K>> implements IPacService<K, E>
{

    public abstract IPacDAO<K, E> getPacDao( );

    @Override
    public void doSaveBean( E bean )
    {
        if ( bean.getId( ) != null )
        {
            getPacDao( ).update( bean );
        }
        else
        {
            getPacDao( ).create( bean );
        }
    }

    @Override
    public void doDeleteBean( K key )
    {
        getPacDao( ).remove( key );
    }

    @Override
    public ResultList<E> findAll( PaginationProperties paginationProperties )
    {
        return getPacDao( ).findAll( paginationProperties );
    }

    @Override
    public ResultList<E> find( GenericJPAFilter<K> filter, PaginationProperties paginationProperties )
    {
        return getPacDao( ).find( filter, paginationProperties );
    }
    
    @Override
    public E findByPrimaryKey(K key){
        return getPacDao( ).findById( key );
    }

}
