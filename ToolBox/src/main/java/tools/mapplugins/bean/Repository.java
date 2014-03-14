package tools.mapplugins.bean;

import java.util.HashMap;
import java.util.Map;


/**
 * Handling artifacts
 * @author jchaline
 */
public class Repository
{
    /**
     * Reference all the artifact, getting by group id, id, and version
     */
    private Map<String, Map<String, Map<String, ArtifactVersion>>> artifacts;

    /**
     * The way to add artifact to repository
     * @param artifact the artifact to add
     */
    public void add( ArtifactVersion artifact )
    {
        if ( getArtifacts( ).containsKey( artifact.getGroupId( ) ) )
        {
            getArtifacts( ).put( artifact.getGroupId( ), new HashMap<String, Map<String, ArtifactVersion>>( ) );
        }
        if ( getArtifacts( ).get( artifact.getGroupId( ) ).containsKey( artifact.getId( ) ) )
        {
            getArtifacts( ).get( artifact.getGroupId( ) ).put( artifact.getId( ),
                    new HashMap<String, ArtifactVersion>( ) );
        }
        getArtifacts( ).get( artifact.getGroupId( ) ).get( artifact.getId( ) ).put( artifact.getVersion( ), artifact );
    }

    /**
     * @return the artifacts
     */
    public Map<String, Map<String, Map<String, ArtifactVersion>>> getArtifacts( )
    {
        return artifacts;
    }

    /**
     * @param artifacts the artifacts to set
     */
    public void setArtifacts( Map<String, Map<String, Map<String, ArtifactVersion>>> artifacts )
    {
        this.artifacts = artifacts;
    }
}
