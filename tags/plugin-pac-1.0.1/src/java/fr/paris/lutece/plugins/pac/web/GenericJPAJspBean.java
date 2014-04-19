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
package fr.paris.lutece.plugins.pac.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

import fr.paris.lutece.plugins.pac.bean.GenericJPABean;
import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationPropertiesAdapterDataTable;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationPropertiesImpl;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;
import fr.paris.lutece.plugins.pac.service.IPacService;
import fr.paris.lutece.plugins.pac.service.IPluginService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConstants;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;
import fr.paris.lutece.util.datatable.DataTableManager;
import fr.paris.lutece.util.datatable.DataTablePaginationProperties;
import fr.paris.lutece.util.html.Paginator;


/**
 * The abstract class JspBean
 * @param <K> the bean primary key
 * @param <E> the bean class
 */
public abstract class GenericJPAJspBean<K, E extends GenericJPABean<K>> extends PluginAdminPageJspBean
{

    public static final Logger LOGGER = Logger.getLogger( GenericJPAJspBean.class );

    private static final long serialVersionUID = 8195930894349438376L;

    protected static final String MARK_BEAN_ID = "bean_id";
    protected static final String MARK_BEAN = "bean";
    protected static final String MARK_DATA_TABLE_BEAN = "dataTableBean";
    protected static final String PARAMETER_CANCEL = "cancel";
    protected static final String PARAMETER_MACRO_COLUMN_ACTIONS_BEAN = "columnActionsBean";
    protected static final String MARK_DATA_TABLE_MANAGER = "dataTableManager";
    protected static final String MARK_FILTER = "filter";
    protected static final String MARK_BACK_URL = "backUrl";
    protected static final String MARK_LOCAL = "local";
    protected static final String MARK_PLUGIN_NAME = "plugin_name";
    protected static final String MARK_SORTED_ATTRIBUTE_NAME = "sorted_attribute_name";
    protected static final String MARK_ASC_SORT = "asc_sort";

    private static final int N_DEFAULT_ITEMS_PER_PAGE = 50;
    protected int _nItemsPerPage;
    private static final String PARAMETER_CURRENT_PAGEINDEX = StringUtils.EMPTY;

    /**
     * Return a bean for pagination in service/dao using parameter in http
     * request
     * @param request http request
     * @return paginator
     */
    protected PaginationProperties getPaginationProperties( HttpServletRequest request )
    {
        String strPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX,
                PARAMETER_CURRENT_PAGEINDEX );
        int nCurrentPageIndex = 1;

        if ( StringUtils.isNotEmpty( strPageIndex ) )
        {
            nCurrentPageIndex = Integer.valueOf( strPageIndex );
        }

        int nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage,
                N_DEFAULT_ITEMS_PER_PAGE );

        return new PaginationPropertiesImpl( ( nCurrentPageIndex - 1 ) * nItemsPerPage, nItemsPerPage );
    }

    /**
     * Abstract implementation to generate DataTableManager
     * @param request the http request
     * @param service the bean service
     * @param filter the bean filter
     * @param keyDataTable the key to save DataTableManager in Session
     * @param jspManage the url to the manage page
     * @param automatic true to use automatic datatable method, false to use
     *            specific implementation
     * @param converter the bean to dto converter function, can be null if you
     *            don't want to use dto
     * @return the DataTableManager
     */
    protected static <E, D> DataTableManager<D> getAbstractDataTableManager( HttpServletRequest request,
            IPluginService service, GenericJPAFilter filter, String keyDataTable, String jspManage, boolean automatic,
            Function<E, D> converter )
    {
        //si un objet est déjà présent en session, on l'utilise
        DataTableManager dataTableToUse = getDataTableToUse( request, keyDataTable, jspManage );

        ResultList<E> listBean = null;
        if ( automatic )
        {
            listBean = service.findAll( null );
        }
        else
        {
            //determination de l'utilisation d'un nouveau filtre (recherche) ou de celui présent en session (changement de page)
            GenericJPAFilter manualFilter = getFilterToUse( request, filter, MARK_FILTER, dataTableToUse );
            BeanUtils.copyProperties( manualFilter, filter );
            //mise à jour de la pagination dans le data table pour l'afficahge de la page courante et du nombre d'items
            DataTablePaginationProperties updatePaginator = dataTableToUse.getAndUpdatePaginator( request );

            //obtention manuel des beans à afficher
            PaginationProperties paginationProperties = new PaginationPropertiesAdapterDataTable( updatePaginator );
            listBean = service.find( filter, paginationProperties );
        }
        Collection listDTO = converter != null ? new ArrayList<D>( Collections2.transform( listBean, converter ) )
                : listBean;
        request.getSession( ).setAttribute( MARK_FILTER, filter );

        if ( automatic )
        {
            dataTableToUse.filterSortAndPaginate( request, new ArrayList<D>( listDTO ) );
        }
        else
        {
            dataTableToUse.setItems( new ArrayList<D>( listDTO ), listBean.getTotalResult( ) );
        }

        return dataTableToUse;
    }

    /**
     * Get model for create beans
     * @param request the http request
     * @return the abstract model
     */
    public Map<String, Object> getSaveBeanModel( HttpServletRequest request )
    {
        Map<String, Object> model = new HashMap<String, Object>( );
        loadCommonData( request, model );
        String primaryKey = request.getParameter( MARK_BEAN_ID );
        if ( StringUtils.isNotBlank( primaryKey ) )
        {
            E bean = getBeanService( ).findByStrPrimaryKey( primaryKey );
            model.put( MARK_BEAN, bean );
        }
        return model;
    }

    /**
     * Get model for manage beans
     * @param request the http request
     * @return the abstract model
     */
    public Map<String, Object> getManageBeanModel( HttpServletRequest request )
    {
        Map<String, Object> model = new HashMap<String, Object>( );
        loadCommonData( request, model );

        return model;
    }

    /**
     * Load common data usable in all the applications
     * @param request the http request
     * @param model the data model
     */
    private void loadCommonData( HttpServletRequest request, Map<String, Object> model )
    {
        model.put( MARK_LOCAL, request.getLocale( ).getDisplayCountry( ) );
    }

    /**
     * Return the url of the JSP which called the last action.
     * 
     * @param request The Http request
     * @return The url of the last JSP
     */
    protected String doGoBack( HttpServletRequest request, String JSP_MANAGE_BEAN )
    {
        String strJspBack = request.getParameter( MARK_BACK_URL );

        return StringUtils.isNotBlank( strJspBack ) ? ( AppPathService.getBaseUrl( request ) + strJspBack )
                : AppPathService.getBaseUrl( request ) + JSP_MANAGE_BEAN;
    }

    /**
     * Get the correct filter to use with data table manager
     * @param request the http request
     * @param filter the bean filter get with request
     * @param markFilter the key of the filter
     * @param dataTable the datatable to use
     * @param <E> the bean filter type
     * @return the filter to use
     */
    protected static <T> GenericJPAFilter getFilterToUse( HttpServletRequest request, GenericJPAFilter filter,
            String markFilter, DataTableManager<T> dataTable )
    {

        @SuppressWarnings( "unchecked" )
        //utilise un nouveau filtre si on utilise un lien du menu lutece pour acceder a la page de gestion des beans
        GenericJPAFilter filterFromSession = request.getParameter( MARK_PLUGIN_NAME ) != null ? null
                : (GenericJPAFilter) request.getSession( ).getAttribute( markFilter );
        //1) est-ce qu'une recherche vient d'être faite ? 2) est-ce qu'un filtre existe en session ? 3) est-ce que le filtre en session est d'un type héritant du fitre fournit en parametre ?
        GenericJPAFilter filterToUse = request.getParameter( MARK_FILTER ) != null || filterFromSession == null
                || !filterFromSession.getClass( ).isAssignableFrom( filter.getClass( ) ) ? dataTable
                .getAndUpdateFilter( request, filter ) : filterFromSession;

        String sortedAttr = request.getParameter( MARK_SORTED_ATTRIBUTE_NAME );
        String ascSort = request.getParameter( MARK_ASC_SORT );
        if ( StringUtils.isNotBlank( sortedAttr ) && StringUtils.isNotBlank( ascSort ) )
        {
            sortedAttr = "_" + sortedAttr;
            filterToUse.getOrders( ).clear( );
            filterToUse.setOrderAsc( ascSort.toLowerCase( ).equals( PacConstants.TRUE ) );
            filterToUse.getOrders( ).add( sortedAttr );
        }
        return filterToUse;
    }

    /**
     * Get the correct data table manager
     * @param request the http request
     * @param markFilter the key of the filter
     * @param jspManage the jsp file to manage the beans
     * @param <T> the bean filter type
     * @return the DataTableManager
     */
    protected static <T> DataTableManager<T> getDataTableToUse( HttpServletRequest request, String markFilter,
            String jspManage )
    {
        DataTableManager<T> dataTableFromSession = loadDataTableFromSession( request, markFilter );
        DataTableManager<T> dataTablePartner = dataTableFromSession != null ? dataTableFromSession
                : new DataTableManager<T>( jspManage, StringUtils.EMPTY, 10, true );

        return dataTablePartner;
    }

    /**
     * 
     * Get the datatable save in the session
     * @param request the http request
     * @param key the key for the data table
     * @return the DataTableManager keep in session
     */
    @SuppressWarnings( "unchecked" )
    protected static <T> DataTableManager<T> loadDataTableFromSession( HttpServletRequest request, String key )
    {
        DataTableManager<T> dataTable = null;

        Object object = request.getSession( ).getAttribute(
                StringUtils.isNotBlank( key ) ? key : MARK_DATA_TABLE_MANAGER );

        if ( object != null && object instanceof DataTableManager<?> )
        {
            try
            {
                dataTable = (DataTableManager<T>) object;
            }
            catch ( Exception e )
            {
                LOGGER.error( "Error during cast :" + e );
            }
        }

        return dataTable;
    }

    /**
     * Save a Data table manager into the http session with key. Can save
     * various data table.
     * @param request the http request with the user session
     * @param dataTable the datatable
     * @param key the datatable key
     * @param <T> Type of data
     */
    protected <T> void saveDataTableInSession( HttpServletRequest request, DataTableManager<T> dataTable, String key )
    {
        request.getSession( ).setAttribute( StringUtils.isNotBlank( key ) ? key : MARK_DATA_TABLE_MANAGER, dataTable );
    }

    /**
     * Delete a bean object
     * 
     * @param request The Http request
     * @return the html code message
     */
    public abstract String doDeleteBean( HttpServletRequest request );

    /**
     * Returns the confirmation message to delete a bean
     * 
     * @param request The Http request
     * @return the html code message
     */
    public abstract String getDeleteBean( HttpServletRequest request );

    /**
     * Save (create or update a bean object
     * @param request the http request
     * @return the bean manage page
     */
    public abstract String doSaveBean( HttpServletRequest request );

    /**
     * Get the create bean page
     * 
     * @param request http the request
     * @return the page with bean list
     */
    public abstract String getSaveBean( HttpServletRequest request );

    /**
     * Get the manage bean page
     * 
     * @param request http the request
     * @return the page with bean list
     */
    public abstract String getManageBean( HttpServletRequest request );

    /**
     * Get the bean service
     * @return the bean service
     */
    protected abstract IPacService<K, E> getBeanService( );
}
