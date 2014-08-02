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

    var graph = new Graph()
    graph.addNode("A")
    graph.addNode("B")
    graph.addNode("C")
    graph.addNode("D")
    graph.addNode("E")
    graph.addNode("F")
    graph.addNode("G")
    graph.addNode("H")
    graph.addNode("I")
    graph.addNode("J")
    graph.addLink("A", "B", 85)
    graph.addLink("A", "C", 217)
    graph.addLink("A", "E", 173)
    graph.addLink("B", "F", 80)
    graph.addLink("C", "G", 186)
    graph.addLink("C", "H", 103)
    graph.addLink("H", "D", 183)
    graph.addLink("H", "J", 167)
    graph.addLink("F", "I", 250)
    graph.addLink("I", "J", 84)
    graph.addLink("E", "J", 502)

    var neighbors = graph.getNeighbors("A")
    //LOGGER.debug(neighbors)

    var way = graph.findShortestWay("A", "J")
    //LOGGER.error(way)

    var artifact = artifactService.findByStrPrimaryKey("")
    println(artifact)
    
    var list = artifactService.findAll(null)
    print(list)

    var template = AppTemplateService.getTemplate(TEMPLATE_REPOSITORY, request.getLocale(), model);

    page.setContent(template.getHtml());
    page.setPathLabel("repository");
    page.setTitle("repository");

    page
  }

}