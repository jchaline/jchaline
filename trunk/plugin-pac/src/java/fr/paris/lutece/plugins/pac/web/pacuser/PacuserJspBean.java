package fr.paris.lutece.plugins.pac.web.pacuser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.pac.bean.Closure;
import fr.paris.lutece.plugins.pac.bean.GenericJPABean;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.bean.pacuser.PacuserFilter;
import fr.paris.lutece.plugins.pac.dto.pacuser.PacuserDTO;
import fr.paris.lutece.plugins.pac.service.IPacService;
import fr.paris.lutece.plugins.pac.service.pacuser.IPacuserService;
import fr.paris.lutece.plugins.pac.service.pacuser.PacuserService;
import fr.paris.lutece.plugins.pac.utils.commons.CsvUtils;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;
import fr.paris.lutece.plugins.pac.utils.commons.PacConstants;
import fr.paris.lutece.plugins.pac.web.AbstractPacJspBean;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.util.beanvalidation.ValidationError;
import fr.paris.lutece.util.datatable.DataTableManager;
import fr.paris.lutece.util.html.HtmlTemplate;


/**
 * The Pacuser Jsp Bean
 * @author jchaline
 */
public class PacuserJspBean extends AbstractPacJspBean<Integer, Pacuser>
{

    public static final String RIGHT_MANAGE_BEAN = "PAC_PACUSER_MANAGEMENT";

    private static final long serialVersionUID = 4056641770117704752L;

    private static final String I18N_ERROR_OCCUR = "pac.error.errorOccur";

    private static final String PARAMETER_DOWNLOAD_FILE_NAME = "pac.csv";

    private static final String MARK_PACFILE = "pacfile";
    private static final String MARK_PAC_FIELD_PREFIX = "pac.pacuser.field.";

    private IPacuserService _servicePacuser;

    @Override
    public void init( HttpServletRequest request, String strRight ) throws AccessDeniedException
    {
        super.init( request, strRight );
        _servicePacuser = SpringContextService.getBean( PacConfigs.BEAN_PACUSER_SERVICE );
    }

    @Override
    public String getManageBean( HttpServletRequest request )
    {
        Map<String, Object> model = super.getManageBeanModel( request );

        PacuserFilter filter = new PacuserFilter( );
        populate( filter, request );

        DataTableManager<PacuserDTO> dataTableUser = getDataTable( request, filter );

        model.put( MARK_DATA_TABLE_BEAN, dataTableUser );
        model.put( MARK_FILTER, filter );

        HtmlTemplate template = AppTemplateService
                .getTemplate( PacConfigs.TEMPLATE_MANAGE_PACUSER, getLocale( ), model );

        dataTableUser.clearItems( );

        return getAdminPage( template.getHtml( ) );
    }

    @Override
    public String getSaveBean( HttpServletRequest request )
    {
        Map<String, Object> model = super.getSaveBeanModel( request, _servicePacuser );
        String id = request.getParameter( MARK_BEAN_ID );
        if ( StringUtils.isNotBlank( id ) )
        {
            Pacuser bean = _servicePacuser.findByStrPrimaryKey( id );

            PacuserDTO dto = PacuserDTO.convert( bean );

            model.put( MARK_BEAN, dto );
        }

        HtmlTemplate template = AppTemplateService.getTemplate( PacConfigs.TEMPLATE_SAVE_PACUSER, getLocale( ), model );

        return getAdminPage( template.getHtml( ) );
    }

    @Override
    public String doSaveBean( HttpServletRequest request )
    {
        String returnUrl = doGoBack( request, PacConfigs.JSP_MANAGE_PACUSER );
        if ( request.getParameter( PacConstants.MARK_CANCEL ) != null )
        {
            return returnUrl;
        }

        PacuserDTO dto = new PacuserDTO( );
        populate( dto, request );

        Pacuser bean = dto.convert( );
        List<ValidationError> errors = validate( bean, MARK_PAC_FIELD_PREFIX );

        if ( errors.isEmpty( ) )
        {
            _servicePacuser.doSaveBean( bean );
        }
        else
        {
            returnUrl = AdminMessageService.getMessageUrl( request, Messages.MESSAGE_INVALID_ENTRY, errors );
        }

        return returnUrl;
    }

    @Override
    public String getDeleteBean( HttpServletRequest request )
    {
        Map<String, Object> urlParam = new HashMap<String, Object>( );
        String strBeanId = request.getParameter( MARK_BEAN_ID );
        urlParam.put( MARK_BEAN_ID, strBeanId );

        return AdminMessageService.getMessageUrl( request, "pac.pacuser.confirmation.delete.bean", null,
                "pac.transverse.title.delete", PacConfigs.JSP_DO_DELETE_PACUSER, "_self",
                AdminMessage.TYPE_CONFIRMATION, urlParam, PacConfigs.JSP_MANAGE_PACUSER );
    }

    @Override
    public String doDeleteBean( HttpServletRequest request )
    {
        String manageUrl = doGoBack( request, PacConfigs.JSP_MANAGE_PACUSER );

        String strBeanId = request.getParameter( MARK_BEAN_ID );
        Integer beanId = null;
        try
        {
            beanId = Integer.valueOf( strBeanId );
            _servicePacuser.doDeleteBean( beanId );
        }
        catch ( NumberFormatException e )
        {
            LOGGER.error( e );
        }

        return manageUrl;
    }

    /**
     * Get the DataTableManager object for the ShowDTO bean
     * @param request the http request
     * @param filter the filter
     * @return the data table to use
     */
    private DataTableManager<PacuserDTO> getDataTable( HttpServletRequest request, PacuserFilter filter )
    {
        Closure converter = PacuserService.getFuncConverter( );
        DataTableManager<PacuserDTO> dataTableToUse = getAbstractDataTableManager( request, _servicePacuser, converter,
                filter, MARK_DATA_TABLE_BEAN, PacConfigs.JSP_MANAGE_PACUSER );

        addDatatableColumns( dataTableToUse );
        saveDataTableInSession( request, dataTableToUse, MARK_DATA_TABLE_BEAN );
        return dataTableToUse;
    }

    /**
     * Add the columns to the DataTableManager if necessary
     * @param dataTableToUse
     */
    private <T> void addDatatableColumns( DataTableManager<T> dataTableToUse )
    {
        if ( dataTableToUse.getListColumn( ).isEmpty( ) )
        {
            dataTableToUse.addFreeColumn( "", "columnSelectBean" );
            dataTableToUse.addColumn( "pac.pacuser.field.nom", "nom", false );
            dataTableToUse.addColumn( "pac.pacuser.field.prenom", "prenom", false );
            dataTableToUse.addColumn( "pac.pacuser.field.prochainPac", "prochainPac", false );
            dataTableToUse.addColumn( "pac.pacuser.field.dernierPac", "dernierPac", false );
            dataTableToUse.addColumn( "pac.pacuser.field.dateEntree", "dateEntree", false );
            dataTableToUse.addFreeColumn( "pac.transverse.title.actions", PARAMETER_MACRO_COLUMN_ACTIONS_BEAN );
        }
    }

    @Override
    protected IPacService<Integer, GenericJPABean<Integer>> getBeanService( )
    {
        IPacService service = null;
        if ( _servicePacuser instanceof IPacuserService )
        {
            service = _servicePacuser;
        }
        return service;
    }

    /**
     * Re-order the next pac
     * @return the manage pac url
     */
    public String doOrderNextPac( HttpServletRequest request )
    {
        _servicePacuser.doOrderNextPac( );
        return doGoBack( request, PacConfigs.JSP_MANAGE_PACUSER );
    }

    /**
     * Get CSV file with all the pacuser informations
     * @param request the http request
     * @param response the http response
     * @return the url redirection
     */
    public String doDownload( HttpServletRequest request, HttpServletResponse response )
    {
        List<String[]> data = _servicePacuser.doDownload( );
        String url = null;
        try
        {
            CsvUtils.downloadFile( response, data, PARAMETER_DOWNLOAD_FILE_NAME );
            url = doGoBack( request, PacConfigs.JSP_MANAGE_PACUSER );
        }
        catch ( IOException e )
        {
            url = AdminMessageService.getMessageUrl( request, I18N_ERROR_OCCUR, AdminMessage.TYPE_ERROR );
        }

        return url;
    }

    /**
     * Load CSV file with all the pacuser informations and save in database.
     * Give id to try updating data, or not to create data
     * @param request the http request
     * @return the url redirection
     */
    public String doUpload( HttpServletRequest request )
    {
        List<List<String>> fileContent = CsvUtils.uploadFile( request, MARK_PACFILE );
        _servicePacuser.doUpload(fileContent);
        return StringUtils.EMPTY;
    }
}