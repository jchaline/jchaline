package fr.paris.lutece.plugins.scala.service

import fr.paris.lutece.plugins.genericjpa.service.AbstractService
import fr.paris.lutece.plugins.scala.dao.ArtifactDAO
import fr.paris.lutece.plugins.scala.bean.Artifact
import fr.paris.lutece.plugins.genericjpa.dao.IPluginDAO

class ArtifactService extends AbstractService[Int,Artifact]{

  var artifactDAO=null:ArtifactDAO
  
  def getPluginDao():IPluginDAO[Int,Artifact]={
    artifactDAO
  }
  
  def findByStrPrimaryKey(key:String):Artifact={
    artifactDAO.findByStrPrimaryKey(key)
  }
}