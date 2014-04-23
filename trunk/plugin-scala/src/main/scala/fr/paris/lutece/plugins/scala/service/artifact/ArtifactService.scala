package fr.paris.lutece.plugins.scala.service.artifact

import fr.paris.lutece.plugins.genericjpa.dao.IPluginDAO
import fr.paris.lutece.plugins.genericjpa.service.AbstractService
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import fr.paris.lutece.plugins.scala.dao.artifact.ArtifactDAO
import javax.inject.Inject

class ArtifactService extends IArtifactService {

  @Inject
  var artifactDAO=null:ArtifactDAO
  
  def getPluginDao():IPluginDAO[Int,Artifact]={
    artifactDAO
  }
  
  def findByStrPrimaryKey(key:String):Artifact={
    artifactDAO.findByStrPrimaryKey(key)
  }
  
}