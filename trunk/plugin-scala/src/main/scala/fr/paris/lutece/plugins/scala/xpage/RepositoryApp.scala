package fr.paris.lutece.plugins.scala.xpage

import fr.paris.lutece.plugins.scala.service.artifact.ArtifactService
import fr.paris.lutece.plugins.scala.service.artifact.IArtifactService
import fr.paris.lutece.portal.service.plugin.Plugin
import fr.paris.lutece.portal.service.template.AppTemplateService
import fr.paris.lutece.portal.web.xpages.AbstractXPageApplication
import fr.paris.lutece.portal.web.xpages.XPage
import javax.servlet.http.HttpServletRequest

class RepositoryApp extends AbstractXPageApplication {

  var TEMPLATE_REPOSITORY = "skin/plugins/scala/repository/repository.html";

  var artifactService = new ArtifactService()

  def getPage(request: HttpServletRequest, nMode: Int, plugin: Plugin): XPage = {
    var page: XPage = new XPage()

    var model: Map[String, Object] = Map()
    
    var listPos =List(2,4,3,5,7,2)
    
    var template = AppTemplateService.getTemplate(TEMPLATE_REPOSITORY, request.getLocale(), model);

    page.setContent(template.getHtml());
    page.setPathLabel("repository");
    page.setTitle("repository");

    page
  }

}