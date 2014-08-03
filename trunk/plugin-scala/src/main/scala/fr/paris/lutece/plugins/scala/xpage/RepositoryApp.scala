package fr.paris.lutece.plugins.scala.xpage

import org.apache.log4j.Logger

import fr.paris.lutece.plugins.genericjpa.service.IPluginService
import fr.paris.lutece.plugins.scala.bean.graph.Graph
import fr.paris.lutece.plugins.scala.service.artifact.ArtifactService
import fr.paris.lutece.plugins.scala.service.artifact.IArtifactService
import fr.paris.lutece.portal.service.plugin.Plugin
import fr.paris.lutece.portal.service.spring.SpringContextService
import fr.paris.lutece.portal.service.template.AppTemplateService
import fr.paris.lutece.portal.web.xpages.AbstractXPageApplication
import fr.paris.lutece.portal.web.xpages.XPage
import javax.servlet.http.HttpServletRequest

class RepositoryApp extends AbstractXPageApplication {

  var TEMPLATE_REPOSITORY = "skin/plugins/scala/repository/repository.html";

  var artifactService:IArtifactService = SpringContextService.getBean("scala.artifactservice")

  var LOGGER: Logger = Logger.getLogger(classOf[RepositoryApp])

  def getPage(request: HttpServletRequest, nMode: Int, plugin: Plugin): XPage = {
    var page: XPage = new XPage()

    var model: Map[String, Object] = Map()


    var artifact = artifactService.findByStrPrimaryKey("")
    println(artifact)
    
    var list = artifactService.findAll(null)

    var template = AppTemplateService.getTemplate(TEMPLATE_REPOSITORY, request.getLocale(), model);

    page.setContent(template.getHtml());
    page.setPathLabel("repository");
    page.setTitle("repository");

    page
  }
  
  def getArtifact(request: HttpServletRequest)={
    "fr.paris.lutece:plugin-scala:1.0.0-SNAPSHOT"
  }

}