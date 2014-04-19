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
package fr.paris.lutece.plugins.pac.service;

import org.springframework.transaction.annotation.Transactional;

import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.IGenericJPADAO;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;


/**
 * 
 * @param <K> the bean primary key
 * @param <E> the bean Oclass
 */
@Transactional( rollbackFor = { Exception.class } )
public interface IGenericJPAService<K,E>
{

    /**
     * Create or update bean in database
     * @param bean the bean to save
     */
    void doSaveBean( IGenericJPADAO<K,E> dao, E bean );

    /**
     * Delete bean in database
     * @param key the primary key of the bean to delete
     */
    void doDeleteBean( IGenericJPADAO<K,E> dao, K key );

    /**
     * Get all the bean from dao
     * @param paginationProperties the pagination properties
     * @return the bean list
     */
    ResultList<E> findAll( IGenericJPADAO<K,E> dao, PaginationProperties paginationProperties );

    /**
     * Get all the bean matching the filter from dao
     * @param filter the filter
     * @param type the classe object
     * @param paginationProperties the pagination properties
     * @return the bean list
     */
    ResultList<E> find( IGenericJPADAO<K,E> dao, GenericJPAFilter<K> filter, Class<E> type,
            PaginationProperties paginationProperties );

}
