package fr.paris.lutece.plugins.scala.web.artifact

import org.springframework.stereotype.Controller

import fr.paris.lutece.plugins.genericjpa.web.AbstractJspBean
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import fr.paris.lutece.plugins.scala.service.artifact.IArtifactService
import fr.paris.lutece.portal.service.spring.SpringContextService
import javax.servlet.http.HttpServletRequest

@Controller("scala.artifactjspbean")
class ArtifactJspBean extends AbstractJspBean[Int,Artifact] {
  
  var artifactService:IArtifactService=null
  
  override def init(request:HttpServletRequest, strRight:String){
    super.init(request, strRight)
  
    artifactService = SpringContextService.getBean("scala.artifactdservice")
  }

}