package fr.paris.lutece.plugins.pac.dao;

import java.util.List;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;

public interface IPacDAO<K,E>
{
    /**
     * Return all entities paged.
     *
     * @param paginationProperties properties for pagination
     * @return the result list
     */
    ResultList<E> findAll( PaginationProperties paginationProperties );

    /**
     * Find the bean which correspond to the filter given
     * @param filter the filter to use
     * @param type the type class of the bean
     * @param paginationProperties the pagination properties
     * @return List of entities found.
     */
    ResultList<E> find( GenericJPAFilter<K> filter, PaginationProperties paginationProperties );
    
    /**
     * Create an entity
     * @param entity The entity to create
     */
    void create( E entity );

    /**
     * Update an entity
     * @param entity An entity that contains new values
     */
    void update( E entity );

    /**
     * Remove an entity
     * @param key The key of the entity to remove
     */
    void remove( K key );

    /**
     * Find an entity by its Id
     * @param key The entity key
     * @return The entity object
     */
    E findById( K key );

    /**
     * Find all entities
     * @return A list of entities
     */
    List<E> findAll(  );

    /**
     * Synchronize the persistence context to the underlying database.
     */
    void flush(  );

    /**
     * Remove the given entity from the persistence context, causing a managed entity to become detached.
     * @param entity the entity
     */
    void detach( E entity );
}
