package fr.paris.lutece.plugins.pac.web.pacdate;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.genericjpa.web.AbstractJspBean;
import fr.paris.lutece.plugins.pac.bean.pacdate.Pacdate;
import fr.paris.lutece.plugins.pac.service.pacdate.IPacdateService;
import fr.paris.lutece.plugins.pac.utils.commons.PacConfigs;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public class PacdateJspBean extends AbstractJspBean<Integer, Pacdate>
{
    private static final long serialVersionUID = 3333848127533730106L;
    
    public static final String RIGHT_MANAGE_BEAN = "PAC_PACDATE_MANAGEMENT";
    
    private IPacdateService _servicePacdate;
    
    @Override
    public void init( HttpServletRequest request, String strRight ) throws AccessDeniedException
    {
        super.init( request, strRight );
        _servicePacdate = SpringContextService.getBean( PacConfigs.BEAN_PACDATE_SERVICE );
    }

}
