package tools.mapplugins.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tools.mapplugins.xml.Project;


/**
 * Handling artifacts
 * @author jchaline
 */
public class Repository implements Serializable
{
    private static final long serialVersionUID = 8115463804253023495L;
    /**
     * Reference all the artifact, getting by group id, id, and version
     */
    private Map<String, Map<String, Map<String, Project>>> artifacts = new HashMap<String, Map<String, Map<String, Project>>>( );

    /**
     * Get all the artifacts
     * @return the artifacts list
     */
    public List<Project> getProjectsList( )
    {
        List<Project> list = new ArrayList<Project>( );
        for ( Map<String, Map<String, Project>> groups : artifacts.values( ) )
        {
            for ( Map<String, Project> versions : groups.values( ) )
            {
                list.addAll( versions.values( ) );
            }
        }

        return list;
    }

    /**
     * The way to add artifact to repository
     * @param artifact the artifact to add
     */
    public void add( Project artifact )
    {
        if ( !getArtifacts( ).containsKey( artifact.getGroupId( ) ) )
        {
            getArtifacts( ).put( artifact.getGroupId( ), new HashMap<String, Map<String, Project>>( ) );
        }
        if ( !getArtifacts( ).get( artifact.getGroupId( ) ).containsKey( artifact.getArtifactId( ) ) )
        {
            getArtifacts( ).get( artifact.getGroupId( ) ).put( artifact.getArtifactId( ),
                    new HashMap<String, Project>( ) );
        }
        getArtifacts( ).get( artifact.getGroupId( ) ).get( artifact.getArtifactId( ) )
                .put( artifact.getVersion( ), artifact );
    }

    /**
     * @return the artifacts
     */
    public Map<String, Map<String, Map<String, Project>>> getArtifacts( )
    {
        return artifacts;
    }

    /**
     * @param artifacts the artifacts to set
     */
    public void setArtifacts( Map<String, Map<String, Map<String, Project>>> artifacts )
    {
        this.artifacts = artifacts;
    }

    public Project findDependency( String groupId, String artifactId, String version )
    {
        Project dependency = null;
        if ( this.artifacts.containsKey( groupId ) )
        {
            Map<String, Map<String, Project>> group = this.artifacts.get( groupId );
            if ( group.containsKey( artifactId ) )
            {
                Map<String, Project> versions = group.get( artifactId );
                Set<String> keySet = versions.keySet( );
                List<String> listVersion = new ArrayList<String>(keySet);
                Collections.sort( listVersion, new ArtifactComparator( ) );
                System.out.println(listVersion);
            }
        }

        return dependency;
    }
}
