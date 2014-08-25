package fr.paris.lutece.plugins.scala.service.artifact

import org.springframework.stereotype.Service

import fr.paris.lutece.plugins.genericjpa.dao.IPluginDAO
import fr.paris.lutece.plugins.genericjpa.service.AbstractService
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import fr.paris.lutece.plugins.scala.dao.artifact.ArtifactDAO
import javax.inject.Inject

@Service("scala.artifactservice")
class ArtifactService extends AbstractService[Int,Artifact] with IArtifactService{

  @Inject
  var artifactDAO=null:ArtifactDAO
  
  def getPluginDao():IPluginDAO[Int,Artifact]={
    artifactDAO
  }
  
  def findByStrPrimaryKey(key:String):Artifact={
    //artifactDAO.findByStrPrimaryKey(key)
      new Artifact()
  }
  
}