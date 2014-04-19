package fr.paris.lutece.plugins.pac.xpage.home;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.pac.xpage.AbstractPacApp;
import fr.paris.lutece.portal.service.message.SiteMessageException;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.security.UserNotSignedException;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.util.html.HtmlTemplate;

public class ContactApp extends AbstractPacApp
{
    private static final long serialVersionUID = 3183895933578412990L;

    private static final String TEMPLATE_CONTACT = "skin/plugins/pac/home/contact.html";

    @Override
    public XPage getPage( HttpServletRequest request, int nMode, Plugin plugin ) throws UserNotSignedException,
            SiteMessageException
    {
        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CONTACT, request.getLocale( ), null );
        
        XPage page = new XPage( );
        page.setContent( template.getHtml( ) );
        page.setPathLabel( "Contact" );
        page.setTitle( "Contact" );

        return page;
    }

}
