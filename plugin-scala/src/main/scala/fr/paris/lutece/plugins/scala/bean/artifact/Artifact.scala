package fr.paris.lutece.plugins.scala.bean.artifact

import org.hibernate.validator.constraints.NotEmpty
import fr.paris.lutece.plugins.genericjpa.bean.AbstractBean
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.TableGenerator
import javax.persistence.metamodel.SingularAttribute
import javax.persistence.metamodel.StaticMetamodel
import javax.persistence.GenerationType

@Entity
@Table(name = "scala_artifact")
class Artifact extends AbstractBean[Int] {

  @Id
  @TableGenerator(table = "scala_sequences", name = "scala_artifact_sequence", pkColumnValue = "scala_artifact_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scala_artifact_sequence")
  @Column(name = "id")
  var _id = 0;
  @NotEmpty
  @Column(name="version")
  var _version = "";
  @NotEmpty
  @Column(name="groupid")
  var _groupId = "";
  @NotEmpty
  @Column(name="artifactid")
  var _artifactId = "";

  def getId(): Int = {
    _id
  }

  def setId(id: Int): Unit = {
    _id = id
  }
  
}