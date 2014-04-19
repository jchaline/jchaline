package fr.paris.lutece.plugins.pac.service.datatable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import fr.paris.lutece.plugins.pac.bean.Closure;
import fr.paris.lutece.plugins.pac.bean.GenericJPAFilter;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationPropertiesAdapterDataTable;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationPropertiesImpl;
import fr.paris.lutece.plugins.pac.dao.commons.ResultList;
import fr.paris.lutece.plugins.pac.service.IPacService;
import fr.paris.lutece.util.datatable.DataTableManager;
import fr.paris.lutece.util.datatable.DataTablePaginationProperties;
import fr.paris.lutece.util.html.Paginator;

/**
 * 
 * @author jchaline
 *
 */
public class DatatableService
{
    private static final Logger LOGGER = Logger.getLogger( DatatableService.class );
    private static final String MARK_DATA_TABLE_MANAGER = "dataTableManager";
    private static final String MARK_FILTER = "filter";
    private static final String MARK_PLUGIN_NAME = "plugin_name";

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
     * @param filter the bean filter
     * @param keyDataTable the key to save DataTableManager in Session
     * @param jspManage the url to the manage page
     * @param service the bean service
     * @param type the class type
     * @return the DataTableManager
     */
    public <T> DataTableManager<T> getAbstractDataTableManager( HttpServletRequest request,
            IPacService service, GenericJPAFilter filter, String keyDataTable, String jspManage )
    {
        //si un objet est déjà présent en session, on l'utilise
        DataTableManager<T> dataTableToUse = getDataTableToUse( request, keyDataTable, jspManage );

        //determination de l'utilisation d'un nouveau filtre (recherche) ou de celui présent en session (changement de page)
        GenericJPAFilter filterToUse = getFilterToUse( request, filter, MARK_FILTER, dataTableToUse );
        BeanUtils.copyProperties( filterToUse, filter );

        //mise à jour de la pagination dans le data table pour l'afficahge de la page courante et du nombre d'items
        DataTablePaginationProperties updatePaginator = dataTableToUse.getAndUpdatePaginator( request );

        //obtention manuel des beans à afficher
        PaginationProperties paginationProperties = new PaginationPropertiesAdapterDataTable( updatePaginator );

        ResultList<T> listAllBean = service.find( filterToUse, paginationProperties );
        request.getSession( ).setAttribute( MARK_FILTER, filter );
        dataTableToUse.setItems( listAllBean, listAllBean.getTotalResult( ) );

        return dataTableToUse;
    }

    /**
     * Abstract implementation to generate DataTableManager
     * @param request the http request
     * @param filter the bean filter
     * @param keyDataTable the key to save DataTableManager in Session
     * @param jspManage the url to the manage page
     * @param service the bean service
     * @param converter the converter closure for the bean list
     * @param type the class type
     * @return the DataTableManager
     */
    public <E, D> DataTableManager<D> getAbstractDataTableManager( HttpServletRequest request,
            IPacService service, Closure converter, GenericJPAFilter filter, String keyDataTable, String jspManage )
    {
        //si un objet est déjà présent en session, on l'utilise
        DataTableManager<D> dataTableToUse = getDataTableToUse( request, keyDataTable, jspManage );

        //determination de l'utilisation d'un nouveau filtre (recherche) ou de celui présent en session (changement de page)
        GenericJPAFilter filterToUse = getFilterToUse( request, filter, MARK_FILTER, dataTableToUse );
        BeanUtils.copyProperties( filterToUse, filter );

        //mise à jour de la pagination dans le data table pour l'afficahge de la page courante et du nombre d'items
        DataTablePaginationProperties updatePaginator = dataTableToUse.getAndUpdatePaginator( request );

        //obtention manuel des beans à afficher
        PaginationProperties paginationProperties = new PaginationPropertiesAdapterDataTable( updatePaginator );

        ResultList<E> listAllBean = service.find( filterToUse, paginationProperties );
        List<D> listDTO = (List<D>) converter.process( listAllBean );
        request.getSession( ).setAttribute( MARK_FILTER, filter );
        dataTableToUse.setItems( listDTO, listAllBean.getTotalResult( ) );

        return dataTableToUse;
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
    private static <T> GenericJPAFilter getFilterToUse( HttpServletRequest request, GenericJPAFilter filter,
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
    private static <T> DataTableManager<T> getDataTableToUse( HttpServletRequest request, String markFilter,
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
    private static <T> DataTableManager<T> loadDataTableFromSession( HttpServletRequest request, String key )
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
    public static <T> void saveDataTableInSession( HttpServletRequest request, DataTableManager<T> dataTable, String key )
    {
        request.getSession( ).setAttribute( StringUtils.isNotBlank( key ) ? key : MARK_DATA_TABLE_MANAGER, dataTable );
    }

}