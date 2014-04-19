package fr.paris.lutece.plugins.pac.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.pac.bean.GenericJPABean;
import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;
import fr.paris.lutece.plugins.pac.service.IPluginService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.util.AppPathService;


public abstract class AbstractPacJspBean<K, E extends GenericJPABean<K>> extends
        GenericJPAJspBean<K, GenericJPABean<K>> implements IPluginJspBean<K, E>
{
    private static final long serialVersionUID = 9059302780101964905L;

    protected static final String MARK_BACK_URL = "backUrl";
    protected static final String MARK_BEAN_ID = "bean_id";
    protected static final String MARK_DATA_TABLE_BEAN = "dataTableBean";

    protected static final String PARAMETER_MACRO_COLUMN_ACTIONS_BEAN = "columnActionsBean";

    /**
     * Return a bean for pagination in service/dao using parameter in http
     * request
     * @param request http request
     * @return paginator
     */
    protected PaginationProperties getPaginationProperties( HttpServletRequest request )
    {
        return null;
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

    @Override
    public Map<String, Object> getManageBeanModel( HttpServletRequest request )
    {
        return new HashMap<String, Object>( );
    }

    @Override
    public Map<String, Object> getSaveBeanModel( HttpServletRequest request, IPluginService service )
    {
        Map<String, Object> model = new HashMap<String, Object>( );
        model.put( "locale", request.getLocale( ) );
        return model;
    }

    @Override
    public String doDeleteBean( HttpServletRequest request )
    {
        return AdminMessageService.getMessageUrl( request, "pac.error.feature.manquante", AdminMessage.TYPE_STOP );
    }

    @Override
    public String getDeleteBean( HttpServletRequest request )
    {
        return StringUtils.EMPTY;
    }

    @Override
    public String doSaveBean( HttpServletRequest request )
    {
        return AdminMessageService.getMessageUrl( request, "pac.error.feature.manquante", AdminMessage.TYPE_STOP );
    }

    @Override
    public String getSaveBean( HttpServletRequest request )
    {
        return StringUtils.EMPTY;
    }
}
