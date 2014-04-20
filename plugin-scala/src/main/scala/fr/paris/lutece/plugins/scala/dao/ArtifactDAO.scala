package fr.paris.lutece.plugins.scala.dao

import fr.paris.lutece.plugins.genericjpa.dao.AbstractDAO
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact
import fr.paris.lutece.plugins.scala.service.ScalaPlugin

class ArtifactDAO extends AbstractDAO[Int, Artifact]{

  def getBeanClass():Class[Artifact]={
    classOf[Artifact]
  }
  
  def getPluginName():String={
    ScalaPlugin.PLUGIN_NAME
  }
  
  def findByStrPrimaryKey(key:String):Artifact={
    null
  }
}