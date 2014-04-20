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
package fr.paris.lutece.plugins.genericjpa.utils.dozer;

import fr.paris.lutece.plugins.genericjpa.dao.commons.ResultList;


/**
 * The abstract converter between bean and DTO
 * @param <D> the DTO object
 * @param <E> the database's bean object
 */
public abstract class AbstractDozerConverter<D, E>
{
    /**
     * convert any bean entity to dto
     * @param source the bean
     * @return the DTO
     */
    public abstract D convertEntity( E source );

    /**
     * convert any list bean entity to list dto
     * @param findAll the bean list
     * @return the dto list
     */
    public ResultList<D> convertEntities( ResultList<E> findAll, Class<D> targetType )
    {
        ResultList<D> ret;

        if ( findAll != null )
        {
            ret = new ResultList<D>( );

            for ( E bean : findAll )
            {
                ret.add( convertEntity( bean ) );
            }

            ret.setTotalResult( findAll.getTotalResult( ) );
        }
        else
        {
            ret = null;
        }

        return ret;
    }
}
