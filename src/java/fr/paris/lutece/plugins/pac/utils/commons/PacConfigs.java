package fr.paris.lutece.plugins.pac.utils.commons;

/**
 * Configure all the bean, jsp and template 
 * @author jchaline
 *
 */
public final class PacConfigs
{
    /* BEAN DEFINITION */
    public static final String BEAN_PACDATE_SERVICE = "pac.pacdateservice";
    public static final String BEAN_PACDATE_DAO = "pac.pacdatedao";
    public static final String BEAN_PACUSER_SERVICE = "pac.pacuserservice";
    public static final String BEAN_PACUSER_DAO = "pac.pacuserdao";
    public static final String BEAN_PACCONFIG_SERVICE = "pac.pacconfigservice";
    public static final String BEAN_PACCONFIG_DAO = "pac.pacconfigdao";
    public static final String BEAN_ARTIFACT_SERVICE = "pac.artifactservice";
    public static final String BEAN_ARTIFACT_DAO = "pac.artifactdao";

    public static final String BEAN_DOZER_MAPPER= "mapper";
    
    /* JSP DEFINITIO */
    public static final String JSP_SAVE_PACUSER = "jsp/admin/plugins/pac/pacuser/SavePacuser.jsp";
    public static final String JSP_DO_DELETE_PACUSER = "jsp/admin/plugins/pac/pacuser/DoDeletePacuser.jsp";
    public static final String JSP_MANAGE_PACUSER = "jsp/admin/plugins/pac/pacuser/ManagePacuser.jsp";
    public static final String JSP_SAVE_PACDATE = "jsp/admin/plugins/pac/pacdate/SavePacdate.jsp";
    public static final String JSP_DO_DELETE_PACDATE = "jsp/admin/plugins/pacdate/conge/DoDeletePacdate.jsp";
    public static final String JSP_MANAGE_PACDATE = "jsp/admin/plugins/pac/pacdate/ManagePacdate.jsp";
    public static final String JSP_PACUSER_MASSE_ACTION = "jsp/admin/plugins/pac/pacuser/MasseAction.jsp";
    public static final String JSP_PACUSER_DO_MASSE_ACTION = "jsp/admin/plugins/pac/pacuser/DoMasseAction.jsp";

    /* TEMPLATE DEFINITION */
    public static final String TEMPLATE_SAVE_PACUSER = "admin/plugins/pac/bean/pacuser/save_pacuser.html";
    public static final String TEMPLATE_MANAGE_PACUSER = "admin/plugins/pac/bean/pacuser/manage_pacuser.html";
    public static final String TEMPLATE_SAVE_PACDATE = "admin/plugins/pac/bean/pacdate/save_pacdate.html";
    public static final String TEMPLATE_MANAGE_PACDATE = "admin/plugins/pac/bean/pacdate/manage_pacdate.html";
    
    /* I18N */
    public static final String I18N_ERROR_OCCUR = "pac.error.errorOccur";
    
    private PacConfigs(){}
}
