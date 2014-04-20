package fr.paris.lutece.plugins.scala.service.artifact

import fr.paris.lutece.plugins.genericjpa.dao.IPluginDAO
import fr.paris.lutece.plugins.genericjpa.service.AbstractService
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import fr.paris.lutece.plugins.scala.dao.ArtifactDAO

class ArtifactService extends IArtifactService {

  var artifactDAO=null:ArtifactDAO
  
  def getPluginDao():IPluginDAO[Int,Artifact]={
    artifactDAO
  }
  
  def findByStrPrimaryKey(key:String):Artifact={
    artifactDAO.findByStrPrimaryKey(key)
  }
  
  def dijkstra(pos :List[Int]):List[Int] = {
    System.out.println("bonjour")
    
    pos
  }
}