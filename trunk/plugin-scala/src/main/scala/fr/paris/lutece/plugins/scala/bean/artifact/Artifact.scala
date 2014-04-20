package fr.paris.lutece.plugins.scala.bean.artifact

import org.hibernate.validator.constraints.NotEmpty
import fr.paris.lutece.plugins.genericjpa.bean.AbstractBean

class Artifact extends AbstractBean[Int] {

  var _id = 0;
  @NotEmpty
  var _version = "";
  @NotEmpty
  var _groupId = "";
  @NotEmpty
  var _artifactId = "";

  def getId(): Int= {
    _id
  }

  def setId(id: Int): Unit = {
    _id = id
  }

}