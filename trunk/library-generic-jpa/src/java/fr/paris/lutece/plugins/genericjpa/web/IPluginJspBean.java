package fr.paris.lutece.plugins.genericjpa.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface IPluginJspBean<K, E>
{
    /**
     * Get model for create beans
     * @param request the http request
     * @return the abstract model
     */
    Map<String, Object> getSaveBeanModel( HttpServletRequest request );

    /**
     * Delete a bean object
     * 
     * @param request The Http request
     * @return the html code message
     */
    String doDeleteBean( HttpServletRequest request );

    /**
     * Returns the confirmation message to delete a bean
     * 
     * @param request The Http request
     * @return the html code message
     */
    String getDeleteBean( HttpServletRequest request );

    /**
     * Save (create or update a bean object
     * @param request the http request
     * @return the bean manage page
     */
    String doSaveBean( HttpServletRequest request );

    /**
     * Get the create bean page
     * 
     * @param request http the request
     * @return the page with bean list
     */
    String getSaveBean( HttpServletRequest request );

    /**
     * Get the manage bean page
     * 
     * @param request http the request
     * @return the page with bean list
     */
    String getManageBean( HttpServletRequest request );

    /**
     * Get model for manage beans
     * @param request the http request
     * @return the abstract model
     */
    Map<String, Object> getManageBeanModel( HttpServletRequest request );
}
