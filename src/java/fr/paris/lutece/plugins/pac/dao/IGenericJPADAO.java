/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.pac.dao;

import java.util.List;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;


/**
 * The interface class for abstract DAO
 *
 * @param <K> the class for entity Key
 * @param <E>  the entity class
 */
public interface IGenericJPADAO<K,E>
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
    ResultList<E> find( GenericJPAFilter<K> filter, Class<E> type, PaginationProperties paginationProperties );
    
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
