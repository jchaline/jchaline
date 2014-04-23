package fr.paris.lutece.plugins.scala.web.artifact
import fr.paris.lutece.plugins.genericjpa.web.AbstractJspBean
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import javax.servlet.http.HttpServletRequest
import fr.paris.lutece.portal.service.admin.PasswordResetException
import fr.paris.lutece.portal.service.admin.AccessDeniedException
import fr.paris.lutece.plugins.scala.service.artifact.IArtifactService
import fr.paris.lutece.portal.service.spring.SpringContextService

class ArtifactJspBean extends AbstractJspBean[Int,Artifact] {
  
  var artifactService:IArtifactService=null
  
  override def init(request:HttpServletRequest, strRight:String){
    super.init(request, strRight)
  
    artifactService = SpringContextService.getBean("scala.artifactdservice")
  }

}