package fr.paris.lutece.plugins.pac.xpage.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.genericjpa.dao.commons.ResultList;
import fr.paris.lutece.plugins.genericjpa.utils.messages.SessionMessage;
import fr.paris.lutece.plugins.pac.bean.pacuser.Pacuser;
import fr.paris.lutece.plugins.pac.dto.pacuser.PacuserDTO;
import fr.paris.lutece.plugins.pac.service.pacuser.IPacuserService;
import fr.paris.lutece.plugins.pac.xpage.AbstractPacApp;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.util.html.HtmlTemplate;


public class NextPacApp extends AbstractPacApp
{
    private static final long serialVersionUID = 7755916155092089155L;
    private static final String TEMPLATE_NEXTPAC = "skin/plugins/pac/nextpac/nextpac.html";

    private static final String MARK_LIST_PACUSER = "listPacuser";

    private IPacuserService _servicePacuser = SpringContextService.getBean( "pac.pacuserservice" );

    @Override
    public XPage getPage( HttpServletRequest request, int nMode, Plugin plugin ) throws UserNotSignedException,
            SiteMessageException
    {
        ResultList<Pacuser> listPacuser = _servicePacuser.findAll( null );
        List<PacuserDTO> listDTO = PacuserDTO.convert( listPacuser );
        Map<String, Object> model = new HashMap<String, Object>( );
        model.put( MARK_LIST_PACUSER, listDTO );

        //        SessionMessage.pushMessage( request, SessionMessage.CODE_ALERTE, "pac.pacuser.field.prochainPac" );
        //        model.put( SessionMessage.MARK_SESSION_MESSAGE, SessionMessage.popMessage( request ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_NEXTPAC, request.getLocale( ), model );

        XPage page = new XPage( );
        page.setContent( template.getHtml( ) );
        page.setPathLabel( "Prochains PAC" );
        page.setTitle( "Prochains PAC" );

        return page;
    }

}
