package fr.paris.lutece.plugins.scala.dao

import fr.paris.lutece.plugins.genericjpa.dao.AbstractDAO
import fr.paris.lutece.plugins.scala.bean.Artifact

class ArtifactDAO extends AbstractDAO[Int, Artifact]{

  def getBeanClass():Class[Artifact]={
    classOf[Artifact]
  }
  
  def getPluginName():String={
    "scala"
  }
  
  def findByStrPrimaryKey(key:String):Artifact={
    null
  }
}