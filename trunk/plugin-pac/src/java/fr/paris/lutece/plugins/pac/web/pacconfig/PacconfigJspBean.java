package fr.paris.lutece.plugins.pac.web.pacconfig;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.pac.bean.GenericJPABean;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.service.IPacService;
import fr.paris.lutece.plugins.pac.service.pacconfig.IPacconfigService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;
import fr.paris.lutece.plugins.pac.web.AbstractPacJspBean;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public class PacconfigJspBean extends AbstractPacJspBean<Integer, Pacdate>
{
    private static final long serialVersionUID = 3333848127533730106L;
    
    public static final String RIGHT_MANAGE_BEAN = "PAC_PACCONFIG_MANAGEMENT";
    
    private IPacconfigService _servicePacconfig;
    
    @Override
    public void init( HttpServletRequest request, String strRight ) throws AccessDeniedException
    {
        super.init( request, strRight );
        _servicePacconfig = SpringContextService.getBean( PacConfigs.BEAN_PACCONFIG_SERVICE );
    }

    @Override
    public String getManageBean( HttpServletRequest request )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected IPacService<Integer, GenericJPABean<Integer>> getBeanService( )
    {
        // TODO Auto-generated method stub
        return null;
    }

}
