package fr.paris.lutece.plugins.scala.bean.artifact

import javax.persistence.metamodel.StaticMetamodel
import javax.persistence.metamodel.SingularAttribute

@StaticMetamodel(classOf[Artifact])
object Artifact_ {
  @volatile var _id: SingularAttribute[Artifact, Integer] = null
  @volatile var _version: SingularAttribute[Artifact, String] = null
  @volatile var _artifactId: SingularAttribute[Artifact, String] = null
  @volatile var _groupId: SingularAttribute[Artifact, String] = null
}