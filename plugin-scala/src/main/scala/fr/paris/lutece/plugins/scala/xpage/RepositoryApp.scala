package fr.paris.lutece.plugins.scala.xpage

import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger

import fr.paris.lutece.plugins.scala.bean.artifact.ArtifactFilter
import fr.paris.lutece.plugins.scala.service.artifact.IArtifactService
import fr.paris.lutece.portal.service.plugin.Plugin
import fr.paris.lutece.portal.service.spring.SpringContextService
import fr.paris.lutece.portal.service.template.AppTemplateService
import fr.paris.lutece.portal.web.xpages.AbstractXPageApplication
import fr.paris.lutece.portal.web.xpages.XPage
import javax.persistence.Entity
import javax.persistence.Table
import javax.servlet.http.HttpServletRequest

class RepositoryApp extends AbstractXPageApplication {

  var TEMPLATE_REPOSITORY = "skin/plugins/scala/repository/repository.html";

  var _artifactService: IArtifactService = SpringContextService.getBean("scala.artifactservice")

  var LOGGER: Logger = Logger.getLogger(classOf[RepositoryApp])

  def getPage(request: HttpServletRequest, nMode: Int, plugin: Plugin): XPage = {
    var page: XPage = new XPage()

    var model: Map[String, Object] = Map()

    var artifact = _artifactService.findByStrPrimaryKey("")

    var list = _artifactService.findAll(null)

    var template = AppTemplateService.getTemplate(TEMPLATE_REPOSITORY, request.getLocale(), model);

    page.setContent(template.getHtml());
    page.setPathLabel("repository");
    page.setTitle("repository");

    page
  }

  /**
   * Search for artifacts matching data send by user
   * @param request the http request with artifact id, group id and version required
   * @return the json with all artifacts matching datas
   */
  def searchArtifacts(request: HttpServletRequest) = {
    val groupId = request.getParameter("groupId")
    val artifactId = request.getParameter("artifactId")
    val version = request.getParameter("version")
    val filter = new ArtifactFilter
    if (StringUtils.isNotBlank(groupId)) {
      filter.setGroupId(groupId)
    }
    if (StringUtils.isNotBlank(artifactId)) {
      filter.setArtifactId(artifactId)
    }
    if (StringUtils.isNotBlank(version)) {
      filter.setVersion(version)
    }
    filter.setArtifactId("plugin-library")
    val res = _artifactService.find(filter, null)
    LOGGER.error("Search for " + groupId + ":" + artifactId + ":" + version)
    LOGGER.error("and find " + res.size + " candidate")
    "fr.paris.lutece:plugin-scala:1.0.0-SNAPSHOT"
  }

}