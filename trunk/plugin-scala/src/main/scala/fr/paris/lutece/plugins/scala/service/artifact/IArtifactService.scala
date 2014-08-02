package fr.paris.lutece.plugins.scala.service.artifact

import fr.paris.lutece.plugins.genericjpa.service.IPluginService
import fr.paris.lutece.plugins.genericjpa.service.AbstractService
import fr.paris.lutece.plugins.scala.bean.artifact.Artifact

trait IArtifactService extends IPluginService[Int,Artifact] {
}