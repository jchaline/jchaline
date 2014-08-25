package fr.paris.lutece.plugins.scala.bean.artifact

import fr.paris.lutece.plugins.genericjpa.bean.AbstractFilter
import scala.beans.BeanProperty

class ArtifactFilter extends AbstractFilter[Int] {

  @BeanProperty
  var artifactId: String = _
  @BeanProperty
  var version: String = _
  @BeanProperty
  var groupId: String = _

}