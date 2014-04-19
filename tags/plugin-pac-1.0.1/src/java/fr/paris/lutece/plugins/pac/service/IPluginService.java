package fr.paris.lutece.plugins.pac.service;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;

@Transactional
public interface IPluginService<K,E>
{
    /**
     * Create or update bean in database
     * @param bean the bean to save
     */
    void doSaveBean( E bean );

    /**
     * Delete bean in database
     * @param key the primary key of the bean to delete
     */
    void doDeleteBean( K key );

    /**
     * Get all the bean from dao
     * @param paginationProperties the pagination properties
     * @return the bean list
     */
    ResultList<E> findAll( PaginationProperties paginationProperties );

    /**
     * Get all the bean matching the filter from dao
     * @param filter the filter
     * @param type the classe object
     * @param paginationProperties the pagination properties
     * @return the bean list
     */
    ResultList<E> find( GenericJPAFilter<K> filter, PaginationProperties paginationProperties );

    /**
     * Find bean with string given as primary key
     * @param primaryKey the key to load bean
     * @return the bean
     */
    E findByStrPrimaryKey( String primaryKey );
    
    /**
     * Find bean with object given as primary key
     * @param primaryKey the key to load bean
     * @return the bean
     */
    E findByPrimaryKey( K primaryKey );
}
