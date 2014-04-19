package fr.paris.lutece.plugins.pac.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.pac.dao.commons.PaginationProperties;

public interface IGenericJPASJspBean
{
    /**
     * Return a bean for pagination in service/dao using parameter in http
     * request
     * @param request http request
     * @return paginator
     */
    public PaginationProperties getPaginationProperties( HttpServletRequest request );

    /**
     * Get model for create beans
     * @param request the http request
     * @return the abstract model
     */
    public Map<String, Object> getSaveBeanModel( HttpServletRequest request );

    /**
     * Get model for manage beans
     * @param request the http request
     * @return the abstract model
     */
    public Map<String, Object> getManageBeanModel( HttpServletRequest request );

    /**
     * Load common data usable in all the applications
     * @param request the http request
     * @param model the data model
     */
    public void loadCommonData( HttpServletRequest request, Map<String, Object> model );

    /**
     * Return the url of the JSP which called the last action.
     * 
     * @param request The Http request
     * @return The url of the last JSP
     */
    public String doGoBack( HttpServletRequest request, String JSP_MANAGE_BEAN );

}
