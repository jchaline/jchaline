package fr.paris.lutece.plugins.scala.bean.artifact;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel( Artifact.class )
class Artifact_
{
    public static volatile SingularAttribute<Artifact, Integer> _id;
    public static volatile SingularAttribute<Artifact, String> _version;
    public static volatile SingularAttribute<Artifact, String> _artifactId;
    public static volatile SingularAttribute<Artifact, String> _groupId;
}