package fr.paris.lutece.plugins.pac.bean.artifact;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( Artifact.class )
public class Artifact_
{
    public static volatile SingularAttribute<Artifact, Integer> _id;
    public static volatile SingularAttribute<Artifact, String> _groupId;
    public static volatile SingularAttribute<Artifact, String> _versionId;
    public static volatile SingularAttribute<Artifact, String> _artifactId;
}
